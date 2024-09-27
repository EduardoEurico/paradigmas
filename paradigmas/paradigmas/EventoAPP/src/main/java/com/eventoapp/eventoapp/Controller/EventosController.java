package com.eventoapp.eventoapp.Controller;

import com.eventoapp.eventoapp.models.Evento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.eventoapp.eventoapp.repository.EventoRepository;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EventosController {

    @Autowired
    private EventoRepository er;

    @RequestMapping(value = "/cadastrarEvento", method = RequestMethod.GET)
    public String form() {return "formEvento";}

    @RequestMapping(value = "/cadastrarEvento", method = RequestMethod.POST)
    public String form(Evento evento) {
        er.save(evento);
        return "redirect:/cadastrarEvento";
    }
    @RequestMapping("/eventos")
    public ModelAndView listaEventos(){
        ModelAndView modelAndView = new ModelAndView("index");
        Iterable<Evento> eventos = er.findAll();

        modelAndView.addObject("eventos", eventos);
        return modelAndView;
    }
}
