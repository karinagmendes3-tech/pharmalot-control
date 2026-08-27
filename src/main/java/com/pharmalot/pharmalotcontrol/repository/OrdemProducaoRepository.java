package com.pharmalot.pharmalotcontrol.repository;

import com.pharmalot.pharmalotcontrol.model.OrdemProducao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrdemProducaoRepository extends JpaRepository<OrdemProducao, Long> {

    Optional<OrdemProducao> findByNumeroOp(String numeroOp);
}