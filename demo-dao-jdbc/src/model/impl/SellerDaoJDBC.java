package model.impl;

import db.DB;
import db.exception.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SellerDaoJDBC implements SellerDao {
    private Connection conn;

    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Seller obj) {

    }

    @Override
    public void update(Seller obj) {

    }

    @Override
    public void deleteById(Seller id) {

    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        String comandoSql = "SELECT seller.*,department.Name as DepName  \n" +
                "FROM seller INNER JOIN department  \n" +
                "ON seller.DepartmentId = department.Id  \n" +
                "WHERE seller.Id = ? ";

        try{
            st = conn.prepareStatement(comandoSql);
            st.setInt(1, id);
            rs = st.executeQuery();

            // testa para ver se retornou algo, casso não retorna null
            if (rs.next()) {
                // Matodo para instanciar um departamento
                Department dep = instantiateDepartment(rs);

                //Metodo para inatanciar um vendedor com seu respectivo departamento
                Seller sel = instantiateSeller(rs, dep);
                return sel;
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatment(st);
            DB.closeResultSet(rs);
        }
    }

    private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
        Seller sel = new Seller();
        sel.setId(rs.getInt("Id"));
        sel.setName(rs.getString("Name"));
        sel.setEmail(rs.getString("Email"));
        sel.setBaseSalary(rs.getDouble("BaseSalary"));
        sel.setBirthday(rs.getDate("BirthDate").toLocalDate());

        sel.setDepartment(dep);

        return sel;
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("DepartmentId"));
        dep.setName(rs.getString("DepName"));
        return dep;
    }

    @Override
    public List<Seller> findAll() {
        return List.of();
    }
}
