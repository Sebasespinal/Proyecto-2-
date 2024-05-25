package com.proyecto2.service;

import com.proyecto2.model.Contenido;
import com.proyecto2.model.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RecomendacionServicio {

    private List<Contenido> contenidoDisponible;

    public RecomendacionServicio() {
        // Inicialización ficticia del contenido disponible
        contenidoDisponible = new ArrayList<>();
        contenidoDisponible.add(new Contenido() {{ setId(1); setTitulo("Película 1"); setGenero("Acción"); }});
        contenidoDisponible.add(new Contenido() {{ setId(2); setTitulo("Película 2"); setGenero("Comedia"); }});
        contenidoDisponible.add(new Contenido() {{ setId(3); setTitulo("Película 3"); setGenero("Drama"); }});
    }

    public List<Contenido> obtenerRecomendaciones(Usuario usuario) {
        List<String> generosPreferidos = usuario.getPreferencias().getGeneros();
        return contenidoDisponible.stream()
                .filter(c -> generosPreferidos.contains(c.getGenero()))
                .collect(Collectors.toList());
    }
}
