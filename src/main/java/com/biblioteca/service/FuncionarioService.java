package com.biblioteca.service;

import com.biblioteca.model.Funcionario;

import java.util.ArrayList;
import java.util.List;

public class FuncionarioService {
    private List<Funcionario> funcionarios = new ArrayList<>();

    public Funcionario adicionarFuncionario(Funcionario funcionario) {
        funcionario.setId((long) (funcionarios.size() + 1));
        funcionarios.add(funcionario);
        return funcionario;
    }

    public List<Funcionario> listarFuncionarios() {
        return new ArrayList<>(funcionarios);
    }
}
