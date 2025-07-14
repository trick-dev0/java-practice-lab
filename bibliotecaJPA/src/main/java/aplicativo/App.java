package aplicativo;

import dao.LivroDao;
import dominio.Autor;
import dominio.Livro;
import jakarta.persistence.EntityManager;
import jdk.swing.interop.SwingInterOpUtils;
import util.JPAUtil;

import java.util.List;

public class App {
    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();

        Autor autorx = em.find(Autor.class, 3);


        em.close();
    }
}
