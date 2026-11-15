package com.EcoGuia.backend.repository;

import com.EcoGuia.backend.model.PuntoLimpio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PuntoLimpioRepository extends JpaRepository<PuntoLimpio, Long> {
}