package com.EcoGuia.backend.controller;

import com.EcoGuia.backend.dto.ResiduoDTO;
import com.EcoGuia.backend.model.Residuo;
import com.EcoGuia.backend.repository.ResiduoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/objetos")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:4173"})
public class ResiduoController {

    private final ResiduoRepository residuoRepository;

    public ResiduoController(ResiduoRepository residuoRepository) {
        this.residuoRepository = residuoRepository;
    }

    @GetMapping
    public ResponseEntity<List<ResiduoDTO>> buscarObjetos(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String categoria) {

        String nombreFiltro = (search != null && !search.trim().isEmpty()) ? search.trim() : null;
        String categoriaFiltro = (categoria != null && !categoria.equals("Todos") && !categoria.isEmpty()) ? categoria : null;

        // Buscamos en la entidad Residuo
        List<Residuo> resultados = residuoRepository.buscarConFiltros(nombreFiltro, categoriaFiltro);

        // Los convertimos al DTO que React entiende
        List<ResiduoDTO> resultadosDTO = resultados.stream().map(r -> new ResiduoDTO(
                r.getId(),
                r.getNombre(),
                r.getCategoria() != null ? r.getCategoria().getNombre() : "Varios",
                r.getContenedorColor(),
                r.getInstrucciones()
        )).collect(Collectors.toList());

        return ResponseEntity.ok(resultadosDTO);
    }
}