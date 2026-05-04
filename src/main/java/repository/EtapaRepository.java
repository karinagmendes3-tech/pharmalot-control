package com.pharmalot.pharmalotcontrol.repository;

import com.pharmalot.pharmalotcontrol.model.EtapaProducao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EtapaRepository extends JpaRepository<EtapaProducao, Long> {

    List<EtapaProducao> findByOp(String op);
}