package com.biblioteca.service;

public class ConfigService {
    private int maxLivrosPorCliente = 5; // Limite padrão de livros por cliente
    private int diasParaDevolucao = 7; // Prazo padrão de devolução em dias

    // Método para configurar o limite máximo de livros por cliente
    public void configurarMaxLivrosPorCliente(int maxLivros) {
        this.maxLivrosPorCliente = maxLivros;
    }

    // Método para configurar o prazo de devolução em dias
    public void configurarDiasParaDevolucao(int dias) {
        this.diasParaDevolucao = dias;
    }

    // Métodos para obter as configurações atuais
    public int getMaxLivrosPorCliente() {
        return maxLivrosPorCliente;
    }

    public int getDiasParaDevolucao() {
        return diasParaDevolucao;
    }
}