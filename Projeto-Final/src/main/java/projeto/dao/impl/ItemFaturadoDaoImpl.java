package projeto.dao.impl;

import projeto.dao.ItemFaturadoDao;
import projeto.model.ItemFaturado;
import projeto.model.Livro;
import projeto.model.Pedido;

import java.util.List;

public class ItemFaturadoDaoImpl extends DaoGenericoImpl<ItemFaturado> implements ItemFaturadoDao {
    public List<ItemFaturado> recuperarItensFaturadosDeUmPedido(int id){
        return map.values()
                .stream()
                .filter((itemFaturado) -> (itemFaturado.getItemPedido().getPedido().getNumPedido() == id))
                .toList();
    }
    public List<ItemFaturado> recuperarItensFaturadosDeUmLivro(Livro livro){
        return map.values()
                .stream()
                .filter((itemFaturado) -> (itemFaturado.getItemPedido().getLivro().getId() == livro.getId()))
                .toList();
    }
}
