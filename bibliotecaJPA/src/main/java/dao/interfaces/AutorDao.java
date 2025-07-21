package dao.interfaces;

import dominio.Autor;

import java.util.List;

public interface AutorDao {
    void inserirAutor(Autor autor);
    void atualizarAutor(Integer id, String name);
    void deletarAutor(int id);
    List<Autor> listarAutores();
    Autor buscarAutor(int id);
}
