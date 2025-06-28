package db;

import db.exception.DbException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {
    private static Connection conn = null;

    public static Connection getConexao() {
        if (conn == null) {
            try {
                Properties props = carregarPropriedades();
                String url = props.getProperty("dburl");
                String user = props.getProperty("user");
                String password = props.getProperty("password");

                conn = DriverManager.getConnection(url, user, password);
                System.out.println("Conectado ao MySQL!");
            } catch (SQLException e) {
                System.err.println("Erro de conexão com o banco de dados:");
                e.printStackTrace();
            } catch (IOException e) {
                System.err.println("Erro ao carregar o arquivo db.properties:");
                e.printStackTrace();
            }
        }
        return conn;
    }

    private static Properties carregarPropriedades() throws IOException {
        Properties props = new Properties();
        FileInputStream fis = new FileInputStream("C:\\Users\\aguia\\Dropbox\\Projetos GitHub\\Java\\demo-dao-jdbc\\db.propertiesi"); // caminho do arquivo
        props.load(fis);
        return props;
    }

    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Conexão encerrada");
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    public static void closeStatment(Statement st){
        if(st != null){
            try{
                st.close();
            }catch(SQLException e){
                throw new DbException(e.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet rs){
        if(rs != null){
            try{
                rs.close();
            }catch(SQLException e){
                throw new DbException(e.getMessage());
            }
        }
    }
}

