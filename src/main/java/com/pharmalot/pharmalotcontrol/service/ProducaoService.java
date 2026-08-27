package com.pharmalot.pharmalotcontrol.service;

import com.pharmalot.pharmalotcontrol.model.HistoricoProducao;
import com.pharmalot.pharmalotcontrol.model.OpBase;
import com.pharmalot.pharmalotcontrol.model.OrdemProducao;
import com.pharmalot.pharmalotcontrol.repository.HistoricoProducaoRepository;
import com.pharmalot.pharmalotcontrol.repository.OrdemProducaoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProducaoService {

    private final OrdemProducaoRepository ordemRepository;
    private final HistoricoProducaoRepository historicoRepository;
    private final OpBaseService opBaseService;

    public ProducaoService(
            OrdemProducaoRepository ordemRepository,
            HistoricoProducaoRepository historicoRepository,
            OpBaseService opBaseService) {

        this.ordemRepository = ordemRepository;
        this.historicoRepository = historicoRepository;
        this.opBaseService = opBaseService;
    }

    public Optional<OrdemProducao> buscarPorOp(String numeroOp) {

        Optional<OrdemProducao> ordemExistente =
                ordemRepository.findByNumeroOp(numeroOp);

        if (ordemExistente.isPresent()) {
            return ordemExistente;
        }

        Optional<OpBase> opBase =
                opBaseService.buscarPorOp(numeroOp);

        if (opBase.isPresent()) {

            OpBase base = opBase.get();

            OrdemProducao ordem = new OrdemProducao();

            ordem.setNumeroOp(base.getOp());
            ordem.setCodigoProduto(base.getProduto());
            ordem.setDescricaoProduto(base.getDescricaoProduto());
            ordem.setLote(base.getLote());
            ordem.setStatusOp(base.getStatusOp());
            ordem.setDataCriacao(base.getDataHoraCriacao());
            ordem.setOrigemDados("OPCENTER");

            return Optional.of(ordem);
        }

        return Optional.empty();
    }

    public OrdemProducao salvarOrdem(OrdemProducao ordem) {
        return ordemRepository.save(ordem);
    }

    public HistoricoProducao registrarEtapa(
            OrdemProducao ordem,
            String etapa,
            String statusProcesso,
            Integer quantidadeDesvios,
            String operador,
            String observacao) {

        HistoricoProducao historico = new HistoricoProducao();

        historico.setOrdemProducao(ordem);
        historico.setEtapa(etapa);
        historico.setStatusProcesso(statusProcesso);
        historico.setQuantidadeDesvios(quantidadeDesvios);
        historico.setOperador(operador);
        historico.setObservacao(observacao);
        historico.setDataHoraRegistro(LocalDateTime.now());

        return historicoRepository.save(historico);
    }

    public List<HistoricoProducao> buscarHistorico(Long ordemId) {
        return historicoRepository
                .findByOrdemProducaoIdOrderByDataHoraRegistroAsc(ordemId);
    }
}