package com.exc;
import com.exc.domain.ExchangeCurrency;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebJson(jsonObject = ExchangeCurrency.class)
@Controller
@RequestMapping("/exchangecurrencys")
public class ExchangeCurrencyController {
}
