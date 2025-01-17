package projeto.model;

import projeto.util.Id;

import java.io.Serializable;
import java.text.NumberFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import projeto.exceptions.DataInvalidaException;

import javax.xml.crypto.Data;

public class Pedido implements Serializable{
    @Id
    private int numPedido;
    private LocalDate dataPedido;
    private LocalDate dataCancelamento;
    private String status;
    private Cliente cliente;
    private List<ItemPedido> itens;
    private String endereçoDeEntrega;
    private int faturado;

    private static final NumberFormat NF; // Formatador para imprimir e efetuar o parse de números

    // Formatador para imprimir e efetuar o parse de objetos date-time
    private static final DateTimeFormatter DTF;

    static
    {
        Locale locale = Locale.of("pt", "BR");
        NF = NumberFormat.getNumberInstance(locale);
        DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        NF.setMaximumFractionDigits (2);	   // O default é 3.
        NF.setMinimumFractionDigits (2);
    }

    public String toString() {
        return "Número do pedido = " + numPedido +
                "  Data do pedido = " + getDataPedidoMasc() +
                "  Status = " + status +
                "  Cliente = " + cliente.getNome();
    }

    public void listarPedidoComItens(){
        System.out.println('\n' + "Número do pedido = " + numPedido + ", seu status:" + status + " e seus itens:");
        for(ItemPedido item : itens){
            System.out.println('\n' + item.listarItemPedido());
        }
    }

    public Pedido (String dataPedido, Cliente cliente, String endereçoDeEntrega) throws DataInvalidaException {
        setDataPedido(dataPedido);
        this.cliente = cliente;
        this.endereçoDeEntrega = endereçoDeEntrega;
        this.itens = new ArrayList<>();
        this.status = "Em Aberto";
        this.faturado = 0;
    }

    public int getNumPedido() {
        return numPedido;
    }

    public void setNumPedido(int numPedido) {
        this.numPedido = numPedido;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(String novaDataPedido) throws DataInvalidaException {
            try {
                int dia = Integer.parseInt(novaDataPedido.substring(0, 2));
                int mes = Integer.parseInt(novaDataPedido.substring(3, 5));
                int ano = Integer.parseInt(novaDataPedido.substring(6, 10));

                dataPedido = LocalDate.of(ano, mes, dia);
            }
            catch(StringIndexOutOfBoundsException |
                  NumberFormatException |
                  DateTimeException e) {
                throw new DataInvalidaException("Data inválida.");
            }
    }

    public LocalDate getDataCancelamento() {
        return dataCancelamento;
    }

    public String getDataPedidoMasc() {
        return DTF.format(dataPedido);
    }

    public void setDataCancelamento(String novaDataCancelamento) throws DataInvalidaException {
        try {
            int dia = Integer.parseInt(novaDataCancelamento.substring(0, 2));
            int mes = Integer.parseInt(novaDataCancelamento.substring(3, 5));
            int ano = Integer.parseInt(novaDataCancelamento.substring(6, 10));

            dataCancelamento = LocalDate.of(ano, mes, dia);
        }
        catch(StringIndexOutOfBoundsException |
              NumberFormatException |
              DateTimeException e) {
            throw new DataInvalidaException("Data inválida.");
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public String getEndereçoDeEntrega() {
        return endereçoDeEntrega;
    }

    public void setEndereçoDeEntrega(String endereçoDeEntrega) {
        this.endereçoDeEntrega = endereçoDeEntrega;
    }

    public int getFaturado() {
        return faturado;
    }

    public void setFaturado(int faturado) {
        this.faturado = faturado;
    }
}
