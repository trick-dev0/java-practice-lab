package model.dao;

import model.entities.Department;

import java.util.List;

public interface DepartmentDao {
    void insert(Department obj); // Inserir um departameto
    void update(Department obj); // Att um departamento
    void deleteById(Integer id); // Deletar umm departamento atarvés do ID
    Department findById(Integer id); //Retorna um object Departamento
    List<Department> findAll(); // Retorna uma lista com todos os departamentos cadastrados no banco
}
