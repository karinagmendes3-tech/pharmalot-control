package com.pharmalot.pharmalotcontrol.repository;

import com.pharmalot.pharmalotcontrol.model.OpBase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OpBaseRepository extends JpaRepository<OpBase, Long> {

    Optional<OpBase> findByOpIgnoreCase(String op);

    boolean existsByOpIgnoreCase(String op);
}