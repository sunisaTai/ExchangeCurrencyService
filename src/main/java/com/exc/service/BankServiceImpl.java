package com.exc.service;

import com.exc.domain.Bank;
import com.exc.repository.BankRepository;
import com.exc.repository.ExchangeCurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created by Sunisa on 9/2/2559.
 */
@Service
public class BankServiceImpl implements BankService{

    @Autowired
    BankRepository bankRepository;

    @Override
    public void saveBanks(Long id,String name,String code,String image){
        bankRepository.saveBanks(id,name,code,image);
    }

    // @Override
    // public void editBanks(Long id,String name,String code,String image){
    //     bankRepository.editBanks(id,name,code,image);
    // }

}
