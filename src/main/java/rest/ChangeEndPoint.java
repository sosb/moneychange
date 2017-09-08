package rest;

import model.Denomination;
import service.Currency;
import service.CurrencyService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

@Path("/change")
public class ChangeEndPoint {

	@Inject
	private CurrencyService cs;

	@GET
	@Path("{currency}")//http://localhost:8080/service/rest/change/HUF_AFTER2008
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStockInfo(@PathParam("currency") String ordinalOfTheCurrency) {
		Currency selectedCurrency = Currency.valueOf(ordinalOfTheCurrency);
		List<Denomination> stockInfo = cs.getAllDenomination(selectedCurrency);
		if (stockInfo == null || stockInfo.size() == 0) {
			return Response.status(500).build();
		}
		return Response.ok(stockInfo).build();
	}

	@POST
	@Path("/money")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response change(@FormParam("amount") int amount,
						   @FormParam("currency") Currency currency) {
		Map<Integer, Long> result = cs.changeCurrency(currency, amount);
		if (result.size() == 0) {
			return Response.status(500).build();
		}
		return Response.status(200).entity(result).build();
	}
}
