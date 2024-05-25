package com.proyecto2;
import com.proyecto2.model.Preferencias;
import com.proyecto2.model.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    private static final String FILE_PATH = "usuarios.txt";

    public static void guardarUsuarios(List<Usuario> usuarios) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Usuario usuario : usuarios) {
                writer.write(usuario.getNombre() + "," + String.join(";", usuario.getPreferencias().getGeneros()));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Usuario> cargarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String nombre = parts[0];
                String[] generos = parts[1].split(";");

                Usuario usuario = new Usuario();
                usuario.setNombre(nombre);
                Preferencias preferencias = new Preferencias();
                preferencias.setGeneros(List.of(generos));
                usuario.setPreferencias(preferencias);

                usuarios.add(usuario);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usuarios;
    }
}

