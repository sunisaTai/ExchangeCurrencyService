package com.exc;
import com.exc.domain.DateForExchange;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebJson(jsonObject = DateForExchange.class)
@Controller
@RequestMapping("/dateforexchanges")
public class DateForExchangeController {
}
