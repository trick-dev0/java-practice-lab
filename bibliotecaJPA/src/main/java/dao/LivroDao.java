package dao;

import dominio.Livro;
import jakarta.persistence.EntityManager;

public class LivroDao {
    private EntityManager em;

    public LivroDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrarLivro(Livro livro){
        this.em.persist(livro);
    }

}
