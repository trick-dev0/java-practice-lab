package aplicativo;

import dao.interfaces.AutorDao;
import dao.interfaces.ClienteDao;
import dao.interfaces.EmprestimoDao;
import dao.interfaces.LivroDao;
import dominio.Autor;
import dominio.Cliente;
import dominio.Emprestimo;
import dominio.Livro;
import imp.AutorDaoJpa;
import imp.ClienteDaoJpa;
import imp.EmprestimoDaoJpa;
import imp.LivroDaoJpa;
import jakarta.persistence.EntityManager;
import util.JPAUtil;

import java.time.LocalDate;

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
        LivroDao livroDao = new LivroDaoJpa(em);

        Autor autor2 = autorDao.buscarAutor(6); // "Camila Ribeiro Nogueira"
        Livro livro1 = new Livro(null, "Coração de Pedra", 2020, autor2);

        //livroDao.inserirLivro(livro1);

        //Livro livroTeste = livroDao.buscarLivro(1);
        //System.out.println(livroTeste);

        //livroDao.atualizarLivro(1, "Dois Mundos, Um Destino");

//        List<Livro> livros = livroDao.listarLivro();
//
//        for (Livro l : livros){
//            System.out.println(l.getTitulo());
//        }
        // =======================================================================
        ClienteDao clienteDao = new ClienteDaoJpa(em);
       // Cliente cliete1 = new Cliente(null, "Patrick Aguiar", "patrick@gmail.com");
       // clienteDao.inserirCliente(cliete1);

        Cliente clienteTeste = clienteDao.buscarCliente(1);
        Livro livroTeste = livroDao.buscarLivro(2);

//        LocalDate dataInicial = LocalDate.now();
//        LocalDate datafianl = dataInicial.plusDays(10);
        EmprestimoDao emprestimoDAO = new EmprestimoDaoJpa(em);

        Emprestimo newEmprestimo = new Emprestimo(null, livroTeste, clienteTeste, LocalDate.now(), LocalDate.now().plusDays(10));
        System.out.println(newEmprestimo.getDataEmprestimo());

        emprestimoDAO.adicionarEmprestimo(newEmprestimo);


        JPAUtil.closeFactory();
    }
}
