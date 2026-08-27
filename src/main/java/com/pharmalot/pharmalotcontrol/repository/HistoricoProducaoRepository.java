package com.pharmalot.pharmalotcontrol.repository;

import com.pharmalot.pharmalotcontrol.model.HistoricoProducao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoricoProducaoRepository
        extends JpaRepository<HistoricoProducao, Long> {

    List<HistoricoProducao>
    findByOrdemProducaoIdOrderByDataHoraRegistroAsc(Long ordemProducaoId);

    List<HistoricoProducao>
    findAllByOrderByDataHoraRegistroDesc();
}