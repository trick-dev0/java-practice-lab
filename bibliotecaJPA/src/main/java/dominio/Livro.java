package dominio;

import java.util.List;
import java.util.Objects;

public class Livro {
    private Long id;
    private String titulo;
    private int anoPublicacao;
    List<Autor> autores;

    public Livro() {
    }

    public Livro(Long id, String titulo, int anoPublicacao, List<Autor> autores) {
        this.id = id;
        this.titulo = titulo;
        this.anoPublicacao = anoPublicacao;
        this.autores = autores;
    }

    public Livro(Long id, String titulo, int anoPublicacao) {
        this.id = id;
        this.titulo = titulo;
        this.anoPublicacao = anoPublicacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Livro livro = (Livro) o;
        return Objects.equals(id, livro.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return titulo + ", " + anoPublicacao + ", Autor(a)" + autores + " - id: " + id;
    }
}
