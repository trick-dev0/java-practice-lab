package model.dao;

import db.DB;
import model.imple.ContatoDaoJDBC;

public class DaoFactory {
    public static ContatoDao conexaoCantatoDao (){
        return new ContatoDaoJDBC(DB.getConnection());
    }

    // Criando uma DaoFactory, protegemos o código.
    // Caso futuramente decidimos não utilizar o JDBC, não precisamos alterar no codigo inteiro
}
