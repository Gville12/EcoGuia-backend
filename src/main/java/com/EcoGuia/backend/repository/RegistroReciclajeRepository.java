package com.EcoGuia.backend.repository;

import com.EcoGuia.backend.model.RegistroReciclaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroReciclajeRepository extends JpaRepository<RegistroReciclaje, Long> {
}
