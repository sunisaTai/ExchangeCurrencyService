package com.exc.service;

import com.exc.domain.ExchangeCurrency;
import com.exc.repository.ExchangeCurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Korrakote on 2/2/2559.
 */
@Service
public class ExchangeCurrencyServiceImpl implements ExchangeCurrencyService{

    @Autowired
    ExchangeCurrencyRepository exchangeCurrencyRepository;

    @Override
    public List<ExchangeCurrency> findAll(){
        return exchangeCurrencyRepository.findAll();
    }
}
