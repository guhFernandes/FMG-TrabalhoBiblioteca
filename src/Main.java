import Models.*;
import Service.*;

import java.util.Scanner;

public class Main {
    private static BibliotecaService bibliotecaService = new BibliotecaService();
    private static Scanner scanner = new Scanner(System.in);

    private static Usuario admin = new Usuario("Admin", "admin@example.com", "admin123", "admin");
    private static Usuario bibliotecaria = new Usuario("Bibliotecaria", "bibliotecaria@example.com", "biblio123", "bibliotecaria");

    public static void main(String[] args) {
        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Login");
            System.out.println("2. Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    login();
                    break;
                case 2:
                    System.out.println("Saindo...");
                    System.exit(0);
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void login() {
        System.out.println("Digite o email:");
        String email = scanner.nextLine();
        System.out.println("Digite a senha:");
        String senha = scanner.nextLine();

        Usuario usuario = autenticarUsuario(email, senha);
        if (usuario != null) {
            System.out.println("Login bem-sucedido! Bem-vindo, " + usuario.getNome());
            if (usuario.getTipo().equals("admin")) {
                menuAdmin();
            } else if (usuario.getTipo().equals("bibliotecaria")) {
                menuBibliotecaria();
            }
        } else {
            System.out.println("Erro: Email ou senha incorretos.");
        }
    }

    private static Usuario autenticarUsuario(String email, String senha) {
        if (admin.getEmail().equals(email) && admin.getSenha().equals(senha)) {
            return admin;
        } else if (bibliotecaria.getEmail().equals(email) && bibliotecaria.getSenha().equals(senha)) {
            return bibliotecaria;
        }
        return null;
    }

    private static void menuAdmin() {
        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Cadastrar Livro");
            System.out.println("2. Editar Livro");
            System.out.println("3. Excluir Livro");
            System.out.println("4. Buscar Livro");
            System.out.println("5. Listar Livros");
            System.out.println("6. Logout");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    cadastrarLivro();
                    break;
                case 2:
                    editarLivro();
                    break;
                case 3:
                    excluirLivro();
                    break;
                case 4:
                    buscarLivro();
                    break;
                case 5:
                    listarLivros();
                    break;
                case 6:
                    System.out.println("Logout bem-sucedido.");
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void menuBibliotecaria() {
        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Listar Livros");
            System.out.println("2. Reservar Livro");
            System.out.println("3. Devolver Livro");
            System.out.println("4. Buscar Livro");
            System.out.println("5. Logout");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    listarLivros();
                    break;
                case 2:
                    reservarLivro();
                    break;
                case 3:
                    devolverLivro();
                    break;
                case 4:
                    buscarLivro();
                    break;
                case 5:
                    System.out.println("Logout bem-sucedido.");
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void cadastrarLivro() {
        System.out.println("Digite o nome do livro:");
        String nome = scanner.nextLine();
        System.out.println("Digite o autor do livro:");
        String autor = scanner.nextLine();

        bibliotecaService.cadastrarLivro(nome, autor);
        System.out.println("Livro cadastrado com sucesso!");
    }

    private static void editarLivro() {
        System.out.println("Digite o nome do livro a ser editado:");
        String nome = scanner.nextLine();
        System.out.println("Digite o novo nome do livro:");
        String novoNome = scanner.nextLine();
        System.out.println("Digite o novo autor do livro:");
        String novoAutor = scanner.nextLine();

        bibliotecaService.editarLivro(nome, novoNome, novoAutor);
        System.out.println("Livro editado com sucesso!");
    }

    private static void excluirLivro() {
        System.out.println("Digite o nome do livro a ser excluído:");
        String nome = scanner.nextLine();

        bibliotecaService.excluirLivro(nome);
        System.out.println("Livro excluído com sucesso!");
    }

    private static void buscarLivro() {
        System.out.println("Digite o nome do livro a ser buscado:");
        String nome = scanner.nextLine();

        Livro livro = bibliotecaService.buscarLivro(nome);
        if (livro != null) {
            livro.exibirDetalhes();
        } else {
            System.out.println("Livro não encontrado.");
        }
    }

    private static void listarLivros() {
        System.out.println("Livros cadastrados:");
        for (Livro livro : bibliotecaService.listarLivros()) {
            livro.exibirDetalhes();
        }
    }

    private static void reservarLivro() {
        System.out.println("Digite o nome do livro a ser reservado:");
        String nome = scanner.nextLine();

        boolean sucesso = bibliotecaService.reservarLivro(nome);
        if (sucesso) {
            System.out.println("Livro reservado com sucesso!");
        } else {
            System.out.println("Erro ao reservar livro. Talvez ele já esteja reservado.");
        }
    }

    private static void devolverLivro() {
        System.out.println("Digite o nome do livro a ser devolvido:");
        String nome = scanner.nextLine();

        boolean sucesso = bibliotecaService.devolverLivro(nome);
        if (sucesso) {
            System.out.println("Livro devolvido com sucesso!");
        } else {
            System.out.println("Erro ao devolver livro. Talvez ele não esteja reservado.");
        }
    }
}
