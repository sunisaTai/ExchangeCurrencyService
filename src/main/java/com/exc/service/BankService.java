package com.exc.service;

import com.exc.domain.Bank;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created by Sunisa on 9/2/2559.
 */
public interface BankService {
	void saveBanks(Long id,String name,String code,String image);
	// void editBanks(Long id,String name,String code,String image);
}
