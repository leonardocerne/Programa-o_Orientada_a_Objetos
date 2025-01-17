package projeto.service;

import projeto.dao.ClienteDao;
import projeto.exceptions.ClasseComItensAssociadosException;
import projeto.exceptions.EntidadeNaoEncontradaException;
import projeto.model.Cliente;
import projeto.util.FabricaDeDaos;

import java.util.List;

public class ClienteService {
    private final ClienteDao clienteDao = FabricaDeDaos.getDAO(ClienteDao.class);
    public Cliente incluir(Cliente cliente) {
        return clienteDao.incluir(cliente);
    }
    public Cliente alterarNome(Cliente cliente, String nome) {
        cliente.setNome(nome);
        return cliente;
    }
    public Cliente alterarCpf(Cliente cliente, String cpf) {
        cliente.setCpf(cpf);
        return cliente;
    }
    public Cliente alterarEndereço(Cliente cliente, String endereço) {
        cliente.setEndereco(endereço);
        return cliente;
    }
    public Cliente alterarTelefone(Cliente cliente, String nome) {
        cliente.setNome(nome);
        return cliente;
    }
    public Cliente alterarEmail(Cliente cliente, String nome) {
        cliente.setNome(nome);
        return cliente;
    }

    public Cliente remover(int id) throws EntidadeNaoEncontradaException, ClasseComItensAssociadosException {
        Cliente cliente = recuperarClientePorId(id);
        if ((cliente.getFaturas().isEmpty()) && cliente.getPedidos().isEmpty()) {
            clienteDao.remover(id);
        } else {
            throw new ClasseComItensAssociadosException(
                    "Este cliente possui faturas ou pedidos e não pode ser removido.");
        }
        return cliente;
    }

    public Cliente recuperarClientePorId(int id) throws EntidadeNaoEncontradaException {
        Cliente cliente = clienteDao.recuperarPorId(id);
        if (cliente == null)
            throw new EntidadeNaoEncontradaException("Produto inexistente.");
        return cliente;
    }

    public List<Cliente> recuperarClientes(){
        return clienteDao.recuperarTodos();
    }

}
