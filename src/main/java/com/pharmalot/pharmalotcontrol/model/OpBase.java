package com.pharmalot.pharmalotcontrol.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "op_base")
public class OpBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String op;

    @Column(name = "status_op")
    private String statusOp;

    private String produto;

    @Column(name = "descricao_produto")
    private String descricaoProduto;

    private String lote;

    @Column(name = "data_hora_criacao")
    private LocalDateTime dataHoraCriacao;

    @Column(name = "processo_atual")
    private String processoAtual;

    @Column(name = "status_processo")
    private String statusProcesso;

    @Column(name = "quantidade_desvios")
    private Integer quantidadeDesvios;

    @Column(name = "data_importacao")
    private LocalDateTime dataImportacao;

    public OpBase() {
    }

    public Long getId() {
        return id;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getStatusOp() {
        return statusOp;
    }

    public void setStatusOp(String statusOp) {
        this.statusOp = statusOp;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public LocalDateTime getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    public void setDataHoraCriacao(LocalDateTime dataHoraCriacao) {
        this.dataHoraCriacao = dataHoraCriacao;
    }

    public String getProcessoAtual() {
        return processoAtual;
    }

    public void setProcessoAtual(String processoAtual) {
        this.processoAtual = processoAtual;
    }

    public String getStatusProcesso() {
        return statusProcesso;
    }

    public void setStatusProcesso(String statusProcesso) {
        this.statusProcesso = statusProcesso;
    }

    public Integer getQuantidadeDesvios() {
        return quantidadeDesvios;
    }

    public void setQuantidadeDesvios(Integer quantidadeDesvios) {
        this.quantidadeDesvios = quantidadeDesvios;
    }

    public LocalDateTime getDataImportacao() {
        return dataImportacao;
    }

    public void setDataImportacao(LocalDateTime dataImportacao) {
        this.dataImportacao = dataImportacao;
    }
}