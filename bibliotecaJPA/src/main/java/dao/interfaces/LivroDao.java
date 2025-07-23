package dao.interfaces;

import dominio.Livro;

import java.util.List;

public interface LivroDao {
    void inserirLivro(Livro livro);
    void atualizarLivro(Integer id, String name);
    void deletarLivro(int id);
    List<Livro> listarLivro();
    Livro buscarLivro(int id);
}
