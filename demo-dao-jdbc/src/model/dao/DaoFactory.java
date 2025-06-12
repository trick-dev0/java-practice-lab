package model.dao;

import db.DB;
import model.impl.SellerDaoJDBC;

public class DaoFactory {

    public static SellerDao createSallerDao(){
        return new SellerDaoJDBC(DB.getConexao());
    }
}
