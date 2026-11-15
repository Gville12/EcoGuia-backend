package com.EcoGuia.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "registros_reciclaje")
public class RegistroReciclaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime fecha = LocalDateTime.now();

    @Column(name = "cantidad_objetos", nullable = false)
    private Integer cantidadObjetos = 1;

    @Column(name = "co2_ahorrado_kg", nullable = false)
    private Double co2AhorradoKg = 0.2;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "residuo_id", nullable = false)
    private Residuo residuo;

    public RegistroReciclaje() {}

    public RegistroReciclaje(Integer cantidadObjetos, Double co2AhorradoKg,
                             Usuario usuario, Residuo residuo) {
        this.cantidadObjetos = cantidadObjetos;
        this.co2AhorradoKg = co2AhorradoKg;
        this.usuario = usuario;
        this.residuo = residuo;
        this.fecha = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
    public Integer getCantidadObjetos() { return cantidadObjetos; }
    public void setCantidadObjetos(Integer cantidadObjetos) { this.cantidadObjetos = cantidadObjetos; }
    public Double getCo2AhorradoKg() { return co2AhorradoKg; }
    public void setCo2AhorradoKg(Double co2AhorradoKg) { this.co2AhorradoKg = co2AhorradoKg; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public Residuo getResiduo() { return residuo; }
    public void setResiduo(Residuo residuo) { this.residuo = residuo; }
}