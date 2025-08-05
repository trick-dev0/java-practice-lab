package dao.interfaces;

import dominio.Cliente;

import java.util.List;

public interface ClienteDao {
    void inserirCliente(Cliente cliente);
    void atualizarCliente(Integer id, String name);
    void deletarCliente(Integer id);
    List<Cliente> listarCliente();
    Cliente buscarCliente(int id);
}
