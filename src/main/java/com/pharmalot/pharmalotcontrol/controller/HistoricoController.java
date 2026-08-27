package com.pharmalot.pharmalotcontrol.controller;

import com.pharmalot.pharmalotcontrol.model.HistoricoProducao;
import com.pharmalot.pharmalotcontrol.service.ProducaoService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HistoricoController {

    private final ProducaoService producaoService;

    public HistoricoController(ProducaoService producaoService) {
        this.producaoService = producaoService;
    }

    @GetMapping("/historico")
    public String historico(Model model) {

        List<HistoricoProducao> registros =
                producaoService.buscarTodoHistorico();

        model.addAttribute("registros", registros);

        return "historico";
    }
}