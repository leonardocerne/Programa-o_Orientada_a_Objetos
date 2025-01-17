package projeto.dao.impl;

import projeto.dao.PedidoDao;
import projeto.model.Pedido;

import java.util.List;

public class PedidoDaoImpl extends DaoGenericoImpl<Pedido> implements PedidoDao {
    public List<Pedido> recuperarPedidosDeUmCliente(int id){
        return map.values()
                .stream()
                .filter((pedido) -> (pedido.getCliente()).getId() == id)
                .toList();
    }
}
