import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Vou implementar uma interface(SellerDao) chamando a classe DaoFactory
        // onde tem uma função que cria um SellerDaoJDBC que contem a parte executavel do crud

        SellerDao sellerDao = DaoFactory.createSallerDao();  // É um obj de SellerDaoJDBC

        System.out.println(" === Teste FinfById === ");
        Seller seller = sellerDao.findById(3);

        System.out.println(seller);

        System.out.println(" === Teste FinfByDepartment === ");
        Department department = new Department(2, null);
        List<Seller> list = sellerDao.findByDepartment(department);

        for (Seller s : list) {
            System.out.println(s);
        }

    }
}