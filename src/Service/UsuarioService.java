package Service;

import Models.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioService {
    private List<Usuario> usuarios;

    public UsuarioService() {
        this.usuarios = new ArrayList<>();
        // Adicionando usuários iniciais
        usuarios.add(new Usuario("AdminUsuarios", "admin_usuarios@example.com", "admin123", "adminUsuarios"));
        usuarios.add(new Usuario("AdminLivros", "admin_livros@example.com", "admin456", "adminLivros"));
        usuarios.add(new Usuario("Bibliotecaria", "bibliotecaria@example.com", "biblio123", "bibliotecaria"));
    }

    public void criarUsuario(String nome, String email, String senha, String tipo) {
        Usuario usuario = new Usuario(nome, email, senha, tipo);
        usuarios.add(usuario);
    }

    public Usuario buscarUsuario(String email, String senha) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
                return usuario;
            }
        }
        return null;
    }

    public void excluirUsuario(String email) {
        usuarios.removeIf(usuario -> usuario.getEmail().equals(email));
    }

    public void atualizarUsuario(String email, String novoNome, String novaSenha, String novoTipo) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email)) {
                usuario.setNome(novoNome);
                usuario.setSenha(novaSenha);
                usuario.setTipo(novoTipo);
                break;
            }
        }
    }

    public List<Usuario> listarUsuarios() {
        return usuarios;
    }
}
