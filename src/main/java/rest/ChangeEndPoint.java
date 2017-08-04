package rest;

import service.Currency;
import service.CurrencyService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

@Path("/change")
public class ChangeEndPoint {

    @Inject
    private CurrencyService cs;

    @GET
    @Path("{currency}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStockInfo(@PathParam("currency") int currency){
        Currency selectedCurrency = Currency.values()[currency];
        if(selectedCurrency == null){
            return Response.status(500).build();
        }
        return Response.ok(cs.getActualStateOfData(selectedCurrency)).build();
    }

    @POST
    @Path("/money")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response change(@FormParam("amount") int amount,
                           @FormParam("currency") Currency currency){

        Map<Integer, Long> result = cs.changeCurrency(currency, amount);

        if(result.size() == 0){
            return Response.status(500).build();
        }
        return Response.status(200).entity(result).build();
    }
}
