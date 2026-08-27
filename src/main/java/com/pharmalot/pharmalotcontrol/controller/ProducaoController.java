package com.pharmalot.pharmalotcontrol.controller;

import com.pharmalot.pharmalotcontrol.model.OrdemProducao;
import com.pharmalot.pharmalotcontrol.service.ProducaoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class ProducaoController {

    private final ProducaoService producaoService;

    public ProducaoController(ProducaoService producaoService) {
        this.producaoService = producaoService;
    }

    @GetMapping("/producao")
    public String producao() {
        return "producao";
    }

    @GetMapping("/producao/buscar")
    public String buscarOp(@RequestParam String numeroOp, Model model) {

        Optional<OrdemProducao> ordem = producaoService.buscarPorOp(numeroOp);

        if (ordem.isPresent()) {
            model.addAttribute("ordem", ordem.get());
            model.addAttribute("encontrada", true);
        } else {
            OrdemProducao novaOrdem = new OrdemProducao();
            novaOrdem.setNumeroOp(numeroOp);

            model.addAttribute("ordem", novaOrdem);
            model.addAttribute("encontrada", false);
        }

        return "producao";
    }
}