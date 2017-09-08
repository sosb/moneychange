package model;

import service.Currency;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;

@Entity
public class Denomination {

	@Id
	@GeneratedValue
	private Long id;

	@Enumerated
	private Currency currency;

	@Column(name = "denomination_value")
	private Integer denominationValue;

	@Min(value = 0)
	@Column(name = "quantity")
	private Long quantity;

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
}
