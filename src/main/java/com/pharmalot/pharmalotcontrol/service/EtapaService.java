package com.pharmalot.pharmalotcontrol.service;

import com.pharmalot.pharmalotcontrol.model.EtapaProducao;
import com.pharmalot.pharmalotcontrol.repository.EtapaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtapaService {

    private final EtapaRepository etapaRepository;

    public EtapaService(EtapaRepository etapaRepository) {
        this.etapaRepository = etapaRepository;
    }

    // Busca registros pela OP
    public List<EtapaProducao> buscarPorOp(String op) {
        return etapaRepository.findByOpContainingIgnoreCase(op);
    }

    // Salva uma atualização de produção no banco
    public EtapaProducao salvar(EtapaProducao etapaProducao) {
        return etapaRepository.save(etapaProducao);
    }

    // Lista todas as atualizações, começando pelas mais recentes
    public List<EtapaProducao> listarTodos() {
        return etapaRepository.findAllByOrderByDataHoraDesc();
    }
}