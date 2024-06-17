package Service;

import Models.Livro;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaService {
    private List<Livro> livros;

    public BibliotecaService() {
        this.livros = new ArrayList<>();
    }

    public void cadastrarLivro(String nome, String autor) {
        Livro livro = new Livro(nome, autor);
        livros.add(livro);
    }

    public void editarLivro(String nome, String novoNome, String novoAutor) {
        Livro livro = buscarLivro(nome);
        if (livro != null) {
            livro.setNome(novoNome);
            livro.setAutor(novoAutor);
        }
    }

    public void excluirLivro(String nome) {
        Livro livro = buscarLivro(nome);
        if (livro != null) {
            livros.remove(livro);
        }
    }

    public Livro buscarLivro(String nome) {
        for (Livro livro : livros) {
            if (livro.getNome().equalsIgnoreCase(nome)) {
                return livro;
            }
        }
        return null;
    }

    public List<Livro> listarLivros() {
        return livros;
    }

    public boolean reservarLivro(String nome) {
        Livro livro = buscarLivro(nome);
        if (livro != null && !livro.isStatusReservado()) {
            livro.setStatusReservado(true);
            return true;
        }
        return false;
    }

    public boolean devolverLivro(String nome) {
        Livro livro = buscarLivro(nome);
        if (livro != null && livro.isStatusReservado()) {
            livro.setStatusReservado(false);
            return true;
        }
        return false;
    }
}
