package com.exc;
import com.exc.domain.ExchangeCurrency;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exchangeCurrencys")
public class ExchangeCurrencyController_Custom_Controller {
    @RequestMapping(value = "/view", params = "exchangeCurrency",produces = "text/html")
    public String exchangeCurrency(Model uiModel) {
        return "exchangeCurrencys/exchangeCurrency";
    }
}
