package model;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CurrencyRepository {

    @PersistenceContext
    private EntityManager entityManager;


}
