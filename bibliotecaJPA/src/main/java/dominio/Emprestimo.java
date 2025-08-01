package dominio;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "emprestimos")
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "livro_id")
    private Livro livroEmprestado;


    @ManyToOne  // Por se tratar de uma tabela que não é tipo primitivo, temos que fazer os relacionamentos
    @JoinColumn(name = "cliente_id")
    private Cliente clienteEmprestado;

    @Column(name = "data_emprestimo")
    private LocalDate dataEmprestimo;
    @Column(name = "data_devolucao")
    private LocalDate dataDevolucao;

    public Emprestimo() {
    }

    public Emprestimo(Long id,  Livro livroEmprestado, Cliente clienteEmprestado, LocalDate dataEmprestimo, LocalDate dataDevolucao) {
        this.id = id;
        this.livroEmprestado = livroEmprestado;
        this.clienteEmprestado = clienteEmprestado;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Livro getLivroEmprestado() {
        return livroEmprestado;
    }

    public void setLivroEmprestado(Livro livroEmprestado) {
        this.livroEmprestado = livroEmprestado;
    }

    public Cliente getClienteEmprestado() {
        return clienteEmprestado;
    }

    public void setClienteEmprestado(Cliente clienteEmprestado) {
        this.clienteEmprestado = clienteEmprestado;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Emprestimo that = (Emprestimo) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Emprestimo (" + id + ")"
                + "\nLivro: " + livroEmprestado
                + "\nCliente: " + clienteEmprestado
                + "\nData de Emprestimo: " + dataEmprestimo
                + "\nData de Devolucao: " + dataDevolucao;
    }
}
