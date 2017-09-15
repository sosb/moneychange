import controller.MoneyChangeController;
import model.CurrencyRepository;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.testng.Assert;
import org.testng.annotations.Test;
import service.Currency;
import service.CurrencyService;

import javax.inject.Inject;
import java.util.Map;
import java.util.TreeMap;

import static org.testng.Assert.assertEquals;

public class ServiceTest extends Arquillian {


	@Deployment
	public static Archive<WebArchive> createDeployment() {
		return ShrinkWrap.create(WebArchive.class)
				.addPackage(CurrencyRepository.class.getPackage())
				.addPackage(CurrencyService.class.getPackage())
				.addPackage(MoneyChangeController.class.getPackage())
				.addAsManifestResource("beans.xml")
				.addAsResource("test-persistence.xml", "META-INF/persistence.xml");
	}


	@Inject
	private CurrencyService currencyService;

	@Test
	public void testChangeCurrencySuccess() {
		long amount = 35_200;
		Map<Integer, Long> expected = new TreeMap<>();
		expected.put(10_000, 3L);
		expected.put(5_000, 1L);
		expected.put(200, 1L);

		Map<Integer, Long> change = currencyService.changeCurrency(Currency.HUF_AFTER2008, amount);

		assertEquals(expected, change);
	}

	@Test
	public void testChangeCurrencyFailure() {
		long amount = 0;
		Map<Integer, Long> change = currencyService.changeCurrency(Currency.HUF_AFTER2008, amount);
		Assert.assertTrue(change.isEmpty());
	}
}
