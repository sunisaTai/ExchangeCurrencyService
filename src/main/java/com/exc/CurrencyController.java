package com.exc;
import com.exc.domain.Currency;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebJson(jsonObject = Currency.class)
@Controller
@RequestMapping("/currencys")
public class CurrencyController {
}
