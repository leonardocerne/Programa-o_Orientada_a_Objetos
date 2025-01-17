package projeto.service;

import projeto.dao.ItemFaturadoDao;
import projeto.dao.LivroDao;
import projeto.dao.PedidoDao;
import projeto.exceptions.ClasseComItensAssociadosException;
import projeto.exceptions.EntidadeNaoEncontradaException;
import projeto.model.ItemFaturado;
import projeto.model.ItemPedido;
import projeto.model.Livro;
import projeto.util.FabricaDeDaos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LivroService {
    private final LivroDao livroDao = FabricaDeDaos.getDAO(LivroDao.class);
    private final ItemFaturadoDao itemFaturadoDao = FabricaDeDaos.getDAO(ItemFaturadoDao.class);
    public Livro incluir(Livro livro) {
        return livroDao.incluir(livro);
    }
    public Livro alterarIsbn(Livro livro, String novoIsbn) {
        livro.setIsbn(novoIsbn);
        return livro;
    }

    public Livro alterarTitulo(Livro livro, String novoTitulo) {
        livro.setTitulo(novoTitulo);
        return livro;
    }

    public Livro alterarDescricao(Livro livro, String novoDescricao) {
        livro.setDescricao(novoDescricao);
        return livro;
    }

    public Livro alterarQtdEstoque(Livro livro, int novaQtdEstoque) {
        livro.setQtdEstoque(novaQtdEstoque);
        return livro;
    }

    public Livro alterarPreco(Livro livro, double novoPreco) {
        livro.setPreco(novoPreco);
        return livro;
    }

    public Livro remover(int id) throws EntidadeNaoEncontradaException, ClasseComItensAssociadosException {
        Livro livro = recuperarLivroPorId(id);
        if(livro.getItensPedidos().isEmpty()) {
            livroDao.remover(id);
        }
        else throw new ClasseComItensAssociadosException("Livro com itens pedidos");
        return livro;
    }

    public Livro recuperarLivroPorId(int id) throws EntidadeNaoEncontradaException {
        Livro livro = livroDao.recuperarPorId(id);
        if (livro == null) throw new EntidadeNaoEncontradaException("Livro inexistente");
        else{
            return livro;
        }
    }

    public List<Livro> recuperarLivros(){
        return livroDao.recuperarTodos();
    }

    public List<Livro> recuperarTodosLivrosNuncaFaturados(){
        return livroDao.recuperarTodosLivrosNuncaFaturados();
    }

    public List<ItemFaturado> recuperarItensFaturadosMesAno(int id, int mes, int ano) throws EntidadeNaoEncontradaException {
        Livro livro = recuperarLivroPorId(id);
        List<ItemFaturado> itensFaturados = itemFaturadoDao.recuperarItensFaturadosDeUmLivro(livro);
        List<ItemFaturado> resposta = new ArrayList<ItemFaturado>();
        for(ItemFaturado itemFaturado : itensFaturados){
            if((itemFaturado.getFatura().getDataFatura().getMonthValue() == mes) && (itemFaturado.getFatura().getDataFatura().getYear() == ano)){
                resposta.add(itemFaturado);
            }
        }
        return resposta;
    }

    public boolean jaFaturadoMesAno(int id, int mes, int ano) throws EntidadeNaoEncontradaException {
        List<ItemFaturado> itens = recuperarItensFaturadosMesAno(id, mes, ano);
        if(itens.isEmpty()) return false;
        return true;
    }

    public List<Livro> recuperarLivrosFaturadosMesAno(int mes, int ano) throws EntidadeNaoEncontradaException {
        Map<Integer, Livro> mapDeLivros = livroDao.getMap();
        return mapDeLivros.values()
                .stream()
                .filter((livro) -> {
                    try {
                        return (jaFaturadoMesAno(livro.getId(), mes, ano));
                    } catch (EntidadeNaoEncontradaException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toList();
    }
}
