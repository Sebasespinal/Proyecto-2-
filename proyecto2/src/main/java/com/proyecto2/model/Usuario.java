package com.proyecto2.model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private String nombre;
    private Preferencias preferencias;

    public Usuario() {
        this.nombre = "Usuario";
        this.preferencias = new Preferencias();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Preferencias getPreferencias() {
        return preferencias;
    }

    public void setPreferencias(Preferencias preferencias) {
        this.preferencias = preferencias;
    }

    public static class Preferencias {
        private List<String> generos;

        public Preferencias() {
            this.generos = new ArrayList<>();
        }

        public List<String> getGeneros() {
            return generos;
        }

        public void setGeneros(List<String> generos) {
            this.generos = generos;
        }
    }
}
