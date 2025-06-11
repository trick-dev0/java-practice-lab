package model.dao;

import model.impl.SellerDaoJDBC;

public class DaoFactory {

    public static SellerDao createSallerDao(){
        return new SellerDaoJDBC();
    }
}
