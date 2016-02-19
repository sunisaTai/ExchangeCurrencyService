package com.exc.repository;

import com.exc.domain.DateForExchange;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by Sunisa on 9/2/2559.
 */
@Repository
@Transactional
public class DateForExchangeRepository {

    @PersistenceContext
    private EntityManager em;

    public DateForExchange findDateForExchangeByDate(Date date){
        Criteria criteria = ((Session) em.getDelegate()).createCriteria(DateForExchange.class);
                criteria.add(Restrictions.eq("datePerExchange",date));
        DateForExchange result = (DateForExchange)criteria.uniqueResult();
        return result;
    }
}
