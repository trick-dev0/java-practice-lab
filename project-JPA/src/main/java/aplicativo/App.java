package aplicativo;

import dominio.Cliente;
import jakarta.persistence.EntityManager;
import util.JPAUtil;

public class App {
    public static void main(String[] args){
        EntityManager em = JPAUtil.getEntityManager();

        Cliente clienteT1 = new Cliente();
        clienteT1.setNome("Patrick");

        em.getTransaction().begin();
        em.persist(clienteT1);
        em.getTransaction().commit();
        System.out.println("Cliente criado com sucesso");
    }
}
