package model.impl;

import db.DB;
import db.exception.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepartmentDaoJDBC implements DepartmentDao {
    Connection conn;

    public DepartmentDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Department obj) {
        PreparedStatement st = null;

        String comandSQL = "INSERT INTO department (Name) VALUES (?)";
        try{
            st = conn.prepareStatement(comandSQL, PreparedStatement.RETURN_GENERATED_KEYS);
            st.setString(1, obj.getName());

            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                // Pega o ID gerado
                ResultSet rs = st.getGeneratedKeys();
                // Avança para a primeira linah do ResultSet
                if(rs.next()){
                    // Obtém o valor da primeira coluna (índice 1) do ResultSet como um inteiro
                    int id = rs.getInt(1);
                    // Atribui o ID recuperado ao objeto (no campo ID)
                    obj.setId(id);
                }
                //Fecha o result Set
                DB.closeResultSet(rs);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally{
            DB.closeStatment(st);
        }
    }

    @Override
    public void update(Department obj) {
        PreparedStatement st = null;
        String comandSQL = "UPDATE department SET Name = ? WHERE Id = ?";

        try{
            st = conn.prepareStatement(comandSQL);
            st.setString(1, obj.getName());
            st.setInt(2, obj.getId());

            int rowsAffected = st.executeUpdate();
            if(rowsAffected > 0){
                System.out.println("Department changed successfully!!");
            }else{
                System.out.println("Error when changing department");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatment(st);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        String comandSQL = "DELETE FROM department WHERE Id = ?";

        try{
            st = conn.prepareStatement(comandSQL);
            st.setInt(1, id);
            int rowsAffected = st.executeUpdate();
            if(rowsAffected > 0){
                System.out.println("Department deleted!!!");
            }else{
                System.out.println("Error deleting department");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        String comandSQL = "SELECT * FROM department WHERE Id = ?";

        try{
            st = conn.prepareStatement(comandSQL);
            st.setInt(1, id);
            rs = st.executeQuery();

            if(rs.next()){
                Department dep = instantiateDepartment(rs);
                return dep;
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally{
            DB.closeResultSet(rs);
            DB.closeStatment(st);
        }
        return null;
    }

    @Override
    public List<Department> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        String comandSql = "SELECT * FROM department";

        try{
            st = conn.prepareStatement(comandSql);
            rs = st.executeQuery();

            //Armazena a lista final de Departmest
            List<Department> deps = new ArrayList<>();
            // Evita duplicatas
            Map<Integer, Department> map = new HashMap<>();
            while(rs.next()){
                // Busca no Map o Departamento com o id desejado
                Department dep = map.get(rs.getInt("Id"));

                // Caso não tenha nenhum, vai instanciar um novo
                if(dep == null){
                    // Instanciando
                    dep = instantiateDepartment(rs);
                    // Adicioan ao Map
                    // O "rs.getInt("Id")" pega o valor do Id na coluna e add  o dep instancializado
                    map.put(rs.getInt("Id"), dep);
                    deps.add(dep);
                }
            }
            return deps;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    public Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department obj = new Department();
        obj.setId(rs.getInt("Id"));
        obj.setName(rs.getString("Name"));
        return obj;
    }
}
