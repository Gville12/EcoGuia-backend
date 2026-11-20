package com.EcoGuia.backend.controller;

import com.EcoGuia.backend.model.PuntoLimpio;
import com.EcoGuia.backend.repository.PuntoLimpioRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/puntos")
@CrossOrigin(origins = "*")
public class PuntoLimpioController {

    private final PuntoLimpioRepository puntoLimpioRepository;

    public PuntoLimpioController(PuntoLimpioRepository puntoLimpioRepository) {
        this.puntoLimpioRepository = puntoLimpioRepository;
    }

    @GetMapping
    public List<PuntoLimpio> listar() {
        return puntoLimpioRepository.findAll();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<PuntoLimpio> crear(@Valid @RequestBody PuntoLimpio puntoLimpio) {
        return ResponseEntity.ok(puntoLimpioRepository.save(puntoLimpio));
    }
}
