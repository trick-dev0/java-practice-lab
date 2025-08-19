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
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();


        JPAUtil.closeFactory();
    }
}
