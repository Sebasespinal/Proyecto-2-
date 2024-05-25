/* Universidad Del Valle de Guatemala
* Anggelie Lizeth Velásquez Asencio - 221181
* Rene Sebastian Espinal Zamora - 22867
* Plataforma de recomendación de peliculas y multiples generos
 */
package com.proyecto2;


import com.proyecto2.controller.UsuarioController;
import com.proyecto2.model.Preferencias;
import com.proyecto2.model.Usuario;
import com.proyecto2.service.Neo4jService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Netflix Recommender");

        // Inicializar el servicio de Neo4j
        Neo4jService neo4jService = new Neo4jService("bolt://localhost:7687", "neo4j", "password");
        UsuarioController usuarioController = new UsuarioController(neo4jService);

        // Escena para solicitar los gustos del usuario
        VBox vboxPreferences = new VBox();
        Label preferencesLabel = new Label("Seleccione sus géneros favoritos:");
        CheckBox accionCheckBox = new CheckBox("Acción");
        CheckBox comediaCheckBox = new CheckBox("Comedia");
        CheckBox romanticoCheckBox = new CheckBox("Romántico");
        CheckBox vidaRealCheckBox = new CheckBox("Vida Real");
        Button preferencesButton = new Button("Aceptar");
        vboxPreferences.getChildren().addAll(preferencesLabel, accionCheckBox, comediaCheckBox, romanticoCheckBox, vidaRealCheckBox, preferencesButton);
        Scene preferencesScene = new Scene(vboxPreferences, 300, 250);

        // Usuario ficticio con preferencias para pruebas
        Usuario usuario = new Usuario();
        usuario.setPreferencias(new Preferencias());

        // Interfaz de usuario básica para las recomendaciones
        VBox vboxMain = new VBox();
        Label label = new Label();
        ListView<String> listView = new ListView<>();
        Button btn = new Button("Obtener Recomendaciones");
        btn.setOnAction(e -> {
            listView.getItems().clear();
            List<String> generosPreferidos = usuario.getPreferencias().getGeneros();
            List<String> recomendaciones = usuarioController.obtenerRecomendaciones(generosPreferidos);
            listView.getItems().addAll(recomendaciones);
        });

        vboxMain.getChildren().addAll(label, btn, listView);
        Scene mainScene = new Scene(vboxMain, 500, 350);

        // Acción del botón de la escena de preferencias
        preferencesButton.setOnAction(e -> {
            List<String> generosPreferidos = usuario.getPreferencias().getGeneros();
            if (accionCheckBox.isSelected()) generosPreferidos.add("Acción");
            if (comediaCheckBox.isSelected()) generosPreferidos.add("Comedia");
            if (romanticoCheckBox.isSelected()) generosPreferidos.add("Romántico");
            if (vidaRealCheckBox.isSelected()) generosPreferidos.add("Vida Real");

            if (!generosPreferidos.isEmpty()) {
                label.setText("Recomendaciones para " + usuario.getNombre());
                primaryStage.setScene(mainScene);
            }
        });

        primaryStage.setScene(preferencesScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
