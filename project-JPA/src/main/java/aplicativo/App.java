package aplicativo;

import dominio.Cliente;
import jakarta.persistence.EntityManager;
import util.JPAUtil;

import java.util.List;

public class App {
    public static void main(String[] args){
        EntityManager em = JPAUtil.getEntityManager();

//        Cliente clienteT2 = new Cliente();
//        clienteT2.setNome("Juquinha");
//
//        em.getTransaction().begin();
//        em.persist(clienteT2);
//        em.getTransaction().commit();
//        System.out.println("Cliente criado com sucesso");
//
//        Cliente resultCliente =  em.find(Cliente.class, 2);
//
//        Cliente clienteMidificado =  new Cliente();
//        clienteMidificado.setId(2L);
//        clienteMidificado.setNome("Miguel Garcia");
//
////        System.out.println(resultCliente);
//        em.getTransaction().begin();
//        em.merge(clienteMidificado);
//        em.getTransaction().commit();


        List<Cliente> clientes = em.createQuery("SELECT c FROM Cliente c", Cliente.class)
                .getResultList();

        for (Cliente c : clientes) {
            System.out.println(c.getNome());
        }
    }
}
