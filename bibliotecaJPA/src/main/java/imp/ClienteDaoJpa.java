package imp;

import dao.interfaces.ClienteDao;
import dominio.Cliente;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ClienteDaoJpa implements ClienteDao {
    EntityManager em;

    public ClienteDaoJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public void inserirCliente(Cliente cliente) {
        try{
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
        }catch(Exception e){
            em.getTransaction().rollback();
        }
    }

    @Override
    public void atualizarCliente(Cliente cliente) {

    }

    @Override
    public void excluirCliente(Cliente cliente) {

    }

    @Override
    public List<Cliente> listarCliente() {
        return List.of();
    }

    @Override
    public Cliente buscarCliente(int id) {
        Cliente cli = null;
        try{
            cli = em.find(Cliente.class, id);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return cli;
    }
}
