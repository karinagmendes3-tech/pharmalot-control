package com.pharmalot.pharmalotcontrol.controller;

import com.pharmalot.pharmalotcontrol.model.EtapaProducao;
import com.pharmalot.pharmalotcontrol.service.EtapaService;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.time.LocalDateTime;
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
       PRODUÇÃO
    ===================================== */

    /* =====================================
       SALVAR STATUS DO LOTE
    ===================================== */

    @PostMapping("/producao/salvar")
    public String salvarProducao(

            @RequestParam String op,

            @RequestParam String produto,

            @RequestParam Double quantidade,

            @RequestParam String etapa,

            @RequestParam String status,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime dataHora,

            @RequestParam(required = false)
            String observacoes,

            Principal principal) {

        EtapaProducao registro = new EtapaProducao();

        registro.setOp(op);

        registro.setProduto(produto);

        registro.setQuantidadeProduzida(quantidade);

        registro.setEtapa(etapa);

        registro.setStatus(status);

        registro.setObservacoes(observacoes);


        /* Se o usuário não informar data/hora,
           o sistema usa automaticamente o momento atual */
        if (dataHora == null) {
            registro.setDataHora(LocalDateTime.now());
        } else {
            registro.setDataHora(dataHora);
        }


        /* Salva automaticamente quem fez a alteração */
        if (principal != null) {
            registro.setUsuarioResponsavel(principal.getName());
        }


        etapaService.salvar(registro);

        return "redirect:/producao";
    }


    /* =====================================
       EQUIPAMENTOS
    ===================================== */

    @GetMapping("/equipamento")
    public String equipamento() {
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

    @GetMapping("/historico")
    public String historico() {
        return "historico";
    }


    /* =====================================
       RELATÓRIOS
    ===================================== */

    @GetMapping("/relatorios")
    public String relatorios() {
        return "relatorios";
    }


    /* =====================================
       BUSCAR OP
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