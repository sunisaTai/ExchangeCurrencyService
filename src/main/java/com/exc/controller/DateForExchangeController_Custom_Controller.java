package com.exc;
import com.exc.domain.DateForExchange;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dateForExchanges")
public class DateForExchangeController_Custom_Controller {
    @RequestMapping(value = "/view", params = "dateForExchange",produces = "text/html")
    public String dateForExchange(Model uiModel) {
        return "dateForExchanges/dateForExchange";
    }
}
