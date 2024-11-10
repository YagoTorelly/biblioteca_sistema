package com.biblioteca.service;

import com.biblioteca.model.Reserva;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReservaService {
    private List<Reserva> reservas = new ArrayList<>();

    // Método para adicionar uma nova reserva
    public Reserva adicionarReserva(Reserva reserva) {
        reserva.setId((long) (reservas.size() + 1));
        reservas.add(reserva);
        return reserva;
    }

    // Método para listar todas as reservas
    public List<Reserva> listarReservas() {
        return new ArrayList<>(reservas);
    }

    // Método para buscar uma reserva por ID
    public Optional<Reserva> buscarReservaPorId(Long id) {
        return reservas.stream().filter(reserva -> reserva.getId().equals(id)).findFirst();
    }

    // Método para remover uma reserva por ID
    public boolean removerReserva(Long id) {
        return reservas.removeIf(reserva -> reserva.getId().equals(id));
    }
}
