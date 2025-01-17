package projeto.model;

import projeto.dao.ClienteDao;
import projeto.dao.ItemFaturadoDao;
import projeto.exceptions.EntidadeNaoEncontradaException;
import projeto.service.LivroService;
import projeto.util.FabricaDeDaos;
import projeto.util.Id;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Livro implements Serializable {
    @Id
    private int id;
    private String titulo;
    private String isbn;
    private String descricao;
    private int qtdEstoque;
    private double preco;
    private List<ItemPedido> itensPedidos;

    public Livro(String titulo, String isbn, String descricao, int qtdEstoque, double preco) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.descricao = descricao;
        this.qtdEstoque = qtdEstoque;
        this.preco = preco;
        this.itensPedidos = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Livro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", isbn='" + isbn + '\'' +
                ", descricao='" + descricao + '\'' +
                ", quantidade no estoque" + qtdEstoque +
                ", preco=" + preco +
                '}';
    }

    public String livroEQuantidade(){
        return "Livro{id=" + id + ", titulo=" + titulo + ", quantidade no estoque=" + qtdEstoque + '}';
    }

    public List<ItemPedido> getItensPedidos() {
        return itensPedidos;
    }

    public void setItensPedidos(List<ItemPedido> itensPedidos) {
        this.itensPedidos = itensPedidos;
    }

    public int getId() { return id;}

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public boolean nuncaFaturado(){
        for(ItemPedido item: itensPedidos){
            if(!(item.getItensFaturados().isEmpty())) return false;
        }
        return true;
    }

}
