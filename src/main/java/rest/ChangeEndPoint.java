package rest;

import service.Currency;
import service.CurrencyService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/change")
public class ChangeEndPoint {

    @Inject
    private CurrencyService cs;

    @GET
    @Path("{currency}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStockInfo(@PathParam("currency") String currency){
        System.out.println(currency);
        return Response.ok(cs.getActualStateOfData(Currency.values()[0])).build();
    }

    @POST
    @Path("/i")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response change(@FormParam("amount") int amount,
                           @FormParam("currency") Currency currency){

        return Response.ok(cs.changeCurrency(currency, amount)).build();
    }
}
