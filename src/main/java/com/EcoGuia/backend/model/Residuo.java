package com.EcoGuia.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "residuos")
public class Residuo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del residuo es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    @Column(nullable = false)
    private String nombre;

    @NotBlank(message = "El color del contenedor es obligatorio")
    @Column(name = "contenedor_color", nullable = false)
    private String contenedorColor;

    private String instrucciones;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @ManyToMany(mappedBy = "residuosAceptados")
    private Set<PuntoLimpio> puntosLimpios = new HashSet<>();

    @OneToMany(mappedBy = "residuo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RegistroReciclaje> registros = new ArrayList<>();

    public Residuo() {}

    public Residuo(String nombre, String contenedorColor, String instrucciones) {
        this.nombre = nombre;
        this.contenedorColor = contenedorColor;
        this.instrucciones = instrucciones;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getContenedorColor() { return contenedorColor; }
    public void setContenedorColor(String contenedorColor) { this.contenedorColor = contenedorColor; }
    public String getInstrucciones() { return instrucciones; }
    public void setInstrucciones(String instrucciones) { this.instrucciones = instrucciones; }
    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
    public Set<PuntoLimpio> getPuntosLimpios() { return puntosLimpios; }
    public void setPuntosLimpios(Set<PuntoLimpio> puntosLimpios) { this.puntosLimpios = puntosLimpios; }
    public List<RegistroReciclaje> getRegistros() { return registros; }
    public void setRegistros(List<RegistroReciclaje> registros) { this.registros = registros; }
}