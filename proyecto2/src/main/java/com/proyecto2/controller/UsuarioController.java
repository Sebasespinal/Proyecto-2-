package com.proyecto2.controller;

import com.proyecto2.model.Usuario;
import com.proyecto2.service.Neo4jService;

import java.util.ArrayList;
import java.util.List;

public class UsuarioController {

    private final Neo4jService neo4jService;

    public UsuarioController() {
        this.neo4jService = neo4jService;
    }

    public List<String> obtenerRecomendaciones(List<String> generos) {
        List<String> recomendaciones = new ArrayList<>();
        for (String genero : generos) {
            recomendaciones.addAll(neo4jService.getRecomendaciones(genero));
        }
        return recomendaciones;
    }

    public List<Usuario> cargarUsuarios() {
    }

    public void guardarUsuario(Usuario usuarioActual) {
    }
}
