package com.biblioteca;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConnectionTest implements CommandLineRunner {

    @Autowired
    private DataSource dataSource;

    @Override
    public void run(String... args) throws Exception {
        try {
            System.out.println("DataSource: " + dataSource);
            System.out.println("Conexão com o banco de dados bem-sucedida!");
        } catch (Exception e) {
            System.err.println("Erro ao conectar com o banco de dados:");
            e.printStackTrace();
        }
    }
}
