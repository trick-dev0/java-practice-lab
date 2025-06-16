package model.impl;

import db.DB;
import db.exception.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.*;
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
        PreparedStatement st = null;

        String comandSql = "INSERT INTO seller \n" +
                "(Name, Email, BirthDate, BaseSalary, DepartmentId)  \n" +
                "VALUES  \n" +
                "(?, ?, ?, ?, ?)";

        try {
            st = conn.prepareStatement(comandSql, Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getName());
            st.setString(2, obj.getEmail());
            // Tenho que  converter LocalDate para java.sql.Date
            st.setDate(3, java.sql.Date.valueOf(obj.getBirthday()));
            st.setDouble(4, obj.getBaseSalary());
            st.setInt(5, obj.getDepartment().getId());

            int rowsAffected = st.executeUpdate();

            // Teste para validar se foi feito alteração
            if(rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if(rs.next()) {
                    // Pega o ID gerado
                    int id = rs.getInt(1);
                    //Insere o ID no Objeto Seller
                    obj.setId(id);
                }
                DB.closeResultSet(rs);
            }else{
                throw new DbException("Error inesperado, nenhuma linha alterada!");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally{
            DB.closeStatment(st);
        }
    }

    @Override
    public void update(Seller obj) {
        PreparedStatement st = null;

        String comandSql = "UPDATE seller  \n" +
                "SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ?  \n" +
                "WHERE Id = ? ";

        try {
            st = conn.prepareStatement(comandSql);

            st.setString(1, obj.getName());
            st.setString(2, obj.getEmail());
            // Tenho que  converter LocalDate para java.sql.Date
            st.setDate(3, java.sql.Date.valueOf(obj.getBirthday()));
            st.setDouble(4, obj.getBaseSalary());
            st.setInt(5, obj.getDepartment().getId());
            st.setInt(6, obj.getId());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally{
            DB.closeStatment(st);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        String comandSql = "DELETE FROM seller  \n" +
                "WHERE Id = ? ";
        try{
            st = conn.prepareStatement(comandSql);

            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
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
        PreparedStatement st = null; //  usado para preparar a consulta SQL
        ResultSet rs = null; // usado para armazenar o resultado da consulta.

        String comandoSql = "SELECT seller.*,department.Name as DepName  \n" +
                "FROM seller INNER JOIN department  \n" +
                "ON seller.DepartmentId = department.Id \n" +
                "ORDER BY Name";

        try{
            st = conn.prepareStatement(comandoSql);
            rs = st.executeQuery();

            // Guarda os vendedores encontrados
            List<Seller> list = new ArrayList<>();
            // Evita duplicação de objetos Department, como se fosse um cache
            Map<Integer, Department> map = new HashMap<>();

            while (rs.next()) {
                // Verifica se já conhece o departamento
                /* O "rs.getInt("DepartmentId")" vai retornar o id do Seller
                 * O map.get vai buscar a HashMap se possui um departamente referente a esse id */
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
            // Evita duplicação de objetos Department, como se fosse um cache
            Map<Integer, Department> map = new HashMap<>();

            while (rs.next()) {
                // Verifica se já conhece o departamento
                /* O "rs.getInt("DepartmentId")" vai retornar o id do Seller
                * O map.get vai buscar a HashMap se possui um departamente referente a esse id */
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
