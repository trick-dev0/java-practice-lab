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
    public void atualizarCliente(Integer id, String name) {
        try{
            em.getTransaction().begin();
            // Busco um cliente pelo id e o salvo numa variavel do tipo CLIENTE
            Cliente cliente = em.find(Cliente.class, id);
            // seto um nome novo para esse cliente
            cliente.setNome(name);

            // atualizo o banco de dados com o nome do novo cliente
            em.merge(cliente);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void deletarCliente(Integer id) {
        try{
            em.getTransaction().begin();
            Cliente cliente = em.find(Cliente.class, id);
            em.remove(cliente);
            em.getTransaction().commit();
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }


    @Override
    public List<Cliente> listarCliente() {
        List<Cliente> lista;
        try{
            em.getTransaction().begin();
            lista = em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
            em.getTransaction().commit();
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return  lista;
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
