package dao.interfaces;

import dominio.Emprestimo;
import dominio.Livro;

import java.util.List;

public interface EmprestimoDao {
    void adicionarEmprestimo(Emprestimo emprestimo);
    void deletarEmprestimo(Integer id);
    void atualizarEmprestimo(Integer id, Livro livro);
    List<Emprestimo> listarEmprestimo();
    Emprestimo buscarEmprestimo(int id);
}
