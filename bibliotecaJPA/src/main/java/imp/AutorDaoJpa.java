package imp;

import dao.interfaces.AutorDao;
import dominio.Autor;
import dominio.Cliente;
import jakarta.persistence.EntityManager;
import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.List;

public class AutorDaoJpa implements AutorDao {
    //O objeto EntityManager é o responsável pela pesistencia em JPA
    private EntityManager em;

    public AutorDaoJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public void inserirAutor(Autor autor) {
        try {
            em.getTransaction().begin();
            em.persist(autor);
            em.getTransaction().commit();
        }catch (Exception e) {
            em.getTransaction().rollback();
            // Caso de algum error, nào será salvo no banco de dados
        }
    }

    @Override
    public void atualizarAutor(Integer id, String name) {
        try{
            em.getTransaction().begin();

            Autor aut = em.find(Autor.class, id);
            aut.setNome(name);

            em.merge(aut);
            em.getTransaction().commit();
        }catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public void deletarAutor(int id) {
        try{
            em.getTransaction().begin();
            Autor autor = em.find(Autor.class, id);
            em.remove(autor);
            em.getTransaction().commit();
        }catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public List<Autor> listarAutores() {
        List<Autor> autores = new ArrayList<>();
        try{
            em.getTransaction().begin();
            autores = em.createQuery("SELECT a FROM Autor a", Autor.class).getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return autores;
    }

    @Override
    public Autor buscarAutor(int id) {
        Autor aut;
        try {
            aut = em.find(Autor.class, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return aut;
    }
}
