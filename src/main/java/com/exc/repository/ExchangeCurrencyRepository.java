package com.exc.repository;

import com.exc.domain.ExchangeCurrency;
import com.exc.domain.Bank;
import com.exc.domain.Currency;
import com.exc.domain.DateForExchange;
import com.exc.repository.BankRepository;
import com.exc.repository.CurrencyRepository;
import com.exc.repository.DateForExchangeRepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by korrakote on 2/2/2559.
 */
@Repository
@Transactional
public class ExchangeCurrencyRepository {

    protected static Logger LOGGER = LoggerFactory.getLogger(ExchangeCurrencyRepository.class);

    @PersistenceContext
    private EntityManager em;

    @Autowired
    BankRepository bankRepository;

    @Autowired
    CurrencyRepository currencyRepository;

    @Autowired
    DateForExchangeRepository dateForExchangeRepository;

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
    //Created By Sunisa 15-02-2016
    public void saveExchangeCurrency(String nameBank,String sell,String buy,Long idCurrency) {

        Bank bank = new Bank();
        Currency currency = new Currency();
        DateForExchange dateForExchange = new DateForExchange();
        ExchangeCurrency exchangeCurrency = new ExchangeCurrency();
        try{
            Date date = new Date();
            Date today = removeTime(date);
            dateForExchange = dateForExchangeRepository.findDateForExchangeByDate(today);
            if (dateForExchange == null) {
                DateForExchange dateExchange = new DateForExchange();

                float priceSell = Float.parseFloat(sell);
                float priceBuy = Float.parseFloat(buy);
                bank = bankRepository.findBankByNameBank(nameBank);
                currency = currencyRepository.findCurrencyById(idCurrency);
                dateExchange.setDatePerExchange(today);
                dateExchange.persist();

                exchangeCurrency.setBank(bank);
                exchangeCurrency.setDateForExchange(dateExchange);
                exchangeCurrency.setCurrency(currency);
                exchangeCurrency.setBuy_rate(priceSell);
                exchangeCurrency.setSell_rate(priceBuy);
                exchangeCurrency.persist();
            }else{
                float priceSell = Float.parseFloat(sell);
                float priceBuy = Float.parseFloat(buy);
                bank = bankRepository.findBankByNameBank(nameBank);
                currency = currencyRepository.findCurrencyById(idCurrency);

                exchangeCurrency.setBank(bank);
                exchangeCurrency.setDateForExchange(dateForExchange);
                exchangeCurrency.setCurrency(currency);
                exchangeCurrency.setBuy_rate(priceSell);
                exchangeCurrency.setSell_rate(priceBuy);
                exchangeCurrency.persist();
            }
        }catch(Exception e){
            LOGGER.error("Error : {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
    //Created By Sunisa 15-02-2016
    public static Date removeTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    };
}
