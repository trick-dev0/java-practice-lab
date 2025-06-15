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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                // Metodo para instanciar um departamento
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


    // Retorna uma lista de vendedores cujo selecione um departamento especifico pelo ID
    // Recebe um Departamento como parâmetro

    @Override
    public List<Seller> findByDepartment(Department department) {
        PreparedStatement st = null; //  usado para preparar a consulta SQL
        ResultSet rs = null; // usado para armazenar o resultado da consulta.

        String comandoSql = "SELECT seller.*,department.Name as DepName  \n" +
                "FROM seller INNER JOIN department  \n" +
                "ON seller.DepartmentId = department.Id \n" +
                "WHERE DepartmentId = ? \n" +
                "ORDER BY Name";

        try{
            st = conn.prepareStatement(comandoSql);
            st.setInt(1, department.getId());
            rs = st.executeQuery();

            // Guarda os vendedores encontrados
            List<Seller> list = new ArrayList<>();
            // Evita duplicação de objetos Department
            Map<Integer, Department> map = new HashMap<>();

            while (rs.next()) {
                // Verifica se já conhece o departamento
                Department dep = map.get(rs.getInt("DepartmentId"));

                if(dep == null) {
                    dep = instantiateDepartment(rs);
                    map.put(rs.getInt("DepartmentId"), dep);
                }

                Seller sel = instantiateSeller(rs, dep);
                list.add(sel);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatment(st);
            DB.closeResultSet(rs);
        }
    }
}
