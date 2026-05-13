package com.pharmalot.pharmalotcontrol.model;

import jakarta.persistence.*;

@Entity
@Table(name = "etapa_producao")
public class EtapaProducao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String op;

    private String etapa;

    private String turno;

    private String equipamento;

    @Column(name = "quantidade_produzida")
    private Double quantidadeProduzida;

    private String observacoes;

    @Column(name = "usuario_responsavel")
    private String usuarioResponsavel;

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

    public String getEtapa() {
        return etapa;
    }

    public void setEtapa(String etapa) {
        this.etapa = etapa;
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
}