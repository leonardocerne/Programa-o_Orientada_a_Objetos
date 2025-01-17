package projeto.service;

import projeto.dao.ItemPedidoDao;
import projeto.exceptions.ClasseComItensAssociadosException;
import projeto.exceptions.EntidadeNaoEncontradaException;
import projeto.model.ItemPedido;
import projeto.model.Pedido;
import projeto.util.FabricaDeDaos;

import java.util.List;

public class ItemPedidoService {
    public final ItemPedidoDao itemPedidoDao = FabricaDeDaos.getDAO(ItemPedidoDao.class);
    public ItemPedido incluir(ItemPedido itemPedido) {
        itemPedido.getPedido().getItens().add(itemPedido);
        itemPedido.getLivro().getItensPedidos().add(itemPedido);
        return itemPedidoDao.incluir(itemPedido);
    }

    public ItemPedido remover(int id) throws EntidadeNaoEncontradaException, ClasseComItensAssociadosException {
        ItemPedido itemPedido = recuperarItemPedidoPorId(id);
        if(itemPedido.getItensFaturados().isEmpty()){
            itemPedido.getLivro().getItensPedidos().remove(itemPedido);
            itemPedido.getPedido().getItens().remove(itemPedido);
        }
        else throw new ClasseComItensAssociadosException("Item pedido tem itens faturados");
        return itemPedido;
    }

    public ItemPedido recuperarItemPedidoPorId(int id) throws EntidadeNaoEncontradaException {
        ItemPedido itemPedido = itemPedidoDao.recuperarPorId(id);
        if(itemPedido == null){
            throw new EntidadeNaoEncontradaException("Item pedido não encontrado");
        }
        return itemPedido;
    }

    public List<ItemPedido> recuperarItensPedidos() {
        return itemPedidoDao.recuperarTodos();
    }
}
