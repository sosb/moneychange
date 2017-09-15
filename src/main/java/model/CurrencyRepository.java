package model;

import service.Currency;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class CurrencyRepository {

	@PersistenceContext(unitName = "moneyChange")
	private EntityManager em;

	public List<Denomination> getAllDenomination(Currency currency) {
		TypedQuery<Denomination> query = em.createQuery("SELECT d " +
				"FROM Denomination d " +
				"WHERE d.currency = " + currency.ordinal() + " AND d.quantity <> 0", Denomination.class);
		return query.getResultList();
	}

	public void updateDenominationQuantity(Integer denomination, Currency currency) {
		em.createQuery(
				"UPDATE Denomination as d " +
						"SET d.quantity = d.quantity - 1 " +
						"WHERE d.denominationValue = " + denomination + " AND d.currency = " + currency.ordinal())
				.executeUpdate();

	}

	public List<Denomination> getActualStateOfData(Currency currency) {
		TypedQuery<Denomination> query = em.createQuery("SELECT d FROM Denomination d WHERE " +
				"d.currency = " + currency.ordinal(), Denomination.class);
		return query.getResultList();
	}

}
