package projeto.dao;

import projeto.model.Fatura;

import java.util.List;

public interface FaturaDao extends DaoGenerico<Fatura> {
    public List<Fatura> recuperarFaturasDeUmPedido(int id);
}
