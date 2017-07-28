package model;

import com.sun.beans.editors.IntegerEditor;
import moneychange.Currency;

import javax.persistence.*;

@Entity
public class Denomination {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated
    private Currency currency;

    @Column(name = "denomination_value")
    private Integer denominationValue;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "denomination_amount")
    private Long denominationAmount;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Integer getDenominationValue() {
        return denominationValue;
    }

    public void setDenominationValue(Integer denominationValue) {
        this.denominationValue = denominationValue;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getDenominationAmount() {
        return denominationAmount;
    }

    public void setDenominationAmount(Long denominationAmount) {
        this.denominationAmount = denominationAmount;
    }
}
