package com.exc.repository;

import com.exc.domain.ExchangeCurrency;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

/**
 * Created by korrakote on 2/2/2559.
 */
@Repository
@Transactional
public class ExchangeCurrencyRepository {

    @PersistenceContext
    private EntityManager em;

    public List<ExchangeCurrency> findExchangeCurrencyByDate(Date date){
        Criteria criteria = ((Session) em.getDelegate()).createCriteria(ExchangeCurrency.class);
        criteria.createCriteria("currency","currency");
        criteria.createCriteria("bank","bank");
        criteria.createCriteria("dateForExchange","dateForExchange");
        criteria.add(Restrictions.and(Restrictions.eq("dateForExchange.datePerExchange",date)));
        criteria.addOrder(Order.asc("bank.bank_Name"));
        criteria.addOrder(Order.asc("currency.currency_Name"));
        return criteria.list();
    }
}
