package com.eventoapp.eventoapp.Controller;

import org.springframework.aot.hint.annotation.Reflective;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
