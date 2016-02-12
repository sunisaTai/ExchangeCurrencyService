package com.exc.service;

import com.exc.domain.Currency;
import com.exc.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created by Sunisa on 9/2/2559.
 */
@Service
public class CurrencyServiceImpl implements CurrencyService{

    @Autowired
    CurrencyRepository currencyRepository;

    @Override
    public void saveCurrencys(Long id,String name,String code,String symbol,String image){
        currencyRepository.saveCurrencys(id,name,code,symbol,image);
    }

}
