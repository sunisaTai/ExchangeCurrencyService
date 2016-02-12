package com.exc.controller;

import com.exc.domain.Bank;
import com.exc.service.BankService;
import com.exc.repository.BankRepository;
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
@RequestMapping("/banks")
public class BankController_Custom_Controller {

	private static Logger LOGGER = LoggerFactory.getLogger(BankController_Custom_Controller.class);

	@Autowired
    private BankService bankService;

    @Autowired
    BankRepository bankRepository;

	@RequestMapping(value = "/view", params = "bank",produces = "text/html")
    public String bank(Model uiModel) {
        return "banks/bank";
    }

    @RequestMapping(value = "/findAllBank",method = RequestMethod.GET,headers = "Accept=application/json")
    public ResponseEntity<String> findAllBank() throws Exception{
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        try {
            List<Bank> result = Bank.findAllBanks();
            if(result == null){
                return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<String>((new JSONSerializer().include("id").include("bank_Name").include("code").include("image_name").exclude("*").deepSerialize(result)),headers, HttpStatus.OK);
        }catch (Exception e) {
            LOGGER.error("Error : {}", e);
            return new ResponseEntity<String>("{\"ERROR\":"+e.getMessage()+"\"}", headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

    @RequestMapping(value = "/findBankByIdBank",method = RequestMethod.GET,headers = "Accept=application/json")
    public ResponseEntity<String> findBankByIdBank(@RequestParam(value = "id", required = false)Long id) throws Exception{
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        try {
            Bank result = bankRepository.findBankById(id);
            if(result == null){
                return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<String>((new JSONSerializer().include("id").include("bank_Name").include("code").include("image_name").exclude("*").deepSerialize(result)),headers, HttpStatus.OK);
        }catch (Exception e) {
            LOGGER.error("Error : {}", e);
            return new ResponseEntity<String>("{\"ERROR\":"+e.getMessage()+"\"}", headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

    @RequestMapping(value = "/saveBanks", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> saveBanks(@RequestParam(value = "id", required = false) Long id,
                                            @RequestParam(value = "name", required = false) String name,
    										@RequestParam(value = "code", required = false) String code,
    										@RequestParam(value = "image", required = false) String image) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        String nameBank = null;
        String codeBank = null;
        String imageBank = null;
        Long idBank = null;
        try {
            nameBank = (name == "" || name == null ? "" : name);
            codeBank = (code == "" || code == null ? "" : code);
            imageBank = (image == "" || image == null ? "" : image);
            idBank = (id == null ? null : id);
            bankService.saveBanks(idBank,nameBank,codeBank,imageBank);

            return new ResponseEntity<String>(headers, HttpStatus.CREATED);
        } catch (Exception e) {
            LOGGER.error("Error : {}", e);
            return new ResponseEntity<String>("{\"ERROR\":"+e.getMessage()+"\"}", headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // @RequestMapping(value = "/editBanks", method = RequestMethod.PUT, headers = "Accept=application/json")
    // public ResponseEntity<String> editBanks(@RequestParam(value = "id") Long id,
    //                                         @RequestParam(value = "name") String name,
    //                                         @RequestParam(value = "code") String code,
    //                                         @RequestParam(value = "image") String image) {
    //     HttpHeaders headers = new HttpHeaders();
    //     headers.add("Content-Type", "application/json");
    //     String nameBank = null;
    //     String codeBank = null;
    //     String imageBank = null;
    //     try {
    //         nameBank = (name == "" || name == null ? "" : name);
    //         codeBank = (code == "" || code == null ? "" : code);
    //         imageBank = (image == "" || image == null ? "" : image);
    //         bankService.editBanks(id,nameBank,codeBank,imageBank);

    //         return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    //     } catch (Exception e) {
    //         LOGGER.error("Error : {}", e);
    //         return new ResponseEntity<String>("{\"ERROR\":"+e.getMessage()+"\"}", headers, HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }

    @RequestMapping(value="/deleteBank/{id}",method = RequestMethod.DELETE,headers="Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> deleteBank(@PathVariable("id")Long id){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        try{
            Bank bank = bankRepository.findBankById(id);
            if(bank == null){
                return new ResponseEntity<String>(headers,HttpStatus.NOT_FOUND);
            }
            else{

                bank.remove();
                return new ResponseEntity<String>(headers,HttpStatus.OK);
            }
        }catch(Exception e){
            return new ResponseEntity<String>("{\"ERROR\":"+e.getMessage()+"\"}", headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
