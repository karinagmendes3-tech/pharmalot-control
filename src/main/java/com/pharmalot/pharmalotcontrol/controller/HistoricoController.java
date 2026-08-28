package com.pharmalot.pharmalotcontrol.controller;

import com.pharmalot.pharmalotcontrol.model.HistoricoProducao;
import com.pharmalot.pharmalotcontrol.service.ProducaoService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
public class HistoricoController {

    private final ProducaoService producaoService;

    public HistoricoController(ProducaoService producaoService) {
        this.producaoService = producaoService;
    }


    /* =====================================
       EXIBIR HISTÓRICO
    ===================================== */

    @GetMapping("/historico")
    public String historico(Model model) {

        List<HistoricoProducao> registros =
                producaoService.buscarTodoHistorico();

        model.addAttribute(
                "registros",
                registros
        );

        return "historico";
    }


    /* =====================================
       SALVAR JUSTIFICATIVA DO SUPERVISOR
    ===================================== */

    @PostMapping("/historico/justificar")
    public String salvarJustificativa(
            @RequestParam Long historicoId,
            @RequestParam String justificativa,
            Principal principal,
            RedirectAttributes redirectAttributes) {

        /*
         * Evita justificativa vazia.
         */
        if (justificativa == null ||
                justificativa.trim().isEmpty()) {

            redirectAttributes.addFlashAttribute(
                    "erro",
                    "Informe a justificativa do supervisor."
            );

            return "redirect:/historico";
        }


        /*
         * Identifica automaticamente o usuário
         * autenticado no sistema.
         */
        String supervisor = "Supervisor";

        if (principal != null) {
            supervisor = principal.getName();
        }


        try {

            producaoService.salvarJustificativaSupervisor(
                    historicoId,
                    justificativa.trim(),
                    supervisor
            );

            redirectAttributes.addFlashAttribute(
                    "sucesso",
                    "Justificativa do supervisor salva com sucesso."
            );

        } catch (Exception e) {

            redirectAttributes.addFlashAttribute(
                    "erro",
                    e.getMessage()
            );
        }

        return "redirect:/historico";
    }
}