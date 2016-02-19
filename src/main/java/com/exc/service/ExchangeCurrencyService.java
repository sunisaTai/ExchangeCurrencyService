package com.exc.service;

import com.exc.domain.ExchangeCurrency;

import java.util.Date;
import java.util.List;

/**
 * Created by korrakote on 2/2/2559.
 */
public interface ExchangeCurrencyService {
    List<ExchangeCurrency> findExchangeCurrencyByDate(Date date);
    void saveExchangeCurrency(String nameBank,String sell,String buy,Long idCurrency);
}
