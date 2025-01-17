package projeto.model;

import projeto.util.Id;

import java.io.Serializable;

public class ItemFaturado implements Serializable {
    @Id
    private int id;
    private int qtdFaturada;
    private ItemPedido itemPedido;
    private Fatura fatura;

    public ItemFaturado(int qtdFaturada, ItemPedido itemPedido, Fatura fatura) {
        this.qtdFaturada = qtdFaturada;
        this.itemPedido = itemPedido;
        this.fatura = fatura;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ItemFaturado{" +
                "id=" + id +
                ",qtd faturada=" + qtdFaturada +
                ", item pedido=" + itemPedido.getLivro().getTitulo() +
                ", quantidade pedida e quantidade faltante=" + itemPedido.getQtdPedida() +
                ", " + itemPedido.getQtdQueFalta() +
                '}';
    }

    public Fatura getFatura() {
        return fatura;
    }

    public void setFatura(Fatura fatura) {
        this.fatura = fatura;
    }

    public int getQtdFaturada() {
        return this.qtdFaturada;
    }

    public void setQtdFaturada(int qtdFaturada) {
        if(qtdFaturada >= itemPedido.getLivro().getQtdEstoque()) this.qtdFaturada = itemPedido.getLivro().getQtdEstoque();
        else this.qtdFaturada = qtdFaturada;
    }

    public ItemPedido getItemPedido() {
        return itemPedido;
    }
}
