package com.pharmalot.pharmalotcontrol.controller;

import com.pharmalot.pharmalotcontrol.model.OrdemProducao;
import com.pharmalot.pharmalotcontrol.service.ProducaoService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
public class ProducaoController {

    private final ProducaoService producaoService;

    public ProducaoController(ProducaoService producaoService) {
        this.producaoService = producaoService;
    }


    /* =====================================
       TELA DE PRODUÇÃO
    ===================================== */

    @GetMapping("/producao")
    public String producao() {

        return "producao";
    }


    /* =====================================
       BUSCAR OP
    ===================================== */

    @GetMapping("/producao/buscar")
    public String buscarOp(
            @RequestParam String numeroOp,
            Model model) {

        String op = numeroOp.trim();

        Optional<OrdemProducao> ordem =
                producaoService.buscarPorOp(op);

        if (ordem.isPresent()) {

            model.addAttribute(
                    "ordem",
                    ordem.get()
            );

            model.addAttribute(
                    "encontrada",
                    true
            );

        } else {

            OrdemProducao novaOrdem =
                    new OrdemProducao();

            novaOrdem.setNumeroOp(op);

            model.addAttribute(
                    "ordem",
                    novaOrdem
            );

            model.addAttribute(
                    "encontrada",
                    false
            );
        }

        return "producao";
    }


    /* =====================================
       SALVAR REGISTRO DE PRODUÇÃO
    ===================================== */

    @PostMapping("/producao/salvar")
    public String salvarRegistro(

            @RequestParam String numeroOp,

            @RequestParam(required = false)
            String codigoProduto,

            @RequestParam(required = false)
            String descricaoProduto,

            @RequestParam(required = false)
            String lote,

            @RequestParam(required = false)
            String statusOp,

            @RequestParam String etapa,

            @RequestParam String statusProcesso,

            @RequestParam(defaultValue = "0")
            Integer quantidadeDesvios,

            @RequestParam(required = false)
            String operador,

            @RequestParam(required = false)
            String observacao,

            Model model) {


        /* =====================================
           BUSCA A OP
        ===================================== */

        String op = numeroOp.trim();

        Optional<OrdemProducao> existente =
                producaoService.buscarPorOp(op);

        OrdemProducao ordem;


        /* =====================================
           OP JÁ EXISTENTE
        ===================================== */

        if (existente.isPresent()) {

            ordem = existente.get();

        }


        /* =====================================
           NOVA OP
        ===================================== */

        else {

            ordem = new OrdemProducao();

            ordem.setNumeroOp(op);

            ordem.setOrigemDados("MANUAL");
        }


        /* =====================================
           ATUALIZA OS DADOS DA OP
        ===================================== */

        ordem.setCodigoProduto(codigoProduto);

        ordem.setDescricaoProduto(
                descricaoProduto
        );

        ordem.setLote(lote);

        ordem.setStatusOp(statusOp);


        /* =====================================
           DATA DE CRIAÇÃO
        ===================================== */

        if (ordem.getDataCriacao() == null) {

            ordem.setDataCriacao(
                    LocalDateTime.now()
            );
        }


        /* =====================================
           SALVA OU ATUALIZA A OP
        ===================================== */

        OrdemProducao ordemSalva =
                producaoService.salvarOrdem(
                        ordem
                );


        /* =====================================
           CRIA REGISTRO NO HISTÓRICO
        ===================================== */

        producaoService.registrarEtapa(

                ordemSalva,

                etapa,

                statusProcesso,

                quantidadeDesvios,

                operador,

                observacao
        );


        /* =====================================
           RETORNA PARA A TELA
        ===================================== */

        model.addAttribute(
                "ordem",
                ordemSalva
        );

        model.addAttribute(
                "encontrada",
                true
        );

        model.addAttribute(
                "sucesso",
                "Registro salvo com sucesso."
        );


        return "producao";
    }
}