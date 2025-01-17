package projeto.dao;

import projeto.exceptions.EntidadeNaoEncontradaException;
import projeto.model.Livro;

import java.util.List;

public interface LivroDao extends DaoGenerico<Livro>{
    public List<Livro> recuperarTodosLivrosNuncaFaturados();
}
