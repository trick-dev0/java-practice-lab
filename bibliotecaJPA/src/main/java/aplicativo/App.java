package aplicativo;

import dominio.Autor;
import dominio.Livro;
import jakarta.persistence.EntityManager;
import util.JPAUtil;

import java.util.List;

public class App {
    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();

        Autor a = new Autor(2L, "jackson maiben");
        Autor a2 = new Autor(3L, "Freida mAC");

        Livro novoLivro = new Livro(1L, "As viagens de Loacken", 2021, List.of(a, a2));

        System.out.println(novoLivro);

    }
}
