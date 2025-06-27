package model.imple;

import model.dao.ContatoDao;
import model.entities.Contato;

import java.util.List;

public class ContatoDaoJDBC implements ContatoDao {
    @Override
    public void insert(Contato obj) {
        
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
