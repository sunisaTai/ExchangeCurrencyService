package com.exc.repository;

import com.exc.domain.Bank;
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
public class BankRepository {

    @PersistenceContext
    private EntityManager em;

	private static Logger LOGGER = LoggerFactory.getLogger(BankRepository.class);

    public void saveBanks(Long id,String name,String code,String image) {
        if(id == null){
            Bank bank = new Bank();
            try{
                bank.setBank_Name(name);
                bank.setCode(code);
                bank.setImage_name(image);
                bank.persist();

            }catch(Exception e){
                LOGGER.error("Error : {}", e.getMessage());
                throw new RuntimeException(e);
            }
        }else {
            Bank bank = null;
            try{
                bank = findBankById(id);
                bank.setBank_Name(name);
                bank.setCode(code);
                bank.setImage_name(image);
                bank.merge();

            }catch(Exception e){
                LOGGER.error("Error : {}", e.getMessage());
                throw new RuntimeException(e);
            }
        }
 

    }

    public Bank findBankById(Long idBank){
        Criteria criteria = ((Session) em.getDelegate()).createCriteria(Bank.class);
        criteria.add(Restrictions.eq("id",idBank));
        Bank result = (Bank)criteria.uniqueResult();
        return result;
    }
    public Bank findBankByNameBank(String nameBank){
        Criteria criteria = ((Session) em.getDelegate()).createCriteria(Bank.class);
        criteria.add(Restrictions.eq("bank_Name",nameBank));
        Bank result = (Bank)criteria.uniqueResult();
        return result;
    }

    public List<Bank> findBankByDate(Date date){
        Criteria criteria = ((Session) em.getDelegate()).createCriteria(Bank.class);
        criteria.createCriteria("exchangeCurrency","exchangeCurrency",Criteria.LEFT_JOIN);
        criteria.createCriteria("exchangeCurrency.dateForExchange","dateForExchange",Criteria.LEFT_JOIN);
        criteria.createAlias("exchangeCurrency.currency","currency");
        criteria.add(Restrictions.eq("dateForExchange.datePerExchange",date));
        criteria.addOrder(Order.asc("currency.code"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }
}
