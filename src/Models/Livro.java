package Models;

public class Livro {
    private String nome;
    private String autor;
    private boolean statusReservado;

    public Livro(String nome, String autor) {
        this.nome = nome;
        this.autor = autor;
        this.statusReservado = false;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public boolean isStatusReservado() {
        return statusReservado;
    }

    public void setStatusReservado(boolean statusReservado) {
        this.statusReservado = statusReservado;
    }

    public void exibirDetalhes() {
        String reservado = statusReservado ? "Sim" : "Não";
        System.out.println("=====================================");
        System.out.println("Nome: " + nome);
        System.out.println("Autor: " + autor);
        System.out.println("Reservado: " + reservado);
        System.out.println("=====================================");
    }
}
