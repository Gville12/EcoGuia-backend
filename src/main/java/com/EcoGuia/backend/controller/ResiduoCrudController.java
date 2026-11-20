package com.EcoGuia.backend.controller;

import com.EcoGuia.backend.model.Residuo;
import com.EcoGuia.backend.repository.ResiduoRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/residuos")
@CrossOrigin(origins = "*")
public class ResiduoCrudController {

    private final ResiduoRepository residuoRepository;

    public ResiduoCrudController(ResiduoRepository residuoRepository) {
        this.residuoRepository = residuoRepository;
    }

    @GetMapping
    public List<Residuo> listarTodos() {
        return residuoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Residuo> obtenerPorId(@PathVariable Long id) {
        Optional<Residuo> residuo = residuoRepository.findById(id);
        return residuo.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Residuo> crear(@Valid @RequestBody Residuo residuo) {
        Residuo nuevoResiduo = residuoRepository.save(residuo);
        return ResponseEntity.ok(nuevoResiduo);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Residuo> actualizar(@PathVariable Long id, @Valid @RequestBody Residuo residuoActualizado) {
        return residuoRepository.findById(id).map(residuo -> {
            residuo.setNombre(residuoActualizado.getNombre());
            residuo.setContenedorColor(residuoActualizado.getContenedorColor());
            residuo.setInstrucciones(residuoActualizado.getInstrucciones());
            residuo.setCategoria(residuoActualizado.getCategoria());
            return ResponseEntity.ok(residuoRepository.save(residuo));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (residuoRepository.existsById(id)) {
            residuoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}