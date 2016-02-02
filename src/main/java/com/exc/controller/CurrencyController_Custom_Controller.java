package com.exc;
import com.exc.domain.Currency;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/currencys")
public class CurrencyController_Custom_Controller {
    @RequestMapping(value = "/view", params = "currency",produces = "text/html")
    public String currency(Model uiModel) {
        return "currencys/currency";
    }
}
