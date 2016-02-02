package com.exc;
import com.exc.domain.Bank;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/banks")
public class BankController_Custom_Controller {
    @RequestMapping(value = "/view", params = "bank",produces = "text/html")
    public String bank(Model uiModel) {
        return "banks/bank";
    }
}
