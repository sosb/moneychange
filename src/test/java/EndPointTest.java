import controller.MoneyChangeController;
import model.CurrencyRepository;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.testng.annotations.Test;
import rest.ChangeEndPoint;
import service.CurrencyService;

import java.net.URL;

import static com.jayway.restassured.RestAssured.given;

public class EndPointTest extends Arquillian {

	private static final String CURRENCY_HUF_AFTER_2008 = "HUF_AFTER2008";

	@Deployment(testable = false)
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class)
				.addPackage(ChangeEndPoint.class.getPackage())
				.addPackage(CurrencyService.class.getPackage())
				.addPackage(CurrencyRepository.class.getPackage())
				.addPackage(MoneyChangeController.class.getPackage())
				.addAsManifestResource("beans.xml")
				.addAsResource("test-persistence.xml", "META-INF/persistence.xml");
	}

	@ArquillianResource
	private URL appUrl;

	@Test
	public void testGetMethod() {
		given()
				.log().all()
				.get(appUrl + "rest/change/" + CURRENCY_HUF_AFTER_2008)
				.then()
				.log().all()
				.assertThat().statusCode(200)
				.assertThat().contentType("application/json");
	}

	@Test
	public void testPostMethod() {
		given()
				.log().all()
				.formParam("amount", 35200).formParam("currency", CURRENCY_HUF_AFTER_2008)
				.post(appUrl + "rest/change/money")
				.then()
				.log().all()
				.assertThat().statusCode(200);
	}
}
