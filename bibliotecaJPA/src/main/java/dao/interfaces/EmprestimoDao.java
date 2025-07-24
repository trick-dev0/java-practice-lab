package dao.interfaces;

import dominio.Emprestimo;

import java.util.List;

public interface EmprestimoDao {
    void adicionarEmprestimo(Emprestimo emprestimo);
    void removerEmprestimo(Emprestimo emprestimo);
    void atualizarEmprestimo(Emprestimo emprestimo);
    List<Emprestimo> listarEmprestimo();
    Emprestimo buscarEmprestimo(int id);
}
