package com.exc.controller;

import org.springframework.ui.Model;
import com.exc.domain.ExchangeCurrency;
import com.exc.service.ExchangeCurrencyService;
import flexjson.JSONSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/exchangeCurrencys")
public class ExchangeCurrencyController_Custom_Controller {

    protected static Logger LOGGER = LoggerFactory.getLogger(ExchangeCurrencyController_Custom_Controller.class);

    @Autowired
    ExchangeCurrencyService exchangeCurrencyService;

    @RequestMapping(value = "/findExchangeCurrencyByDate",method = RequestMethod.GET,headers = "Accept=application/json")
    public ResponseEntity<String> findExchangeCurrencyByDate(@RequestParam(value = "date", required = false)String str_date) throws Exception{
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy",Locale.US);
        Date date = format.parse(str_date);
        List<ExchangeCurrency> result = exchangeCurrencyService.findExchangeCurrencyByDate(date);
        return new ResponseEntity<String>((new JSONSerializer().exclude("*.class")
                .include("id")
                .include("buy_rate")
                .include("sell_rate")
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

    @RequestMapping(value = "/saveExchangeCurrency", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> saveExchangeCurrency(@RequestParam(value = "name_Bank", required = false) String name_Bank,
                                            @RequestParam(value = "currency", required = false) Long id_Currency,
                                            @RequestParam(value = "sell", required = false) String sell,
                                            @RequestParam(value = "buy", required = false) String buy,
                                            @RequestParam(value = "id", required = false) Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        String nameBank = null;
        Long idCurrency = null;
        Long idExchangeCurrency = null;
        String priceSell = null;
        String priceBuy = null;
        try {
            nameBank = (name_Bank == "" || name_Bank == null ? "" : name_Bank);
            priceSell = (sell == "" || sell == null ? "" : sell);
            priceBuy = (buy == "" || buy == null ? "" : buy);
            idCurrency = (id_Currency == null ? null : id_Currency);
            idExchangeCurrency = (id == null ? null : id);
            exchangeCurrencyService.saveExchangeCurrency(nameBank,priceSell,priceBuy,idCurrency,idExchangeCurrency);

            return new ResponseEntity<String>(headers, HttpStatus.CREATED);
        } catch (Exception e) {
            LOGGER.error("Error : {}", e);
            return new ResponseEntity<String>("{\"ERROR\":"+e.getMessage()+"\"}", headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/findExchangeCurrencyByNameBank", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<String> findExchangeCurrencyByNameBank(@RequestParam(value = "name", required = false) String name) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        String nameBank = null;
        try {
            nameBank = (name == null ? null : name);
            List<ExchangeCurrency> exchangeCurrency = exchangeCurrencyService.findExchangeCurrencyByNameBank(nameBank);

        return new ResponseEntity<String>((new JSONSerializer().exclude("*.class")
                .include("id")
                .include("buy_rate")
                .include("sell_rate")
                .include("type_code")
                .include("type_name")
                .include("bank.id")
                .include("bank.bank_Name")
                .include("currency.currency_Name")
                .include("currency.symbol")
                .include("currency.id")
                .exclude("*")
                .deepSerialize(exchangeCurrency)),headers, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Error : {}", e);
            return new ResponseEntity<String>("{\"ERROR\":"+e.getMessage()+"\"}", headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
