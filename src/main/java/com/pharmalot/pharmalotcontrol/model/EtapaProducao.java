package com.pharmalot.pharmalotcontrol.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "etapa_producao")
public class EtapaProducao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String op;

    private String produto;

    private String etapa;

    private String status;

    private String turno;

    private String equipamento;

    @Column(name = "quantidade_produzida")
    private Double quantidadeProduzida;

    @Column(length = 2000)
    private String observacoes;

    @Column(name = "usuario_responsavel")
    private String usuarioResponsavel;

    @Column(name = "data_hora")
    private LocalDateTime dataHora;

    public EtapaProducao() {
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

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getEtapa() {
        return etapa;
    }

    public void setEtapa(String etapa) {
        this.etapa = etapa;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(String equipamento) {
        this.equipamento = equipamento;
    }

    public Double getQuantidadeProduzida() {
        return quantidadeProduzida;
    }

    public void setQuantidadeProduzida(Double quantidadeProduzida) {
        this.quantidadeProduzida = quantidadeProduzida;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getUsuarioResponsavel() {
        return usuarioResponsavel;
    }

    public void setUsuarioResponsavel(String usuarioResponsavel) {
        this.usuarioResponsavel = usuarioResponsavel;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}