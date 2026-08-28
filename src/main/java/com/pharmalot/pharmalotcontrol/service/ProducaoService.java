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


    /* =====================================
       BUSCAR OP
    ===================================== */

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

            ordem.setCodigoProduto(
                    base.getProduto()
            );

            ordem.setDescricaoProduto(
                    base.getDescricaoProduto()
            );

            ordem.setLote(
                    base.getLote()
            );

            ordem.setStatusOp(
                    base.getStatusOp()
            );

            ordem.setDataCriacao(
                    base.getDataHoraCriacao()
            );

            ordem.setOrigemDados(
                    "OPCENTER"
            );

            return Optional.of(ordem);
        }

        return Optional.empty();
    }


    /* =====================================
       SALVAR OP
    ===================================== */

    public OrdemProducao salvarOrdem(
            OrdemProducao ordem) {

        return ordemRepository.save(ordem);
    }


    /* =====================================
       REGISTRAR NOVA ETAPA
    ===================================== */

    public HistoricoProducao registrarEtapa(
            OrdemProducao ordem,
            String etapa,
            String statusProcesso,
            Integer quantidadeDesvios,
            String operador,
            String observacao) {

        HistoricoProducao historico =
                new HistoricoProducao();

        historico.setOrdemProducao(
                ordem
        );

        historico.setEtapa(
                etapa
        );

        historico.setStatusProcesso(
                statusProcesso
        );

        historico.setQuantidadeDesvios(
                quantidadeDesvios
        );

        historico.setOperador(
                operador
        );

        historico.setObservacao(
                observacao
        );

        historico.setDataHoraRegistro(
                LocalDateTime.now()
        );

        return historicoRepository.save(
                historico
        );
    }


    /* =====================================
       HISTÓRICO DE UMA OP
    ===================================== */

    public List<HistoricoProducao> buscarHistorico(
            Long ordemId) {

        return historicoRepository
                .findByOrdemProducaoIdOrderByDataHoraRegistroAsc(
                        ordemId
                );
    }


    /* =====================================
       TODO O HISTÓRICO
    ===================================== */

    public List<HistoricoProducao> buscarTodoHistorico() {

        return historicoRepository
                .findAllByOrderByDataHoraRegistroDesc();
    }


    /* =====================================
       SALVAR JUSTIFICATIVA DO SUPERVISOR
    ===================================== */

    public HistoricoProducao salvarJustificativaSupervisor(
            Long historicoId,
            String justificativa,
            String supervisor) {

        HistoricoProducao historico =
                historicoRepository
                        .findById(historicoId)
                        .orElseThrow(
                                () -> new IllegalArgumentException(
                                        "Registro de histórico não encontrado."
                                )
                        );

        /*
         * Como a justificativa deve ser utilizada
         * para registros abandonados, impedimos
         * justificativa em outros status.
         */
        if (!"Abandonado".equalsIgnoreCase(
                historico.getStatusProcesso())) {

            throw new IllegalStateException(
                    "A justificativa do supervisor só pode ser registrada para OPs abandonadas."
            );
        }

        historico.setJustificativaSupervisor(
                justificativa
        );

        historico.setSupervisorResponsavel(
                supervisor
        );

        historico.setDataHoraJustificativa(
                LocalDateTime.now()
        );

        return historicoRepository.save(
                historico
        );
    }
}