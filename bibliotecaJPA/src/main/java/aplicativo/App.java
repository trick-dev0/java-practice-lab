package aplicativo;

import dao.interfaces.AutorDao;
import dominio.Autor;
import dominio.Livro;
import imp.AutorDaoJpa;
import jakarta.persistence.EntityManager;
import util.JPAUtil;

import java.util.List;

public class App {
    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();

        //Criando AUTORES
        Autor autor = new Autor(null, "Camila Ribeiro Nogueira");
        AutorDao autorDao = new AutorDaoJpa(em);


//        // ==== Teste Insert ====
//        autorDao.inserirAutor(autor);

//        // ==== Teste findById ====
//        Autor resultAutor = autorDao.buscarAutor(5);
//        System.out.println(resultAutor);

//        // ==== Teste Update ====
//        autorDao.atualizarAutor(5, "Camila Ribeiro Nogueira");

//        // ==== Teste Delet ====
//        autorDao.deletarAutor(3);

        // ==== Teste Listar autores ====
        List<Autor> listAutor = autorDao.listarAutores();
        for (Autor a1 : listAutor) {
            System.out.println(a1.getNome());
        }

    }
}
