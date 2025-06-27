package model.dao;

import model.entities.Contato;

import java.util.List;

public interface ContatoDao {
    void insert(Contato obj); // Inserir um novo contato
    void update(Contato obj); // Atualiza um contato
    void deleteById(Integer id); // Deletar um contato atarvés do ID
    Contato findById(Integer id); //Retorna um object contato
    List<Contato> findAll(); // Retorna uma lista com todos os contatos cadastrados no banco
}
