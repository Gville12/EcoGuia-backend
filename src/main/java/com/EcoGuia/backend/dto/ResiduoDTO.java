package com.EcoGuia.backend.dto;

public class ResiduoDTO {
    private Long id;
    private String nombre;
    private String categoria;
    private String contenedor;
    private String simbolo;
    private String color;
    private String descripcion;

    public ResiduoDTO(Long id, String nombre, String categoria, String contenedor, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.contenedor = contenedor;
        this.descripcion = descripcion;

        // Traducimos el color de la base de datos al que espera React
        this.color = traducirColor(contenedor);
        this.simbolo = generarSimbolo(nombre);
    }

    private String traducirColor(String contenedorColor) {
        if (contenedorColor == null) return "yellow";
        String color = contenedorColor.toLowerCase();
        if (color.contains("amarillo")) return "yellow";
        if (color.contains("azul")) return "blue";
        if (color.contains("verde")) return "green";
        if (color.contains("marrón") || color.contains("marron")) return "brown";
        return "red"; // Para pilas o especiales
    }

    private String generarSimbolo(String nombre) {
        if (nombre == null) return "OBJ";
        return nombre.substring(0, 3).toUpperCase(); // Coge las 3 primeras letras
    }

    // Getters
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getCategoria() { return categoria; }
    public String getContenedor() { return contenedor; }
    public String getSimbolo() { return simbolo; }
    public String getColor() { return color; }
    public String getDescripcion() { return descripcion; }
}
