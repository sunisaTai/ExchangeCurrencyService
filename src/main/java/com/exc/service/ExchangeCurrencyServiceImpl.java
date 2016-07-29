package com.exc.service;

import com.exc.domain.ExchangeCurrency;
import com.exc.repository.ExchangeCurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Korrakote on 2/2/2559.
 */
@Service
public class ExchangeCurrencyServiceImpl implements ExchangeCurrencyService{

    @Autowired
    ExchangeCurrencyRepository exchangeCurrencyRepository;

    @Override
    public List<ExchangeCurrency> findExchangeCurrencyByDate(Date date){
        return exchangeCurrencyRepository.findExchangeCurrencyByDate(date);
    }
    @Override
    public void saveExchangeCurrency(String nameBank,String sell,String buy,Long idCurrency,Long idExchangeCurrency){
        exchangeCurrencyRepository.saveExchangeCurrency(nameBank,sell,buy,idCurrency,idExchangeCurrency);
    }
    @Override
    public List<ExchangeCurrency> findExchangeCurrencyByNameBank(String nameBank){
        return exchangeCurrencyRepository.findExchangeCurrencyByNameBank(nameBank);
    }
    @Override
    public Float calculateBuyingPrice(Date date,Float money,Long bank,Long currency){
        List<ExchangeCurrency> exchangeCurrencyList = exchangeCurrencyRepository.findExchangeCurrencyByNameIdAndCurrencyIdAndDate(bank,currency,date);
        Float selling = 0f;
        Float result = 0f;
        if(exchangeCurrencyList.size() != 0){
            for (ExchangeCurrency e:exchangeCurrencyList){
                selling = e.getSell_rate();
                break;
            }
            result = money/selling;
        }
        return result;
    }
    @Override
    public Float calculateSellingPrice(Date date,Float money,Long bank,Long currency){
        List<ExchangeCurrency> exchangeCurrencyList = exchangeCurrencyRepository.findExchangeCurrencyByNameIdAndCurrencyIdAndDate(bank,currency,date);
        Float buying = 0f;
        Float result = 0f;
        if(exchangeCurrencyList.size() != 0){
            for (ExchangeCurrency e:exchangeCurrencyList){
                buying = e.getBuy_rate();
                break;
            }
            result = money*buying;
        }
        return result;
    }
}
