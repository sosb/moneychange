package controller;


import model.Denomination;
import service.Currency;
import service.CurrencyService;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.Map;


@ManagedBean
@ViewScoped
public class MoneyChangeController implements Serializable{

    @Inject
    private CurrencyService cs;

    private int amount;

    private Map<Integer, Long> result;

    private Currency currency;

    private List<Denomination> data;


    public void change(ActionEvent event){
        this.result = cs.changeCurrency(currency, amount);
    }

    public void actualStateOfData(ActionEvent event){
        this.data = cs.getActualStateOfData(currency);
    }



    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Map<Integer, Long> getResult() {
        return result;
    }

    public void setResult(Map<Integer, Long> result) {
        this.result = result;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Currency[] getCurrencies() {
        return Currency.values();
    }

    public List<Denomination> getData() {
        return data;
    }

}
