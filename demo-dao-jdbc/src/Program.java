import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Criação de um obj DepartmentDaoJDBC atraves de injeçao de dependencia
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        // TEST OK
//        System.out.println(" -------- Test 1: Insert -------- ");
//        Department newDepartment = new Department(null, "Notebooks");
//
//        departmentDao.insert(newDepartment);
//        System.out.println("Create Department!! \n"
//                + "Generated ID " + newDepartment.getId());

//        // TEST OK
//        System.out.println(" -------- Test 2: Update -------- ");
//        // Criando um objeto Department para atualizar
//        Department depAtt = new Department();
//        depAtt.setId(5);
//        depAtt.setName("House cleaning");
//
//        departmentDao.update(depAtt);
//
//        System.out.println(" -------- Test 3: Delete -------- ");
//        System.out.print("Which ID do you want to delete?: ");
//        int id = sc.nextInt();
//        departmentDao.deleteById(id);

//        // TEST OK
//        System.out.println(" -------- Test 4: findById -------- ");
//        Department department = departmentDao.findById(2);
//        System.out.println(department);

        List<Department> deps = departmentDao.findAll();

        for (Department dep : deps) {
            System.out.println(dep);
        }
    }
}
