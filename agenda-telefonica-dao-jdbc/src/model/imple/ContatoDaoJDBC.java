package model.imple;

import db.DB;
import db.exception.DbException;
import model.dao.ContatoDao;
import model.entities.Contato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContatoDaoJDBC implements ContatoDao {
    Connection conn;

    public ContatoDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Contato obj) {
        String comandoSql = "INSERT INTO contatos (nome, telefone, email) VALUES (?, ?, ?)";

        try(PreparedStatement st = conn.prepareStatement(comandoSql, PreparedStatement.RETURN_GENERATED_KEYS)){
            st.setString(1, obj.getNome());
            st.setString(2, obj.getTelefone());
            st.setString(3, obj.getEmail());

            // Capturo a quantidade de linhas afetadas com o comando `PreparedStatement.RETURN_GENERATED_KEYS`
            int linhasAfetadas = st.executeUpdate();

            if(linhasAfetadas > 0){
                ResultSet rs = st.getGeneratedKeys(); // Pega o ID gerado

                // Avança para a proxima linha
                if(rs.next()){
                    int idContato = rs.getInt(1);// Obtém o valor da primeira coluna (índice 1) do ResultSet como um inteiro
                    obj.setId(idContato);// Atribui o ID recuperado ao objeto (no campo ID)
                }
                rs.close();
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void update(Contato obj) {
        PreparedStatement st = null;
        String comandoSql = "UPDATE contatos SET nome = ?, telefone = ?, email = ? WHERE id = ?";

        try {
            st = conn.prepareStatement(comandoSql);
            st.setString(1, obj.getNome());
            st.setString(2, obj.getTelefone());
            st.setString(3, obj.getEmail());
            st.setInt(4, obj.getId());
            st.executeUpdate();
            System.out.println("Update concluido com sucesso!");
        }catch (SQLException e){
            throw new DbException("Error ao fazer o Update: " + e.getMessage());
        }finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        String comandoSql = "DELETE FROM contatos WHERE id = ?";

        try{
            st = conn.prepareStatement(comandoSql);
            st.setInt(1, id);
            st.executeUpdate();
            System.out.println("Contato removido com sucesso!");
        } catch (SQLException e) {
            throw new DbException("Error ao remover contato: " + e.getMessage());
        }finally {
            DB.closeStatement(st);
        }

    }

    @Override
    public Contato findById(Integer id) {
        // Nessa caso preferi Instanciar o PreparedStatement e o ResultSet fora, fechando sua conexão no bloco Finally
        // Foi criada uma função para fechamento.

        PreparedStatement st = null;
        ResultSet rs = null;
        String comandoSql = "SELECT * FROM contatos WHERE id = ?";

        try{
            st = conn.prepareStatement(comandoSql);
            st.setInt(1, id);
            rs = st.executeQuery();

            // Assim que executado o comando, avançamos para a linha.
            // Instanciona um obj (Contato)
            if(rs.next()){
                Contato cont = instanciaContato(rs);
                return cont;
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
        return null;
    }

    @Override
    public List<Contato> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        String comandoSql = "SELECT * FROM contatos";
        List<Contato> listContatos = new ArrayList<>();
        try{
            st = conn.prepareStatement(comandoSql);
            rs = st.executeQuery();

            while(rs.next()){
                Contato cont = instanciaContato(rs);
                listContatos.add(cont);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return listContatos;
    }

    // CRIADO PARA INSTANCIAR UM CONTATO
    private static Contato instanciaContato(ResultSet rs) throws SQLException {
        Contato contato = new Contato();
        contato.setId(rs.getInt("id"));
        contato.setNome(rs.getString("nome"));
        contato.setTelefone(rs.getString("telefone"));
        contato.setEmail(rs.getString("email"));
        return contato;
    }
}
