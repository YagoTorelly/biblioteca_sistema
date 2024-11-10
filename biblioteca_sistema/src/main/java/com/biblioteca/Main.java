package com.biblioteca;

import com.biblioteca.model.Cliente;
import com.biblioteca.model.Funcionario;
import com.biblioteca.model.Livro;
import com.biblioteca.model.Emprestimo;
import com.biblioteca.service.ClienteService;
import com.biblioteca.service.EmprestimoService;
import com.biblioteca.service.LivroService;
import com.biblioteca.service.ConfigService;
import com.biblioteca.service.FuncionarioService;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static ClienteService clienteService = new ClienteService();
    private static LivroService livroService = new LivroService();
    private static ConfigService configService = new ConfigService();
    private static FuncionarioService funcionarioService = new FuncionarioService();
    private static EmprestimoService emprestimoService = new EmprestimoService(livroService, configService);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean rodando = true;

        while (rodando) {
            System.out.println("\n=== Sistema de Biblioteca ===");
            System.out.println("Escolha o tipo de usuário:");
            System.out.println("1. Admin");
            System.out.println("2. Funcionário");
            System.out.println("3. Cliente");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int perfil = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (perfil) {
                case 1 -> menuAdmin(scanner);
                case 2 -> menuFuncionario(scanner);
                case 3 -> menuCliente(scanner);
                case 0 -> {
                    rodando = false;
                    System.out.println("Encerrando o sistema...");
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
        scanner.close();
    }

    private static void menuCliente(Scanner scanner) {
        boolean rodando = true;
        while (rodando) {
            System.out.println("\n=== Menu Cliente ===");
            System.out.println("1. Consultar Livros Disponíveis");
            System.out.println("2. Empréstimo Livro");
            System.out.println("3. Devolver Livro");
            System.out.println("4. Reservar Livro");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> listarLivros();
                case 2 -> realizarEmprestimo(scanner);
                case 3 -> registrarDevolucao(scanner);
                case 4 -> reservarLivro(scanner);
                case 0 -> rodando = false;
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void menuFuncionario(Scanner scanner) {
        boolean rodando = true;
        while (rodando) {
            System.out.println("\n=== Menu Funcionário ===");
            System.out.println("1. Consultar Livros Disponíveis");
            System.out.println("2. Adicionar Livro");
            System.out.println("3. Atualizar/Editar Livro");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> listarLivros();
                case 2 -> adicionarLivro(scanner);
                case 3 -> atualizarLivro(scanner);
                case 0 -> rodando = false;
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void menuAdmin(Scanner scanner) {
        boolean rodando = true;
        while (rodando) {
            System.out.println("\n=== Menu Admin ===");
            System.out.println("1. Consultar Livros Disponíveis");
            System.out.println("2. Adicionar Cliente");
            System.out.println("3. Atualizar/Editar Cliente");
            System.out.println("4. Gerenciar Funcionários");
            System.out.println("5. Configurar Regras de Empréstimo");
            System.out.println("6. Adicionar Funcionário"); // Nova opção
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> listarLivros();
                case 2 -> adicionarCliente(scanner);
                case 3 -> editarCliente(scanner);
                case 4 -> gerenciarFuncionarios(scanner);
                case 5 -> configurarRegrasEmprestimo(scanner);
                case 6 -> adicionarFuncionario(scanner); // Chamando o novo método
                case 0 -> rodando = false;
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void listarLivros() {
        List<Livro> livros = livroService.listarLivros();
        System.out.println("\n=== Lista de Livros ===");
        System.out.printf("%-5s %-30s %-20s %-20s %-10s%n", "ID", "Título", "Autor", "Categoria", "Disponível");
        for (Livro livro : livros) {
            System.out.printf("%-5s %-30s %-20s %-20s %-10d%n",
                    livro.getId(), livro.getTitulo(), livro.getAutor(), livro.getCategoria(), livro.getQuantidadeDisponivel());
        }
    }

    private static void realizarEmprestimo(Scanner scanner) {
        System.out.print("ID do Cliente: ");
        Long clienteId = scanner.nextLong();
        System.out.print("ID do Livro: ");
        Long livroId = scanner.nextLong();
        scanner.nextLine();

        Emprestimo emprestimo = emprestimoService.realizarEmprestimo(clienteId, livroId);
        if (emprestimo != null) {
            System.out.println("Empréstimo realizado com sucesso! Data de devolução: " + emprestimo.getDataDevolucao());
        } else {
            System.out.println("Não foi possível realizar o empréstimo. Livro não disponível.");
        }
    }

    private static void adicionarFuncionario(Scanner scanner) {
        System.out.print("Nome do Funcionário: ");
        String nome = scanner.nextLine();
        System.out.print("Idade do Funcionário: ");
        int idade = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        Funcionario funcionario = new Funcionario(null, nome, idade, telefone, cpf, email);
        funcionarioService.adicionarFuncionario(funcionario);
        System.out.println("Funcionário adicionado com sucesso!");
    }


    private static void registrarDevolucao(Scanner scanner) {
        System.out.print("ID do Empréstimo: ");
        Long emprestimoId = scanner.nextLong();
        scanner.nextLine();

        boolean devolvido = emprestimoService.registrarDevolucao(emprestimoId);
        if (devolvido) {
            System.out.println("Devolução registrada com sucesso!");
        } else {
            System.out.println("Empréstimo não encontrado ou já devolvido.");
        }
    }

    private static void reservarLivro(Scanner scanner) {
        System.out.print("ID do Cliente: ");
        Long clienteId = scanner.nextLong();
        System.out.print("ID do Livro: ");
        Long livroId = scanner.nextLong();
        scanner.nextLine();

        boolean reservado = livroService.reservarLivro(clienteId, livroId);
        if (reservado) {
            System.out.println("Reserva realizada com sucesso!");
        } else {
            System.out.println("Não foi possível realizar a reserva. Verifique se o livro está disponível.");
        }
    }

    private static void adicionarCliente(Scanner scanner) {
        System.out.print("Nome do Cliente: ");
        String nome = scanner.nextLine();
        System.out.print("Idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        Cliente cliente = new Cliente(null, nome, idade, telefone, cpf, email);
        clienteService.adicionarCliente(cliente);
        System.out.println("Cliente adicionado com sucesso!");
    }

    private static void editarCliente(Scanner scanner) {
        System.out.print("ID do Cliente a editar: ");
        Long clienteId = scanner.nextLong();
        scanner.nextLine();
        Cliente cliente = clienteService.buscarClientePorId(clienteId);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }
        System.out.print("Novo nome (deixe em branco para manter): ");
        String nome = scanner.nextLine();
        if (!nome.isBlank()) cliente.setNome(nome);

        System.out.print("Nova idade (0 para manter): ");
        int idade = scanner.nextInt();
        scanner.nextLine();
        if (idade != 0) cliente.setIdade(idade);

        clienteService.atualizarCliente(clienteId, cliente);
        System.out.println("Cliente atualizado com sucesso!");
    }

    private static void gerenciarFuncionarios(Scanner scanner) {
        System.out.println("Gerenciando Funcionários...");
        // Adicione as operações de adicionar/remover/atualizar funcionários conforme necessário
    }

    private static void configurarRegrasEmprestimo(Scanner scanner) {
        System.out.print("Nova duração de empréstimo em dias: ");
        int duracao = scanner.nextInt();
        configService.configurarDiasParaDevolucao(duracao);
        System.out.println("Configuração de regras de empréstimo atualizada com sucesso!");
    }

    private static void adicionarLivro(Scanner scanner) {
        System.out.print("Título do Livro: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();
        System.out.print("Quantidade Disponível: ");
        int quantidadeDisponivel = scanner.nextInt();
        scanner.nextLine();

        Livro livro = new Livro(null, titulo, autor, categoria, quantidadeDisponivel);
        livroService.adicionarLivro(livro);
        System.out.println("Livro adicionado com sucesso!");
    }

    private static void atualizarLivro(Scanner scanner) {
        System.out.print("ID do Livro a editar: ");
        Long livroId = scanner.nextLong();
        scanner.nextLine();
        Livro livro = livroService.buscarLivroPorId(livroId);
        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return;
        }
        System.out.print("Novo título (deixe em branco para manter): ");
        String titulo = scanner.nextLine();
        if (!titulo.isBlank()) livro.setTitulo(titulo);

        System.out.print("Nova quantidade (0 para manter): ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();
        if (quantidade != 0) livro.setQuantidadeDisponivel(quantidade);

        livroService.atualizarLivro(livroId, livro);
        System.out.println("Livro atualizado com sucesso!");
    }
}
