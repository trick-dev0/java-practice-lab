package imp;

import dao.interfaces.EmprestimoDao;
import dominio.Emprestimo;
import dominio.Livro;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

public class EmprestimoDaoJpa implements EmprestimoDao {
    EntityManager em;

    public EmprestimoDaoJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public void adicionarEmprestimo(Emprestimo emprestimo) {
        try{
            em.getTransaction().begin();
            em.persist(emprestimo);
            em.getTransaction().commit();
        }catch(Exception e){
            em.getTransaction().rollback();
        }
    }

    @Override
    public void deletarEmprestimo(Integer id) {
        try{
            em.getTransaction().begin();
            Emprestimo emprestimo = em.find(Emprestimo.class, id);

            em.remove(emprestimo);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
        }
    }

    @Override
    public void atualizarEmprestimo(Integer id, Livro livro) {

    }

    @Override
    public List<Emprestimo> listarEmprestimo() {
        List<Emprestimo> emp = null;

        try{
            em.getTransaction().begin();

            emp = em.createQuery("SELECT e FROM Emprestimo e", Emprestimo.class)
                    .getResultList();

            em.getTransaction().commit();
        }catch(Exception e){
            em.getTransaction().rollback();
        }
        return emp;
    }

    @Override
    public Emprestimo buscarEmprestimo(int id) {
        Emprestimo emp = null;
        try{
            em.getTransaction().begin();
            emp = em.find(Emprestimo.class, id);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return emp;
    }
}
