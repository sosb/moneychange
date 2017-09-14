

import controller.MoneyChangeController;
import model.CurrencyRepository;
import model.Denomination;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.testng.annotations.Test;
import service.Currency;
import service.CurrencyService;

import javax.inject.Inject;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class RepositoryTest extends Arquillian {

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
	private CurrencyRepository repository;

	@Test
	public void getAllDenomination() {
		List<Denomination> denominations = repository.getAllDenomination(Currency.HUF_AFTER2008);

		boolean allMatch = denominations.stream()
				.allMatch(d -> d.getCurrency() == Currency.HUF_AFTER2008);

		assertTrue(allMatch);
	}
}
