package com.proyecto2.service;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;

import java.util.ArrayList;
import java.util.List;

public class Neo4jService implements AutoCloseable {

    private final Driver driver;

    public Neo4jService(String uri, String user, String password) {
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
    }

    @Override
    public void close() {
        driver.close();
    }

    public List<String> getRecomendaciones(String genero) {
        List<String> recomendaciones = new ArrayList<>();
        try (Session session = driver.session()) {
            String query = "MATCH (p:Película) WHERE p.genero = $genero RETURN p.titulo";
            session.readTransaction(tx -> tx.run(query, org.neo4j.driver.Values.parameters("genero", genero))
                    .list(record -> recomendaciones.add(record.get("p.titulo").asString())));
        }
        return recomendaciones;
    }
}
