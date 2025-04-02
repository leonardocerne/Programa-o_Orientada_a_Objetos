package projeto.service;

import projeto.dao.ItemFaturadoDao;
import projeto.dao.LivroDao;
import projeto.exceptions.EntidadeNaoEncontradaException;
import projeto.exceptions.QuantidadeInvalidaException;
import projeto.model.ItemFaturado;
import projeto.model.Livro;
import projeto.util.FabricaDeDaos;

import java.util.List;

public class ItemFaturadoService {
    ItemFaturadoDao itemFaturadoDao = FabricaDeDaos.getDAO(ItemFaturadoDao.class);
    LivroDao livroDao = FabricaDeDaos.getDAO(LivroDao.class);
    public ItemFaturado incluir(ItemFaturado itemFaturado) throws QuantidadeInvalidaException {
        if (itemFaturado.getItemPedido().getPedido().getFaturado() == 0){
            int aux = itemFaturado.getItemPedido().getQtdQueFalta();
            int qtdEst = itemFaturado.getItemPedido().getLivro().getQtdEstoque();
            if(aux > 0){
                if(qtdEst == 0) {
                    System.out.println('\n' + "Nao eh possivel faturar o item " + itemFaturado.getItemPedido().getLivro().getId() + " pois o estoque dele esta zerado");
                    itemFaturado.getItemPedido().setQtdQueFalta(aux);
                    return itemFaturado;
                }
                if(itemFaturado.getQtdFaturada() > itemFaturado.getItemPedido().getQtdPedida()){
                    throw new QuantidadeInvalidaException("Mais itens faturados que itens pedidos");
                }
                if(itemFaturado.getQtdFaturada() > itemFaturado.getItemPedido().getQtdQueFalta()){
                    System.out.println('\n' + "QTD FATURADA ANTES" + itemFaturado.getItemPedido().getQtdQueFalta());
                    System.out.println('\n' + "AUX" + aux);
                    itemFaturado.setQtdFaturada(aux);
                    System.out.println('\n' + "QTD FATURADA DEPOIS" + itemFaturado.getItemPedido().getQtdQueFalta());
                }
                if(itemFaturado.getQtdFaturada() >= qtdEst){
                    itemFaturado.setQtdFaturada(qtdEst);
                    itemFaturado.getItemPedido().getLivro().setQtdEstoque(0);
                }
                else itemFaturado.getItemPedido().getLivro().setQtdEstoque(qtdEst - itemFaturado.getQtdFaturada());
                itemFaturado.getItemPedido().setQtdQueFalta(itemFaturado.getItemPedido().getQtdQueFalta() - itemFaturado.getQtdFaturada());
                itemFaturado.getItemPedido().getItensFaturados().add(itemFaturado);
                itemFaturado.getFatura().getItensFaturados().add(itemFaturado);
            }
            return itemFaturadoDao.incluir(itemFaturado);
        } else return null;
    }

    public ItemFaturado remover(int id) throws EntidadeNaoEncontradaException {
        ItemFaturado itemFaturado = recuperarItemFaturadoPorId(id);
        itemFaturado.getItemPedido().setQtdQueFalta(itemFaturado.getQtdFaturada() + itemFaturado.getItemPedido().getQtdQueFalta());
        itemFaturado.getItemPedido().getLivro().setQtdEstoque(itemFaturado.getItemPedido().getLivro().getQtdEstoque() + itemFaturado.getQtdFaturada());
        itemFaturado.getItemPedido().getItensFaturados().remove(itemFaturado);
        itemFaturado.getFatura().getItensFaturados().remove(itemFaturado);
        if(itemFaturado.getFatura().getItensFaturados().isEmpty()){ itemFaturado.getItemPedido().getPedido().setFaturado(0);}
        return itemFaturadoDao.remover(id);
    }

    public ItemFaturado recuperarItemFaturadoPorId(int id) throws EntidadeNaoEncontradaException {
        ItemFaturado itemFaturado = itemFaturadoDao.recuperarPorId(id);
        if(itemFaturado == null) throw new EntidadeNaoEncontradaException("Item faturado inexistente");
        return itemFaturado;
    }

    public List<ItemFaturado> recuperarItensFaturados(){
        return itemFaturadoDao.recuperarTodos();
    }

    public List<ItemFaturado> recuperarItensFaturadosPorPedido(int id){
        return itemFaturadoDao.recuperarItensFaturadosDeUmPedido(id);
    }

    public List<ItemFaturado> recuperarItensFaturadosPorLivro(int id) throws EntidadeNaoEncontradaException {
        Livro livro = livroDao.recuperarPorId(id);
        return itemFaturadoDao.recuperarItensFaturadosDeUmLivro(livro);
    }
}
