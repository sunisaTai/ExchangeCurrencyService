package com.exc;
import com.exc.domain.Bank;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebJson(jsonObject = Bank.class)
@Controller
@RequestMapping("/banks")
public class BankController {

}
