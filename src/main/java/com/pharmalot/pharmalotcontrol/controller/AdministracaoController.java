package com.pharmalot.pharmalotcontrol.controller;

import com.pharmalot.pharmalotcontrol.service.OpBaseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class AdministracaoController {

    private final OpBaseService opBaseService;

    public AdministracaoController(OpBaseService opBaseService) {
        this.opBaseService = opBaseService;
    }

    @GetMapping("/administracao")
    public String administracao() {
        return "administracao";
    }

    @PostMapping("/administracao/importar")
    public String importarBase(
            @RequestParam("arquivo") MultipartFile arquivo,
            Model model) {

        if (arquivo.isEmpty()) {
            model.addAttribute(
                    "erro",
                    "Selecione uma planilha para importar."
            );

            return "administracao";
        }

        try {

            int quantidade = opBaseService.importarPlanilha(arquivo);

            model.addAttribute(
                    "sucesso",
                    quantidade + " registro(s) importado(s) com sucesso."
            );

        } catch (Exception e) {

            model.addAttribute(
                    "erro",
                    "Não foi possível importar a planilha: " + e.getMessage()
            );
        }

        return "administracao";
    }
}