package com.EcoGuia.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El formato del email no es válido")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(nullable = false)
    private String nombre;

    @Column(name = "token")
    private String token;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "objetos_reciclados")
    private Integer objetosReciclados = 0;

    @Column(name = "co2_evitado_kg")
    private Double co2EvitadoKg = 0.0;

    public Usuario() {
    }

    public Usuario(String email, String nombre, String password) {
        this.email = email;
        this.nombre = nombre;
        this.password = password;
    }

    public Long getId() { return id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Integer getObjetosReciclados() { return objetosReciclados; }
    public void setObjetosReciclados(Integer objetosReciclados) { this.objetosReciclados = objetosReciclados; }
    public Double getCo2EvitadoKg() { return co2EvitadoKg; }
    public void setCo2EvitadoKg(Double co2EvitadoKg) { this.co2EvitadoKg = co2EvitadoKg; }
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
}