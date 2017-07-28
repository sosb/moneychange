package rest;


import moneychange.CurrencyService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/change")
public class MoneyChangeEndPoint {

    private CurrencyService currencyService;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getThis(){

        return Response.ok(currencyService.changeCurrency(60_000)).build();

    }

    @Inject
    public void setCurrencyService(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }
}
