package com.exc.controller;

import com.exc.domain.ExchangeCurrency;
import com.exc.service.ExchangeCurrencyService;
import flexjson.JSONSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/exchangeCurrencys")
public class ExchangeCurrencyController_Custom_Controller {

    @Autowired
    ExchangeCurrencyService exchangeCurrencyService;

    @RequestMapping(value = "/findAll",method = RequestMethod.GET,headers = "Accept=application/json")
    public ResponseEntity<String> findAll(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<ExchangeCurrency> result = exchangeCurrencyService.findAll();
        return new ResponseEntity<String>((new JSONSerializer().exclude("*.class")
                .include("id")
                .include("exchange_Rates")
                .include("type_code")
                .include("type_name")
                .include("bank.id")
                .include("bank.bank_Name")
                .include("currency.currency_Name")
                .include("currency.symbol")
                .include("currency.id")
                .exclude("*")
                .deepSerialize(result)),headers, HttpStatus.OK);
    }

}
