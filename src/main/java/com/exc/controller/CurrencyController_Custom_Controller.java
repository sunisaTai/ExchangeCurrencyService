package com.exc.controller;

import com.exc.domain.Currency;
import com.exc.service.CurrencyService;
import com.exc.repository.CurrencyRepository;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import java.util.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.util.Map;
import java.util.Map.Entry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/currencys")
public class CurrencyController_Custom_Controller {

	private static Logger LOGGER = LoggerFactory.getLogger(CurrencyController_Custom_Controller.class);

	@Autowired
    private CurrencyService currencyService;

    @Autowired
    CurrencyRepository currencyRepository;

    @RequestMapping(value = "/findAllCurrency",method = RequestMethod.GET,headers = "Accept=application/json")
    public ResponseEntity<String> findAllCurrency() throws Exception{
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        try {
            List<Currency> result = Currency.findAllCurrencys();
            if(result == null){
                return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<String>((new JSONSerializer().include("id").include("currency_Name").include("symbol").include("code").include("image_name").exclude("*").deepSerialize(result)),headers, HttpStatus.OK);
        }catch (Exception e) {
            LOGGER.error("Error : {}", e);
            return new ResponseEntity<String>("{\"ERROR\":"+e.getMessage()+"\"}", headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/findCurrencyByIdCurrency",method = RequestMethod.GET,headers = "Accept=application/json")
    public ResponseEntity<String> findCurrencyByIdCurrency(@RequestParam(value = "id", required = false)Long id) throws Exception{
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        try {
            Currency result = currencyRepository.findCurrencyById(id);
            if(result == null){
                return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<String>((new JSONSerializer().include("id").include("currency_Name").include("symbol").include("code").include("image_name").exclude("*").deepSerialize(result)),headers, HttpStatus.OK);
        }catch (Exception e) {
            LOGGER.error("Error : {}", e);
            return new ResponseEntity<String>("{\"ERROR\":"+e.getMessage()+"\"}", headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

    @RequestMapping(value = "/saveCurrencys", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> saveCurrencys(@RequestParam(value = "id", required = false) Long id,
                                            @RequestParam(value = "currency_Name", required = false) String currency_Name,
    										@RequestParam(value = "symbol", required = false) String symbol,
    										@RequestParam(value = "code", required = false) String code,
    										@RequestParam(value = "image_name", required = false) String image_name) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        String nameCurrency = null;
        String codeCurrency = null;
        String symbolCurrency = null;
        String imageCurrency = null;
        Long idCurrency = null;
        try {
            nameCurrency = (currency_Name == "" || currency_Name == null ? "" : currency_Name);
            codeCurrency = (code == "" || code == null ? "" : code);
            symbolCurrency = (symbol == "" || symbol == null ? "" : symbol);
            imageCurrency = (image_name == "" || image_name == null ? "" : image_name);
            idCurrency = (id == null ? null : id);
            currencyService.saveCurrencys(idCurrency,nameCurrency,codeCurrency,symbolCurrency,imageCurrency);

            return new ResponseEntity<String>(headers, HttpStatus.CREATED);
        } catch (Exception e) {
            LOGGER.error("Error : {}", e);
            return new ResponseEntity<String>("{\"ERROR\":"+e.getMessage()+"\"}", headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value="/deleteCurrency/{id}",method = RequestMethod.DELETE,headers="Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> deleteCurrency(@PathVariable("id")Long id){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        try{
            Currency currency = currencyRepository.findCurrencyById(id);
            if(currency == null){
                return new ResponseEntity<String>(headers,HttpStatus.NOT_FOUND);
            }
            else{

                currency.remove();
                return new ResponseEntity<String>(headers,HttpStatus.OK);
            }
        }catch(Exception e){
            return new ResponseEntity<String>("{\"ERROR\":"+e.getMessage()+"\"}", headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
