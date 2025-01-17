package projeto.service;

import corejava.Console;
import projeto.dao.PedidoDao;
import projeto.exceptions.ClasseComItensAssociadosException;
import projeto.exceptions.DataInvalidaException;
import projeto.exceptions.EntidadeNaoEncontradaException;
import projeto.model.Cliente;
import projeto.model.Fatura;
import projeto.model.ItemFaturado;
import projeto.model.Pedido;
import projeto.util.FabricaDeDaos;

import java.util.List;

public class PedidoService {
    private final PedidoDao pedidoDao = FabricaDeDaos.getDAO(PedidoDao.class);
    private final ItemFaturadoService itemFaturadoService = new ItemFaturadoService();
    private final FaturaService faturaService = new FaturaService();

    public Pedido incluir(Pedido pedido) {
        pedidoDao.incluir(pedido);
        pedido.getCliente().getPedidos().add(pedido);
        return pedido;
    }
    public Pedido remover(int numPedido) throws EntidadeNaoEncontradaException, ClasseComItensAssociadosException {
        Pedido pedido = this.recuperarPedidoPorId(numPedido);
        if(pedido.getItens().isEmpty()) {
            pedido.getCliente().getPedidos().remove(pedido);
            pedidoDao.remover(numPedido);
        }
        else throw new ClasseComItensAssociadosException("Pedido com itens pedidos associados");
        return pedido;
    }

    public void cancelar(int numPedido) throws EntidadeNaoEncontradaException, DataInvalidaException, ClasseComItensAssociadosException {
        Pedido umPedido = recuperarPedidoPorId(numPedido);
        String dataCancelamento = Console.readLine('\n' + "Digite a data do cancelamento(DD/MM/YYYY): ");
        umPedido.setDataCancelamento(dataCancelamento);
        umPedido.setStatus("Cancelado");
        List<ItemFaturado> itensFaturados = itemFaturadoService.recuperarItensFaturadosPorPedido(umPedido.getNumPedido());
        for(ItemFaturado itemFaturado : itensFaturados){
            itemFaturadoService.remover(itemFaturado.getId());
        }
        List<Fatura> faturas = faturaService.recuperarFaturasPorPedido(umPedido.getNumPedido());
        for(Fatura fatura : faturas){
            faturaService.remover(fatura.getNumFatura());
        }
        System.out.println('\n' + "Pedido " + umPedido.getNumPedido() + " cancelado com sucesso!");
    }

    public void cancelarDefinido(Pedido umPedido, String dataCancelamento) throws EntidadeNaoEncontradaException, DataInvalidaException, ClasseComItensAssociadosException {
        if(umPedido.getFaturado() == 1){
            System.out.println('\n' + "Nao e possivel cancelar o pedido " + umPedido.getNumPedido() + " pois ele esta faturado.");
        }
        else{
            umPedido.setDataCancelamento(dataCancelamento);
            umPedido.setStatus("Cancelado");
            List<ItemFaturado> itensFaturados = itemFaturadoService.recuperarItensFaturadosPorPedido(umPedido.getNumPedido());
            for(ItemFaturado itemFaturado : itensFaturados){
                itemFaturadoService.remover(itemFaturado.getId());
            }
            List<Fatura> faturas = faturaService.recuperarFaturasPorPedido(umPedido.getNumPedido());
            for(Fatura fatura : faturas){
                faturaService.remover(fatura.getNumFatura());
            }
            System.out.println('\n' + "Pedido " + umPedido.getNumPedido() + " cancelado com sucesso!");
        }
    }

    public Pedido recuperarPedidoPorId(int id) throws EntidadeNaoEncontradaException {
        Pedido pedido = pedidoDao.recuperarPorId(id);
        if (pedido == null)
            throw new EntidadeNaoEncontradaException("Pedido inexistente.");
        return pedido;
    }

    public List<Pedido> recuperarPedidos() {
        return pedidoDao.recuperarTodos();
    }
    public List<Pedido> recuperarPedidosPorCliente(int id) {
        return pedidoDao.recuperarPedidosDeUmCliente(id);
    }
}
