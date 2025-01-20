package projeto.service;

import corejava.Console;
import projeto.dao.FaturaDao;
import projeto.exceptions.ClasseComItensAssociadosException;
import projeto.exceptions.DataInvalidaException;
import projeto.exceptions.EntidadeNaoEncontradaException;
import projeto.model.*;
import projeto.util.FabricaDeDaos;

import java.util.ArrayList;
import java.util.List;

public class FaturaService {
    private final FaturaDao faturaDao = FabricaDeDaos.getDAO(FaturaDao.class);
    private final ItemFaturadoService itemFaturadoService = new ItemFaturadoService();
    public Fatura incluir(Fatura fatura) {
        fatura.getCliente().getFaturas().add(fatura);
        ItemFaturado itemFaturado = fatura.getItensFaturados().get(0);
        List<ItemPedido> itensPedidos = itemFaturado.getItemPedido().getPedido().getItens();
        int conts = 0, contn = 0;//, cont0 = 0;
        for(ItemPedido itemPedido : itensPedidos) {
            //if(itemPedido.getLivro().getQtdEstoque() == 0) cont0++;
            if(itemPedido.getQtdQueFalta() == 0) conts++;
            if(itemPedido.getQtdQueFalta() == itemPedido.getQtdPedida()) contn++;
        }
        //if(cont0 == itensPedidos.size()) System.out.println('\n' + "Nao foi possivel faturar nenhum item");;
        if(conts == itensPedidos.size()) itemFaturado.getItemPedido().getPedido().setStatus("Pago");
        if((contn < itensPedidos.size()) && contn > 0) itemFaturado.getItemPedido().getPedido().setStatus("Pagando");
        if(contn == itensPedidos.size()) itemFaturado.getItemPedido().getPedido().setStatus("Em Aberto");
        itemFaturado.getItemPedido().getPedido().setFaturado(1);
        return faturaDao.incluir(fatura);
    }

    public Fatura remover(int numFatura) throws EntidadeNaoEncontradaException, ClasseComItensAssociadosException {
        Fatura fatura = recuperarFaturaPorId(numFatura);
        if(fatura.getCancelada() == 1){
            System.out.println('\n' + "Fatura nao pode ser removida pois foi cancelada");
            return fatura;
        }
        if(fatura.getItensFaturados().isEmpty()){
            fatura.getCliente().getFaturas().remove(fatura);
            faturaDao.remover(numFatura);
        }
        else throw new ClasseComItensAssociadosException("Fatura com itens faturados");
        return fatura;
    }

    public Fatura recuperarFaturaPorId(int numFatura) throws EntidadeNaoEncontradaException {
        Fatura fatura = faturaDao.recuperarPorId(numFatura);
        if (fatura == null)
            throw new EntidadeNaoEncontradaException("Fatura inexistente.");
        return fatura;
    }

    public List<Fatura> recuperarFaturas() {
        return faturaDao.recuperarTodos();
    }

    public List<Fatura> recuperarFaturasPorPedido(int id) {
        return faturaDao.recuperarFaturasDeUmPedido(id);
    }

    public void cancelar(int idFatura) throws EntidadeNaoEncontradaException, DataInvalidaException {
        Fatura umaFatura = recuperarFaturaPorId(idFatura);
        Cliente cliente = umaFatura.getCliente();
        if(cliente.getFaturas().size() < 3) {
            System.out.println('\n' + "O cliente nao possui faturas suficientes para poder cancelar uma");
            return;
        }
        if(umaFatura.getCancelada() == 1){
            System.out.println('\n' + "Fatura ja cancelada");
            return;
        }
        String dataCancelamento = Console.readLine('\n' + "Digite a data de fatura do cliente(DD/MM/YYYY): ");
        umaFatura.setDataCancelamento(dataCancelamento);
        List<ItemFaturado> itensFaturados = umaFatura.getItensFaturados();
        if(itensFaturados.isEmpty()){
            umaFatura.setCancelada(1);
            System.out.println('\n' + "Fatura cancelada com sucesso!" );
            return;
        }
        for(ItemFaturado itemFaturado : itensFaturados)
            itemFaturadoService.remover(itemFaturado.getId());
        umaFatura.setCancelada(1);
        System.out.println('\n' + "Fatura cancelada com sucesso!");
    }

    public void cancelarDefinido(int idFatura, String dataCancelamento) throws EntidadeNaoEncontradaException, DataInvalidaException {
        Fatura umaFatura = recuperarFaturaPorId(idFatura);
        Cliente cliente = umaFatura.getCliente();
        if(cliente.getFaturas().size() < 3) {
            System.out.println('\n' + "O cliente nao possui faturas suficientes para poder cancelar uma");
            return;
        }
        if(umaFatura.getCancelada() == 1){
            System.out.println('\n' + "Fatura ja cancelada");
            return;
        }
        umaFatura.setDataCancelamento(dataCancelamento);
        List<ItemFaturado> itensFaturados = umaFatura.getItensFaturados();
        if(itensFaturados.isEmpty()){
            umaFatura.setCancelada(1);
            System.out.println('\n' + "Fatura cancelada com sucesso!" );
            return;
        }
        List<Integer> listaDeIDS = new ArrayList<>();
        for(ItemFaturado itemFaturado : itensFaturados)
            listaDeIDS.add(itemFaturado.getId());
        for(Integer id : listaDeIDS){
            itemFaturadoService.remover(id);
        }
        umaFatura.setCancelada(1);
        System.out.println('\n' + "Fatura cancelada com sucesso!");
    }
}
