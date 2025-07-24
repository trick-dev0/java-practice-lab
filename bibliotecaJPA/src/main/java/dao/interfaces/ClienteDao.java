package dao.interfaces;

import dominio.Cliente;

import java.util.List;

public interface ClienteDao {
    void inserirCliente(Cliente cliente);
    void atualizarCliente(Cliente cliente);
    void excluirCliente(Cliente cliente);
    List<Cliente> listarCliente();
    Cliente buscarCliente(int id);
}
