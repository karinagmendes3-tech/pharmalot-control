package com.pharmalot.pharmalotcontrol.controller;

import com.pharmalot.pharmalotcontrol.model.EtapaProducao;
import com.pharmalot.pharmalotcontrol.service.EtapaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    private final EtapaService etapaService;

    public HomeController(EtapaService etapaService) {
        this.etapaService = etapaService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/buscar")
    public String buscarOp(@RequestParam String op, Model model) {
        List<EtapaProducao> etapas = etapaService.buscarPorOp(op);
        model.addAttribute("etapas", etapas);
        model.addAttribute("op", op);
        return "index";
    }
}}