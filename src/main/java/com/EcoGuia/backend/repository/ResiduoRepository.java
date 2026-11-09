package com.EcoGuia.backend.repository;

import com.EcoGuia.backend.model.Residuo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResiduoRepository extends JpaRepository<Residuo, Long> {

    @Query("SELECT r FROM Residuo r LEFT JOIN r.categoria c WHERE " +
            "(:nombre IS NULL OR LOWER(r.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))) AND " +
            "(:categoria IS NULL OR c.nombre = :categoria)")
    List<Residuo> buscarConFiltros(@Param("nombre") String nombre, @Param("categoria") String categoria);

    @Query("SELECT r FROM Residuo r WHERE LOWER(r.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Residuo> buscarPorNombre(@Param("nombre") String nombre);

    List<Residuo> findByContenedorColor(String contenedorColor);
}