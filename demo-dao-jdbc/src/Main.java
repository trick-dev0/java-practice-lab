import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Vou implementar uma interface(SellerDao) chamando a classe DaoFactory
        // onde tem uma função que cria um SellerDaoJDBC que contem a parte executavel do crud

        SellerDao sellerDao = DaoFactory.createSallerDao();  // É um obj de SellerDaoJDBC

        System.out.println(" === Teste FinfById === ");
        Seller seller = sellerDao.findById(3);

        System.out.println(seller);
    }
}