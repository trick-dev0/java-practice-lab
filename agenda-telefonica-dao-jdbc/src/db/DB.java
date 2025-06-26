package db;

import db.exception.DbException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {
    // Static para acessar diretamente pela classe
    private static  Connection conn = null;

    // Metodo para conectar com o banco de dados
    public static Connection getConnection() {
        
        // Verifica se já existe uma conexao ativa, caso não, cria uma 
        if(conn == null) {
            try{
                Properties prop = carregarPropriedades();
                String url = prop.getProperty("dburl");
                String user = prop.getProperty("user");
                String pass = prop.getProperty("password");

                conn = DriverManager.getConnection(url,user, pass);
                System.out.println("Conectado ao MySQL!\n");

            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return conn;
    }
    
    private static Properties carregarPropriedades() throws IOException {
        // Armazena as informações do arquivo
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("C:\\Users\\aguia\\Dropbox\\Projetos GitHub\\Java\\agenda-telefonica-dao-jdbc\\db.properties"); // O `FileInputStream` é uma classe do Java usada para ler dados de arquivos como .properties, .txt, .bin
        prop.load(fis);
        return prop;
    }

    public static void closeConnection() {
        if(conn != null) {
            try{
                conn.close();
                System.out.println("Conexão encerrada com sucesso!\n");
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }
}
