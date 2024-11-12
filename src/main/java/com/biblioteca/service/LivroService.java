package com.biblioteca.service;

import com.biblioteca.model.Livro;
import java.util.ArrayList;
import java.util.List;

public class LivroService {
    private List<Livro> livros = new ArrayList<>();

    public Livro adicionarLivro(Livro livro) {
        livro.setId((long) (livros.size() + 1));
        livros.add(livro);
        return livro;
    }

    public List<Livro> listarLivros() {
        return new ArrayList<>(livros);
    }

    public Livro buscarLivroPorId(Long id) {
        return livros.stream().filter(livro -> livro.getId().equals(id)).findFirst().orElse(null);
    }

    public Livro atualizarLivro(Long id, Livro livroAtualizado) {
        Livro livroExistente = buscarLivroPorId(id);
        if (livroExistente != null) {
            livroExistente.setTitulo(livroAtualizado.getTitulo());
            livroExistente.setAutor(livroAtualizado.getAutor());
            livroExistente.setCategoria(livroAtualizado.getCategoria());
            livroExistente.setQuantidadeDisponivel(livroAtualizado.getQuantidadeDisponivel());
            return livroExistente;
        }
        return null; // Retorna null se o livro não for encontrado
    }

    public boolean removerLivro(Long id) {
        return livros.removeIf(livro -> livro.getId().equals(id));
    }

    public boolean reservarLivro(Long clienteId, Long livroId) {
        Livro livro = buscarLivroPorId(livroId);
        if (livro != null && livro.getQuantidadeDisponivel() == 0) {
            // Lógica de reserva aqui
            return true;
        }
        return false;
    }
}
