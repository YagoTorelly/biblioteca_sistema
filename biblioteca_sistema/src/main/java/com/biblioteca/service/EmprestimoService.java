package com.biblioteca.service;

import com.biblioteca.model.Emprestimo;
import com.biblioteca.model.Livro;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoService {
    private List<Emprestimo> emprestimos = new ArrayList<>();
    private final LivroService livroService;
    private final ConfigService configService;

    public EmprestimoService(LivroService livroService, ConfigService configService) {
        this.livroService = livroService;
        this.configService = configService;
    }

    // Método para realizar um novo empréstimo
    public Emprestimo realizarEmprestimo(Long clienteId, Long livroId) {
        Livro livro = livroService.buscarLivroPorId(livroId);
        if (livro != null && livro.getQuantidadeDisponivel() > 0) {
            livro.setQuantidadeDisponivel(livro.getQuantidadeDisponivel() - 1);
            Emprestimo emprestimo = new Emprestimo(
                    (long) (emprestimos.size() + 1),
                    clienteId,
                    livroId,
                    LocalDate.now(),
                    LocalDate.now().plusDays(configService.getDiasParaDevolucao())
            );
            emprestimos.add(emprestimo);
            return emprestimo;
        }
        return null; // Empréstimo não realizado devido a livro indisponível
    }

    // Método para listar todos os empréstimos
    public List<Emprestimo> listarEmprestimos() {
        return new ArrayList<>(emprestimos);
    }

    // Método para registrar a devolução de um livro
    public boolean registrarDevolucao(Long emprestimoId) {
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getId().equals(emprestimoId)) {
                Livro livro = livroService.buscarLivroPorId(emprestimo.getLivroId());
                if (livro != null) {
                    livro.setQuantidadeDisponivel(livro.getQuantidadeDisponivel() + 1);
                }
                emprestimos.remove(emprestimo);
                return true; // Devolução registrada com sucesso
            }
        }
        return false; // Empréstimo não encontrado
    }
}