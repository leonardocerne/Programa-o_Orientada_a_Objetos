package projeto.dao.impl;

import projeto.dao.LivroDao;
import projeto.exceptions.EntidadeNaoEncontradaException;
import projeto.model.ItemFaturado;
import projeto.model.ItemPedido;
import projeto.model.Livro;
import projeto.model.Pedido;

import java.util.List;

public class LivroDaoImpl extends DaoGenericoImpl<Livro> implements LivroDao {
    public List<Livro> recuperarTodosLivrosNuncaFaturados(){
        return map.values()
                .stream()
                .filter((livro) -> (livro.nuncaFaturado()))
                .toList();
    }

}
