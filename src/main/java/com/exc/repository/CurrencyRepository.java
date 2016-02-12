package com.exc.repository;

import com.exc.domain.Currency;
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
public class CurrencyRepository {

    @PersistenceContext
    private EntityManager em;

	private static Logger LOGGER = LoggerFactory.getLogger(CurrencyRepository.class);

    public void saveCurrencys(Long id,String name,String code,String symbol,String image) {
        if(id == null){
            Currency currency = new Currency();
            try{
                currency.setCurrency_Name(name);
                currency.setCode(code);
                currency.setSymbol(symbol);
                currency.setImage_name(image);
                currency.persist();

            }catch(Exception e){
                LOGGER.error("Error : {}", e.getMessage());
                throw new RuntimeException(e);
            }
        }else {
            Currency currency = null;
            try{
                currency = findCurrencyById(id);
                currency.setCurrency_Name(name);
                currency.setCode(code);
                currency.setSymbol(symbol);
                currency.setImage_name(image);
                currency.merge();

            }catch(Exception e){
                LOGGER.error("Error : {}", e.getMessage());
                throw new RuntimeException(e);
            }
        }
 

    }

    public Currency findCurrencyById(Long idCurrency){
        Criteria criteria = ((Session) em.getDelegate()).createCriteria(Currency.class);
                criteria.add(Restrictions.eq("id",idCurrency));
        Currency result = (Currency)criteria.uniqueResult();
        return result;
    }
}
