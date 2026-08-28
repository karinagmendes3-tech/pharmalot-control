package com.pharmalot.pharmalotcontrol.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "historico_producao")
public class HistoricoProducao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ordem_producao_id", nullable = false)
    private OrdemProducao ordemProducao;

    private String etapa;

    @Column(name = "status_processo")
    private String statusProcesso;

    @Column(name = "quantidade_desvios")
    private Integer quantidadeDesvios;

    private String operador;

    private String observacao;

    @Column(name = "data_hora_registro")
    private LocalDateTime dataHoraRegistro;

    @Column(name = "justificativa_supervisor", length = 2000)
    private String justificativaSupervisor;

    @Column(name = "supervisor_responsavel")
    private String supervisorResponsavel;

    @Column(name = "data_hora_justificativa")
    private LocalDateTime dataHoraJustificativa;

    public HistoricoProducao() {
    }

    public Long getId() {
        return id;
    }

    public OrdemProducao getOrdemProducao() {
        return ordemProducao;
    }

    public void setOrdemProducao(OrdemProducao ordemProducao) {
        this.ordemProducao = ordemProducao;
    }

    public String getEtapa() {
        return etapa;
    }

    public void setEtapa(String etapa) {
        this.etapa = etapa;
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

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public LocalDateTime getDataHoraRegistro() {
        return dataHoraRegistro;
    }

    public void setDataHoraRegistro(LocalDateTime dataHoraRegistro) {
        this.dataHoraRegistro = dataHoraRegistro;
    }

    public String getJustificativaSupervisor() {
        return justificativaSupervisor;
    }

    public void setJustificativaSupervisor(String justificativaSupervisor) {
        this.justificativaSupervisor = justificativaSupervisor;
    }

    public String getSupervisorResponsavel() {
        return supervisorResponsavel;
    }

    public void setSupervisorResponsavel(String supervisorResponsavel) {
        this.supervisorResponsavel = supervisorResponsavel;
    }

    public LocalDateTime getDataHoraJustificativa() {
        return dataHoraJustificativa;
    }

    public void setDataHoraJustificativa(LocalDateTime dataHoraJustificativa) {
        this.dataHoraJustificativa = dataHoraJustificativa;
    }
}