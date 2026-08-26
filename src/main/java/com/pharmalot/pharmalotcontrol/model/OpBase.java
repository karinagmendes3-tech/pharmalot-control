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

    private String lote;

    private String produto;

    @Column(name = "etapa_sistema")
    private String etapaSistema;

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

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getEtapaSistema() {
        return etapaSistema;
    }

    public void setEtapaSistema(String etapaSistema) {
        this.etapaSistema = etapaSistema;
    }

    public LocalDateTime getDataImportacao() {
        return dataImportacao;
    }

    public void setDataImportacao(LocalDateTime dataImportacao) {
        this.dataImportacao = dataImportacao;
    }
}