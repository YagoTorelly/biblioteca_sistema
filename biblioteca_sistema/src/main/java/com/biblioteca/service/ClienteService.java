package com.biblioteca.service;

import com.biblioteca.model.Cliente;
import java.util.ArrayList;
import java.util.List;

public class ClienteService {
    private List<Cliente> clientes = new ArrayList<>();

    // Método para adicionar um novo cliente
    public Cliente adicionarCliente(Cliente cliente) {
        cliente.setId((long) (clientes.size() + 1));
        clientes.add(cliente);
        return cliente;
    }

    // Método para listar todos os clientes
    public List<Cliente> listarClientes() {
        return new ArrayList<>(clientes);
    }

    // Método para atualizar um cliente existente
    public Cliente atualizarCliente(Long id, Cliente clienteAtualizado) {
        for (Cliente cliente : clientes) {
            if (cliente.getId().equals(id)) {
                cliente.setNome(clienteAtualizado.getNome());
                cliente.setIdade(clienteAtualizado.getIdade());
                cliente.setTelefone(clienteAtualizado.getTelefone());
                cliente.setCpf(clienteAtualizado.getCpf());
                cliente.setEmail(clienteAtualizado.getEmail());
                return cliente;
            }
        }
        return null; // Cliente não encontrado
    }

    // Método para remover um cliente por ID
    public Cliente removerCliente(Long id) {
        Cliente clienteRemovido = buscarClientePorId(id);
        if (clienteRemovido != null) {
            clientes.remove(clienteRemovido);
        }
        return clienteRemovido;
    }

    // Método para buscar um cliente por ID
    public Cliente buscarClientePorId(Long id) {
        return clientes.stream()
                .filter(cliente -> cliente.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
