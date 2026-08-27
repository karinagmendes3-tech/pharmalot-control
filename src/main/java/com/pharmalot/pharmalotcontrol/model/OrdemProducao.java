package com.pharmalot.pharmalotcontrol.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ordem_producao")
public class OrdemProducao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_op", unique = true, nullable = false)
    private String numeroOp;

    @Column(name = "codigo_produto")
    private String codigoProduto;

    @Column(name = "descricao_produto")
    private String descricaoProduto;

    private String lote;

    @Column(name = "status_op")
    private String statusOp;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @Column(name = "origem_dados")
    private String origemDados;

    public OrdemProducao() {
    }

    public Long getId() {
        return id;
    }

    public String getNumeroOp() {
        return numeroOp;
    }

    public void setNumeroOp(String numeroOp) {
        this.numeroOp = numeroOp;
    }

    public String getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(String codigoProduto) {
        this.codigoProduto = codigoProduto;
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

    public String getStatusOp() {
        return statusOp;
    }

    public void setStatusOp(String statusOp) {
        this.statusOp = statusOp;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getOrigemDados() {
        return origemDados;
    }

    public void setOrigemDados(String origemDados) {
        this.origemDados = origemDados;
    }
}