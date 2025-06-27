package model.imple;

import db.DB;
import db.exception.DbException;
import model.dao.ContatoDao;
import model.entities.Contato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Contato findById(Integer id) {
        return null;
    }

    @Override
    public List<Contato> findAll() {
        return List.of();
    }
}
