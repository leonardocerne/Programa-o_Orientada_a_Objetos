package projeto.model;

import projeto.util.Id;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ItemPedido implements Serializable {
    @Id
    private int id;
    private int qtdPedida;
    private int qtdQueFalta;
    private double precoCobrado;
    private Livro livro;
    private Pedido pedido;
    private List<ItemFaturado> itensFaturados;

    public ItemPedido(int qtdPedida, Livro livro, Pedido pedido) {
        this.qtdPedida = qtdPedida;
        this.qtdQueFalta = qtdPedida;
        this.livro = livro;
        this.pedido = pedido;
        setPrecoCobrado(livro, qtdPedida);
        this.itensFaturados = new ArrayList<>();
    }

    public String listarItemPedido() {
        return "Item Pedido = " + "ID=" + id + " Titulo=" + livro.getTitulo() + " na quantidade " + qtdPedida + " e seu preço total ficou " + precoCobrado ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public List<ItemFaturado> getItensFaturados() {
        return itensFaturados;
    }

    public void setItens(List<ItemFaturado> itens) {
        this.itensFaturados = itens;
    }

    private double getPrecoCobrado() {
        return precoCobrado;
    }

    public void setPrecoCobrado(Livro livro, int qtdPedida) {
        this.precoCobrado = livro.getPreco() * qtdPedida;
    }

    public int getQtdPedida() {
        return qtdPedida;
    }

    public int getQtdQueFalta() {
        return qtdQueFalta;
    }

    public void setQtdQueFalta(int qtdQueFalta) {
        this.qtdQueFalta = qtdQueFalta;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }
}
