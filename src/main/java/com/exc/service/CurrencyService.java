package com.exc.service;

import com.exc.domain.Currency;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created by Sunisa on 9/2/2559.
 */
public interface CurrencyService {
	void saveCurrencys(Long id,String name,String code,String symbol,String image);
}
