package com.EcoGuia.backend.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "puntos_limpios")
public class PuntoLimpio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String direccion;
    private String municipio;
    private String horario;
    private Double latitud;
    private Double longitud;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "punto_limpio_residuo",
            joinColumns = @JoinColumn(name = "punto_limpio_id"),
            inverseJoinColumns = @JoinColumn(name = "residuo_id")
    )
    private Set<Residuo> residuosAceptados = new HashSet<>();

    public PuntoLimpio() {}

    public PuntoLimpio(String nombre, String direccion, String municipio,
                       String horario, Double latitud, Double longitud) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.municipio = municipio;
        this.horario = horario;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getMunicipio() { return municipio; }
    public void setMunicipio(String municipio) { this.municipio = municipio; }
    public String getHorario() { return horario; }
    public void setHorario(String horario) { this.horario = horario; }
    public Double getLatitud() { return latitud; }
    public void setLatitud(Double latitud) { this.latitud = latitud; }
    public Double getLongitud() { return longitud; }
    public void setLongitud(Double longitud) { this.longitud = longitud; }
    public Set<Residuo> getResiduosAceptados() { return residuosAceptados; }
    public void setResiduosAceptados(Set<Residuo> residuosAceptados) { this.residuosAceptados = residuosAceptados; }
}
