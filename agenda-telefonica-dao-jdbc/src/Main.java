import db.DB;
import model.dao.ContatoDao;
import model.dao.DaoFactory;
import model.entities.Contato;
import model.imple.ContatoDaoJDBC;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        System.out.println("Teste de conexão com o banco de dados:\n");
        // INICIAR
        Connection conn = DB.getConnection();

        System.out.println("  ======  Teste 1: INSERT  =====  ");
        Contato pessoa1 = new Contato(null, "Patrick", "77988189417", "patrick@gmail.com");
        Contato pessoa2 = new Contato(null, "Juca", "(55) 88998985", "juca@gmail.com");

        ContatoDao contatoTeste = DaoFactory.conexaoCantatoDao();
        contatoTeste.insert(pessoa1);
        contatoTeste.insert(pessoa2);



        // ENCERRAR
        DB.closeConnection();
    }
}