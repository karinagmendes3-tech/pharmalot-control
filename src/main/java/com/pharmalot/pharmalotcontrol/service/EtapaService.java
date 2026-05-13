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

    public List<EtapaProducao> buscarPorOp(String op) {
        return etapaRepository.findByOpContainingIgnoreCase(op);
    }
}