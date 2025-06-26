import db.DB;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {

        System.out.println("Teste de conexão com o banco de dados:\n");
        // INICIAR
        Connection conn = DB.getConnection();

        // ENCERRAR
        DB.closeConnection();
    }
}