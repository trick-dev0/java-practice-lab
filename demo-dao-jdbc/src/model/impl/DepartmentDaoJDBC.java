package model.impl;

import db.DB;
import db.exception.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
        return null;
    }

    @Override
    public List<Department> findAll() {
        return List.of();
    }
}
