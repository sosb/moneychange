package model;

import moneychange.Currency;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

import static javax.transaction.Transactional.TxType.REQUIRED;

public class CurrencyRepository {

    @PersistenceContext(unitName = "moneyChange")
    private EntityManager em;



    public List<Denomination> getAllDenomination(Currency currency){
        TypedQuery<Denomination> query = em.createQuery(
                "SELECT d " +
                        "FROM Denomination d " +
                        "WHERE d.currency = " + currency.getName() + " AND d.quantity <> 0", Denomination.class);
        return query.getResultList();
    }

    @Transactional(REQUIRED)
    public void updateDenominationQuantity(Integer denomination, Currency currency){
        em.createQuery(
                "UPDATE Denomination as d " +
                        "SET d.quantity = d.quantity - 1 " +
                        "WHERE d.denominationValue = " + denomination +" AND d.currency = " + currency.getName(), Denomination.class);
    }

}
