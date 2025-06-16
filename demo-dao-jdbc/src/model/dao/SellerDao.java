package model.dao;

import model.entities.Department;
import model.entities.Seller;

import java.util.List;

public interface SellerDao {
    void insert(Seller obj); // Inserir um vendedor
    void update(Seller obj); // Att um vendedor
    void deleteById(Integer id); // Deletar umm vendedor atarvés do ID
    Seller findById(Integer id); //Retorna um object vendedor
    List<Seller> findAll(); // Retorna uma lista com todos os vendedor cadastrados no banco
    List<Seller> findByDepartment(Department department);
}
