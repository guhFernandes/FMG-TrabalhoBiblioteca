import Models.*;
import Service.*;

import java.util.Scanner;

public class Main {
    private static BibliotecaService bibliotecaService = new BibliotecaService();
    private static UsuarioService usuarioService = new UsuarioService();
    private static Scanner scanner = new Scanner(System.in);
    private static Usuario usuarioLogado;

    public static void main(String[] args) {
        while (true) {
            exibirMenuPrincipal();
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    limparTela();
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

    private static void exibirMenuPrincipal() {
        System.out.println("=====================================");
        System.out.println("Bem-vindo ao Sistema de Biblioteca");
        System.out.println("=====================================");
        System.out.println("Escolha uma opção:");
        System.out.println("1. Login");
        System.out.println("2. Sair");
        System.out.println("=====================================");
        System.out.print("Opção: ");
    }

    private static void login() {
        System.out.print("Digite o email: ");
        String email = scanner.nextLine();
        System.out.print("Digite a senha: ");
        String senha = scanner.nextLine();

        Usuario usuario = usuarioService.buscarUsuario(email, senha);
        if (usuario != null) {
            limparTela();
            usuarioLogado = usuario;
            System.out.println("Login bem-sucedido! Bem-vindo, " + usuario.getNome());
            switch (usuario.getTipo()) {
                case "adminUsuarios":
                    menuAdminUsuarios();
                    break;
                case "adminLivros":
                    menuAdminLivros();
                    break;
                case "bibliotecaria":
                    menuBibliotecaria();
                    break;
                default:
                    System.out.println("Erro: Tipo de usuário desconhecido.");
            }
        } else {
            System.out.println("Erro: Email ou senha incorretos.");
        }
    }

    private static void menuAdminUsuarios() {
        while (true) {
            System.out.println("=====================================");
            System.out.println("Menu do Administrador de Usuários");
            System.out.println("=====================================");
            System.out.println("Escolha uma opção:");
            System.out.println("1. Criar Usuário");
            System.out.println("2. Listar Usuários");
            System.out.println("3. Atualizar Usuário");
            System.out.println("4. Excluir Usuário");
            System.out.println("5. Logout");
            System.out.println("=====================================");
            System.out.print("Opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    limparTela();
                    criarUsuario();
                    break;
                case 2:
                    limparTela();
                    listarUsuarios();
                    break;
                case 3:
                    limparTela();
                    atualizarUsuario();
                    break;
                case 4:
                    limparTela();
                    excluirUsuario();
                    break;
                case 5:
                    limparTela();
                    System.out.println("Logout bem-sucedido.");
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void menuAdminLivros() {
        while (true) {
            System.out.println("=====================================");
            System.out.println("Menu do Administrador de Livros");
            System.out.println("=====================================");
            System.out.println("Escolha uma opção:");
            System.out.println("1. Cadastrar Livro");
            System.out.println("2. Editar Livro");
            System.out.println("3. Excluir Livro");
            System.out.println("4. Buscar Livro");
            System.out.println("5. Listar Livros");
            System.out.println("6. Logout");
            System.out.println("=====================================");
            System.out.print("Opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    limparTela();
                    cadastrarLivro();
                    break;
                case 2:
                    limparTela();
                    editarLivro();
                    break;
                case 3:
                    limparTela();
                    excluirLivro();
                    break;
                case 4:
                    limparTela();
                    buscarLivro();
                    break;
                case 5:
                    limparTela();
                    listarLivros();
                    break;
                case 6:
                    limparTela();
                    System.out.println("Logout bem-sucedido.");
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void menuBibliotecaria() {
        while (true) {
            System.out.println("=====================================");
            System.out.println("Menu da Bibliotecária");
            System.out.println("=====================================");
            System.out.println("Escolha uma opção:");
            System.out.println("1. Listar Livros");
            System.out.println("2. Reservar Livro");
            System.out.println("3. Devolver Livro");
            System.out.println("4. Buscar Livro");
            System.out.println("5. Logout");
            System.out.println("=====================================");
            System.out.print("Opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    limparTela();
                    listarLivros();
                    break;
                case 2:
                    limparTela();
                    reservarLivro();
                    break;
                case 3:
                    limparTela();
                    devolverLivro();
                    break;
                case 4:
                    limparTela();
                    buscarLivro();
                    break;
                case 5:
                    limparTela();
                    System.out.println("Logout bem-sucedido.");
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void cadastrarLivro() {
        System.out.print("Digite o nome do livro: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o autor do livro: ");
        String autor = scanner.nextLine();

        bibliotecaService.cadastrarLivro(nome, autor);
        System.out.println("Livro cadastrado com sucesso!");
    }

    private static void editarLivro() {
        System.out.print("Digite o nome do livro a ser editado: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o novo nome do livro: ");
        String novoNome = scanner.nextLine();
        System.out.print("Digite o novo autor do livro: ");
        String novoAutor = scanner.nextLine();

        bibliotecaService.editarLivro(nome, novoNome, novoAutor);
        System.out.println("Livro editado com sucesso!");
    }

    private static void excluirLivro() {
        System.out.print("Digite o nome do livro a ser excluído: ");
        String nome = scanner.nextLine();

        bibliotecaService.excluirLivro(nome);
        System.out.println("Livro excluído com sucesso!");
    }

    private static void buscarLivro() {
        System.out.print("Digite o nome do livro a ser buscado: ");
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

    private static void criarUsuario() {
        System.out.print("Digite o nome do usuário: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o email do usuário: ");
        String email = scanner.nextLine();
        System.out.print("Digite a senha do usuário: ");
        String senha = scanner.nextLine();
        System.out.print("Digite o tipo do usuário (adminUsuarios/adminLivros/bibliotecaria): ");
        String tipo = scanner.nextLine();

        usuarioService.criarUsuario(nome, email, senha, tipo);
        System.out.println("Usuário criado com sucesso!");
    }

    private static void listarUsuarios() {
        System.out.println("Usuários cadastrados:");
        for (Usuario usuario : usuarioService.listarUsuarios()) {
            System.out.println("=====================================");
            System.out.println("Nome: " + usuario.getNome());
            System.out.println("Email: " + usuario.getEmail());
            System.out.println("Tipo: " + usuario.getTipo());
            System.out.println("=====================================");
        }
    }

    private static void atualizarUsuario() {
        System.out.print("Digite o email do usuário a ser atualizado: ");
        String email = scanner.nextLine();
        System.out.print("Digite o novo nome do usuário: ");
        String novoNome = scanner.nextLine();
        System.out.print("Digite a nova senha do usuário: ");
        String novaSenha = scanner.nextLine();
        System.out.print("Digite o novo tipo do usuário (adminUsuarios/adminLivros/bibliotecaria): ");
        String novoTipo = scanner.nextLine();

        usuarioService.atualizarUsuario(email, novoNome, novaSenha, novoTipo);
        System.out.println("Usuário atualizado com sucesso!");
    }

    private static void excluirUsuario() {
        System.out.print("Digite o email do usuário a ser excluído: ");
        String email = scanner.nextLine();

        usuarioService.excluirUsuario(email);

        if (usuarioLogado != null && usuarioLogado.getEmail().equals(email)) {
            System.out.println("Você excluiu seu próprio usuário. Logout automático.");
            usuarioLogado = null;
            return;
        }

        System.out.println("Usuário excluído com sucesso!");
    }

    private static void reservarLivro() {
        System.out.print("Digite o nome do livro a ser reservado: ");
        String nome = scanner.nextLine();

        boolean sucesso = bibliotecaService.reservarLivro(nome);
        if (sucesso) {
            System.out.println("Livro reservado com sucesso!");
        } else {
            System.out.println("Erro ao reservar livro. Talvez ele já esteja reservado.");
        }
    }

    private static void devolverLivro() {
        System.out.print("Digite o nome do livro a ser devolvido: ");
        String nome = scanner.nextLine();

        boolean sucesso = bibliotecaService.devolverLivro(nome);
        if (sucesso) {
            System.out.println("Livro devolvido com sucesso!");
        } else {
            System.out.println("Erro ao devolver livro. Talvez ele não esteja reservado.");
        }
    }

    private static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
