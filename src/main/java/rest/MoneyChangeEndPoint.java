package rest;


import moneychange.Currency;
import moneychange.CurrencyService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



@Path("/change")
public class MoneyChangeEndPoint{

    private CurrencyService currencyService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllData(){

        return Response.ok(currencyService.changeCurrency(Currency.HUF_AFTER2008, 60_000)).build();

    }

    @Inject
    public void setCurrencyService(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

}
