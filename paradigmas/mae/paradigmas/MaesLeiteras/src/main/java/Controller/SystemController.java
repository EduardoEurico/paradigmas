package Controller;

import Model.Mae;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SystemController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String menu() {
        return "menu";
    }

    @RequestMapping(value = "/Procurar", method = RequestMethod.GET)
    public String procurar(Mae model) {

        List<Mae> pessoas = new ArrayList<>();
        pessoas.add(new Pessoa("João", "Silva", 30, "Rua A", "123456789"));
        pessoas.add(new Pessoa("Maria", "Oliveira", 25, "Rua B", "987654321"));

        model.addAttribute("pessoas", pessoas);
        return "procurar";
    }
}
