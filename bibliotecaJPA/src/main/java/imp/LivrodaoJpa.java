package imp;

import dao.interfaces.LivroDao;
import dominio.Livro;
import jakarta.persistence.EntityManager;

import java.util.List;

public class LivrodaoJpa implements LivroDao {
    private EntityManager em;

    public LivrodaoJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public void inserirLivro(Livro livro) {
        try{
            em.getTransaction().begin();
            em.persist(livro);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public void atualizarLivro(Integer id, String name) {
        try{
            em.getTransaction().begin();

            Livro livro = em.find(Livro.class, id);
            livro.setTitulo(name);

            em.merge(livro);
            em.getTransaction().commit();
        }catch(Exception e){
            em.getTransaction().rollback();
        }
    }

    @Override
    public void deletarLivro(int id) {
        try{
            em.getTransaction().begin();
            Livro livro = em.find(Livro.class, id);
            em.remove(livro);
            em.getTransaction().commit();
        }catch(Exception e){
            em.getTransaction().rollback();
        }

    }

    @Override
    public List<Livro> listarLivro() {
        return List.of();
    }

    @Override
    public Livro buscarLivro(int id) {
        Livro livro;
        try{
            livro = em.find(Livro.class, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return livro;
    }
}
