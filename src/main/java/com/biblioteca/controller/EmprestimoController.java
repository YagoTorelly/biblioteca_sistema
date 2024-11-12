package com.biblioteca.controller;

import com.biblioteca.model.Emprestimo;
import com.biblioteca.service.EmprestimoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    private final EmprestimoService emprestimoService;

    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    // Endpoint para realizar um novo empréstimo
    @PostMapping
    public ResponseEntity<Emprestimo> realizarEmprestimo(@RequestParam Long clienteId, @RequestParam Long livroId) {
        Emprestimo emprestimo = emprestimoService.realizarEmprestimo(clienteId, livroId);
        if (emprestimo != null) {
            return new ResponseEntity<>(emprestimo, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // Endpoint para listar todos os empréstimos
    @GetMapping
    public ResponseEntity<List<Emprestimo>> listarEmprestimos() {
        List<Emprestimo> emprestimos = emprestimoService.listarEmprestimos();
        return new ResponseEntity<>(emprestimos, HttpStatus.OK);
    }

    // Endpoint para registrar devolução de um livro
    @PutMapping("/devolucao/{emprestimoId}")
    public ResponseEntity<Void> registrarDevolucao(@PathVariable Long emprestimoId) {
        boolean devolvido = emprestimoService.registrarDevolucao(emprestimoId);
        if (devolvido) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}