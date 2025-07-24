package imp;

import dao.interfaces.LivroDao;
import dominio.Livro;
import jakarta.persistence.EntityManager;

import java.util.List;

public class LivroDaoJpa implements LivroDao {
    private EntityManager em;

    public LivroDaoJpa(EntityManager em) {
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
        List<Livro> listLivros;
        try{
            em.getTransaction().begin();
            listLivros = em.createQuery("SELECT l FROM Livro l", Livro.class).getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return listLivros;
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
