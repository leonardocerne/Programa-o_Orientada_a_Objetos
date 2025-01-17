package projeto.dao.impl;

import projeto.dao.FaturaDao;
import projeto.model.Fatura;
import projeto.model.Pedido;

import java.util.List;

public class FaturaDaoImpl extends DaoGenericoImpl<Fatura> implements FaturaDao {
    public List<Fatura> recuperarFaturasDeUmPedido(int id){
        return map.values()
                .stream()
                .filter((fatura) -> (fatura.getFirstItemFaturado().getItemPedido().getPedido().getNumPedido() == id))
                .toList();
    }
}
