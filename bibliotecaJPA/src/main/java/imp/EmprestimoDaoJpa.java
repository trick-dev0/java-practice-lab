package imp;

import dao.interfaces.EmprestimoDao;
import dominio.Emprestimo;
import jakarta.persistence.EntityManager;

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
    public void removerEmprestimo(Emprestimo emprestimo) {

    }

    @Override
    public void atualizarEmprestimo(Emprestimo emprestimo) {

    }

    @Override
    public List<Emprestimo> listarEmprestimo() {
        return List.of();
    }

    @Override
    public Emprestimo buscarEmprestimo(int id) {
        return null;
    }
}
