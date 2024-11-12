package com.biblioteca.model;

import java.time.LocalDate;

public class Reserva {
    private Long id;
    private Long clienteId;
    private Long livroId;
    private LocalDate dataReserva;

    // Construtor com os parâmetros corretos
    public Reserva(Long id, Long clienteId, Long livroId, LocalDate dataReserva) {
        this.id = id;
        this.clienteId = clienteId;
        this.livroId = livroId;
        this.dataReserva = dataReserva;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getLivroId() {
        return livroId;
    }

    public void setLivroId(Long livroId) {
        this.livroId = livroId;
    }

    public LocalDate getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(LocalDate dataReserva) {
        this.dataReserva = dataReserva;
    }
}
