package projeto.model;

import projeto.exceptions.DataInvalidaException;
import projeto.util.Id;

import java.io.Serializable;
import java.text.NumberFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Fatura implements Serializable {
    @Id
    private int numFatura;
    private LocalDate dataFatura;
    private LocalDate dataCancelamento;
    private List<ItemFaturado> itensFaturados;
    private double valorTotal;
    private double valorTotalDesconto = 0;
    private Cliente cliente;
    private int Cancelada = 0;

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


    public Fatura(String dataFatura, Cliente cliente) throws DataInvalidaException {
        setDataFatura(dataFatura);
        this.cliente = cliente;
        this.itensFaturados = new ArrayList<ItemFaturado>();
    }

    @Override
    public String toString() {
        return "Fatura{" +
                "numero de fatura=" + numFatura +
                ", valor total=" + valorTotal +
                ", valor total desconto=" + valorTotalDesconto +
                ", cliente=" + cliente.getNome() +
                ", dataFatura=" + getDataFaturaMasc() +
                '}';
    }

    public int getNumFatura() {
        return numFatura;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal() {
        double soma = 0.0;
        for (ItemFaturado itemFaturado : itensFaturados) {
            soma += itemFaturado.getQtdFaturada() * itemFaturado.getItemPedido().getLivro().getPreco();
        }
        if(getCliente().getFaturas().size() > 4){
            this.valorTotal = soma * 0.95;
        }
        this.valorTotal = soma;
    }

    public double getValorTotalDesconto() {
        return valorTotalDesconto;
    }

    public void setValorTotalDesconto() {
        if(getCliente().getFaturas().size() > 4){
            double soma = 0.0;
            for (ItemFaturado itemFaturado : itensFaturados) {
                soma += itemFaturado.getQtdFaturada() * itemFaturado.getItemPedido().getLivro().getPreco();
            }
            this.valorTotalDesconto = soma * 0.05;
        }
        else this.valorTotalDesconto = 0;
    }

    public LocalDate getDataFatura() {
        return dataFatura;
    }

    public void setDataFatura(String novaDataFatura) throws DataInvalidaException {
        try {
            int dia = Integer.parseInt(novaDataFatura.substring(0, 2));
            int mes = Integer.parseInt(novaDataFatura.substring(3, 5));
            int ano = Integer.parseInt(novaDataFatura.substring(6, 10));

            dataFatura = LocalDate.of(ano, mes, dia);
        }
        catch(StringIndexOutOfBoundsException |
              NumberFormatException |
              DateTimeException e) {
            throw new DataInvalidaException("Data inválida.");
        }
    }

    public int getCancelada() {
        return Cancelada;
    }

    public void setCancelada(int cancelada) {
        Cancelada = cancelada;
    }

    public LocalDate getDataCancelamento() {
        return dataCancelamento;
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

    public String getDataFaturaMasc() {
        return DTF.format(dataFatura);
    }

    public List<ItemFaturado> getItensFaturados() {
        return itensFaturados;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ItemFaturado getFirstItemFaturado(){
        return itensFaturados.get(0);
    }
}
