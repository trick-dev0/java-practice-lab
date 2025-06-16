import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Vou implementar uma interface(SellerDao) chamando a classe DaoFactory
        // onde tem uma função que cria um SellerDaoJDBC que contem a parte executavel do crud

        SellerDao sellerDao = DaoFactory.createSallerDao();  // É um obj de SellerDaoJDBC

        System.out.println(" === Teste FinfById === ");
        Seller seller = sellerDao.findById(3);

        System.out.println(seller);

//        System.out.println(" === Teste FinfByDepartment === ");
//        Department department = new Department(2, null);
//        List<Seller> list = sellerDao.findByDepartment(department);
//
//        for (Seller s : list) {
//            System.out.println(s);
//        }
//
//        System.out.println(" === Teste FinfAll === ");
//        List<Seller> list2 = sellerDao.findAll();
//
//        for (Seller s : list2) {
//            System.out.println(s);
//        }
//
//        System.out.println(" === Teste Insert === ");
//
//        Seller seller0 = new Seller(null, "Patrick", "Patrickvendedor@gmail.com", LocalDate.of(2002, 8, 25), 3000.00, department);
//        sellerDao.insert(seller0);
//        System.out.println("Inserted! New Id = " + seller0.getId());

//        System.out.println(" === Teste UpDate === ");
//        Department department2 = new Department(4, null);
//
//        seller = sellerDao.findById(8);
//        seller.setName("Maria");
//        seller.setEmail("maria@gmail.com");
//        seller.setBirthday(LocalDate.of(1990, 12, 25));
//        seller.setDepartment(department2);
//        seller.setBaseSalary(4000.00);
//        sellerDao.update(seller);
//        System.out.println("Updated! New Id = " + seller.getId());

//        System.out.println(" === Test Delete === ");
//        System.out.print("Digite o Id que deja deletar: ");
//        int id = sc.nextInt();
//        sellerDao.deleteById(id);
    }
}