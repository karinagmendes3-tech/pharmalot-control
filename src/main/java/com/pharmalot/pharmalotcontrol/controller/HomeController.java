package com.pharmalot.pharmalotcontrol.controller;

import com.pharmalot.pharmalotcontrol.model.EtapaProducao;
import com.pharmalot.pharmalotcontrol.service.EtapaService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    private final EtapaService etapaService;

    public HomeController(EtapaService etapaService) {
        this.etapaService = etapaService;
    }


    /* =====================================
       HOME
    ===================================== */

    @GetMapping("/")
    public String home() {
        return "index";
    }


    /* =====================================
       EQUIPAMENTOS
    ===================================== */

    @GetMapping("/equipamentos")
    public String equipamentos() {
        return "equipamentos";
    }


    /* =====================================
       LOGBOOK
    ===================================== */

    @GetMapping("/logbook")
    public String logbook(Model model) {

        List<Map<String, String>> areas = List.of(

                Map.of(
                        "nome", "Pesagem",
                        "descricao", "Box de pesagem",
                        "url", "pesagem"
                ),

                Map.of(
                        "nome", "Manipulação",
                        "descricao", "Mistura, granulação e preparação",
                        "url", "manipulacao"
                ),

                Map.of(
                        "nome", "Compressão",
                        "descricao", "Compressão de comprimidos",
                        "url", "compressao"
                ),

                Map.of(
                        "nome", "Revestimento",
                        "descricao", "Revestimento de comprimidos",
                        "url", "revestimento"
                ),

                Map.of(
                        "nome", "Acondicionamento",
                        "descricao", "Embalagem e acondicionamento",
                        "url", "acondicionamento"
                )
        );

        model.addAttribute("areas", areas);

        return "logbook";
    }


    /* =====================================
       EQUIPAMENTOS POR ÁREA
    ===================================== */

    @GetMapping("/logbook/{area}")
    public String equipamentosPorArea(
            @PathVariable String area,
            Model model) {

        model.addAttribute("area", area);

        if (area.equals("manipulacao")) {

            model.addAttribute(
                    "equipamentos",
                    List.of(

                            Map.of(
                                    "nome", "Diosna 02",
                                    "tag", "HOM504001(3)",
                                    "status", "Ativo"
                            ),

                            Map.of(
                                    "nome", "Misturador de Bins",
                                    "tag", "MIS504007",
                                    "status", "Ativo"
                            )
                    )
            );

        } else {

            model.addAttribute(
                    "equipamentos",
                    List.of()
            );
        }

        return "logbook-equipamentos";
    }


    /* =====================================
       HISTÓRICO
    ===================================== */

    /* =====================================
       RELATÓRIOS
    ===================================== */

    @GetMapping("/relatorios")
    public String relatorios() {
        return "relatorios";
    }


    /* =====================================
       BUSCAR OP ANTIGA
    ===================================== */

    @GetMapping("/buscar")
    public String buscarOp(
            @RequestParam String op,
            Model model) {

        List<EtapaProducao> etapas =
                etapaService.buscarPorOp(op);

        model.addAttribute("etapas", etapas);
        model.addAttribute("op", op);

        return "index";
    }


    /* =====================================
       LOGIN
    ===================================== */

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}