package aplicativo;

import dao.interfaces.AutorDao;
import dao.interfaces.LivroDao;
import dominio.Autor;
import dominio.Livro;
import imp.AutorDaoJpa;
import imp.LivrodaoJpa;
import jakarta.persistence.EntityManager;
import util.JPAUtil;

import java.util.List;

public class App {
    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();

        //Criando AUTORES
        Autor autor = new Autor(null, "Luciana Correia Brito");
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

//        // ==== Teste Listar autores ====
//        List<Autor> listAutor = autorDao.listarAutores();
//        for (Autor a1 : listAutor) {
//            System.out.println(a1.getNome());
//        }
// ===========================================================================
        LivroDao livroDao = new LivrodaoJpa(em);

        Autor autor2 = autorDao.buscarAutor(6); // "Camila Ribeiro Nogueira"
        Livro livro1 = new Livro(null, "Coração de Pedra", 2020, autor2);

        //livroDao.inserirLivro(livro1);

        //Livro livroTeste = livroDao.buscarLivro(1);
        //System.out.println(livroTeste);

        livroDao.atualizarLivro(1, "Dois Mundos, Um Destino");

        JPAUtil.closeFactory();
    }
}
