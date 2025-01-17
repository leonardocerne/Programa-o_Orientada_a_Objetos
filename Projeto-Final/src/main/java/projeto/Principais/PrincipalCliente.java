package projeto.Principais;

import corejava.Console;
import projeto.exceptions.ClasseComItensAssociadosException;
import projeto.exceptions.DataInvalidaException;
import projeto.exceptions.EntidadeNaoEncontradaException;
import projeto.exceptions.QuantidadeInvalidaException;
import projeto.model.*;
import projeto.service.*;

import java.util.ArrayList;
import java.util.List;

public class PrincipalCliente {
    public ClienteService clienteService = new ClienteService();
    public PedidoService pedidoService = new PedidoService();
    public FaturaService faturaService = new FaturaService();
    public ItemPedidoService itemPedidoService = new ItemPedidoService();
    public ItemFaturadoService itemFaturadoService = new ItemFaturadoService();
    public void principal(){
        boolean continua = true;
        String nome, endereco, cpf, email, telefone;
        Cliente umCliente;
        ItemPedido itemPedido;
        Fatura umaFatura = null;
        while(continua){
            System.out.println('\n' + "========================================================");
            System.out.println('\n' + "O que você deseja fazer?");
            System.out.println('\n' + "1. Cadastrar um cliente");
            System.out.println('\n' + "2. Alterar um cliente");
            System.out.println('\n' + "3. Remover um cliente");
            System.out.println('\n' + "4. Tratar faturas");
            System.out.println('\n' + "5. Listar todos clientes");
            System.out.println('\n' + "6. Voltar");
            int opcao = Console.readInt('\n' + "Digite um número entre 1 e 6:");
            switch(opcao){
                // Cadastra cliente
                case 1 -> {
                    nome = Console.readLine("Digite o nome do cliente: ");
                    endereco = Console.readLine("Digite o endereço do cliente: ");
                    cpf = Console.readLine("Digite o cpf do cliente: ");
                    email = Console.readLine("Digite o email do cliente: ");
                    telefone = Console.readLine("Digite o telefone do cliente: ");
                    umCliente = new Cliente(nome, cpf, endereco, telefone, email);
                    clienteService.incluir(umCliente);
                    System.out.println("Cliente de nome " + nome + " cadastrado com sucesso!");
                }
                // Altera cliente
                case 2 -> {
                    int id = Console.readInt("Digite um id do cliente que deseja alterar: ");
                    try{
                        umCliente = clienteService.recuperarClientePorId(id);
                    } catch (EntidadeNaoEncontradaException e) {
                        System.out.println('\n' + e.getMessage());
                        break;
                    }

                    System.out.println('\n' + "O que você deseja alterar?");
                    System.out.println('\n' + "1. Nome");
                    System.out.println('\n' + "2. CPF");
                    System.out.println('\n' + "3. Endereço");
                    System.out.println('\n' + "4. Telefone");
                    System.out.println("5. Email");
                    int opcaoalteracao = Console.readInt('\n' + "Digite um numero entre 1 e 5: ");
                    switch(opcaoalteracao){
                        // Altera Nome
                        case 1 -> {
                            String nomenovo = Console.readLine('\n' + "Digite o novo nome do cliente: ");
                            clienteService.alterarNome(umCliente, nomenovo);
                            System.out.println('\n' + "Nome do Cliente alterado com sucesso!");
                        }
                        // Altera CPF
                        case 2 -> {
                            String cpfnovo = Console.readLine('\n' + "Digite o novo cpf do cliente: ");
                            clienteService.alterarCpf(umCliente, cpfnovo);
                            System.out.println('\n' + "CPF do Cliente alterado com sucesso!");
                        }
                        // Altera endereço
                        case 3 -> {
                            String endereconovo = Console.readLine('\n' + "Digite o novo endereco do cliente: ");
                            clienteService.alterarEndereço(umCliente, endereconovo);
                            System.out.println('\n' + "Endereco do Cliente alterado com sucesso!");
                        }
                        // Altera telefone
                        case 4 -> {
                            String telefonenovo = Console.readLine('\n' + "Digite o novo telefone do cliente: ");
                            clienteService.alterarTelefone(umCliente, telefonenovo);
                            System.out.println('\n' + "Telefone do Cliente alterado com sucesso!");
                        }
                        // Altera email
                        case 5 -> {
                            String emailnovo = Console.readLine('\n' + "Digite o novo email do cliente: ");
                            clienteService.alterarEmail(umCliente, emailnovo);
                            System.out.println('\n' + "Email do Cliente alterado com sucesso!");
                        }
                        default -> {
                            System.out.println('\n' + "Opção inválida!");
                        }
                    }
                }
                // Remove cliente
                case 3 -> {
                    int id = Console.readInt("Digite o id do cliente que deseja remover: ");
                    try{
                        umCliente = clienteService.remover(id);
                        System.out.println('\n' + "Cliente removido com sucesso");
                    } catch (EntidadeNaoEncontradaException |ClasseComItensAssociadosException e) {
                        System.out.println('\n' + e.getMessage());
                    }
                }
                // Tratamento de Faturas
                case 4 -> {
                    int id = Console.readInt('\n' + "Digite o id do cliente que vai tratar faturas: ");
                    try{
                        umCliente = clienteService.recuperarClientePorId(id);
                    } catch (EntidadeNaoEncontradaException e) {
                        System.out.println('\n' + e.getMessage());
                        break;
                    }
                    List<Pedido> pedidos = umCliente.getPedidos();
                    if(pedidos.isEmpty()){
                        System.out.println('\n' + "Este cliente não possui pedidos.");
                        break;
                    }
                    System.out.println('\n' + "O que deseja tratar nas faturas?");
                    System.out.println('\n' + "1. Emitir Fatura");
                    System.out.println('\n' + "2. Remover Fatura");
                    System.out.println('\n' + "3. Cancelar Fatura");
                    System.out.println('\n' + "4. Listar Faturas");
                    int opcaofatura = Console.readInt('\n' + "Digite um numero entre 1 e 4: ");
                    switch(opcaofatura){
                        // Emissão de Faturas
                        case 1 -> {
                            String dataFatura = Console.readLine('\n' + "Digite a data de fatura do cliente(DD/MM/YYYY): ");
                            try{
                                umaFatura = new Fatura(dataFatura, umCliente);
                            } catch(DataInvalidaException e){
                                System.out.println('\n' + e.getMessage());
                            }
                            System.out.println('\n' + "Lista dos pedidos do cliente de nome " + umCliente.getNome());
                            for(Pedido pedido : pedidos){
                                pedido.listarPedidoComItens();
                                System.out.println('\n');
                            }
                            id = Console.readInt('\n' + "Digite o número do pedido que vai ser faturado: ");
                            int itensFaturados = Console.readInt('\n' + "Digite quantos itens serão faturados(no mínimo 1): ");
                            if(itensFaturados < 1) throw new QuantidadeInvalidaException('\n' + "Uma fatura deve possuir no mínimo um item faturado.");
                            for(int i = 0; i < itensFaturados; i++) {
                                int idItem = Console.readInt('\n' + "Digite id do item pedido que vai ser faturado");
                                try {
                                    itemPedido = itemPedidoService.recuperarItemPedidoPorId(idItem);
                                } catch (EntidadeNaoEncontradaException e) {
                                    System.out.println('\n' + e.getMessage());
                                    break;
                                }
                                int qtdFaturada = Console.readInt('\n' + "Digite a quantidade desse item que vai ser faturada: ");
                                ItemFaturado umItemFaturado = new ItemFaturado(qtdFaturada, itemPedido, umaFatura);
                                try{
                                    itemFaturadoService.incluir(umItemFaturado);
                                } catch (QuantidadeInvalidaException e){
                                    System.out.println('\n' + e.getMessage());
                                    break;
                                }
                            }
                            umaFatura.setValorTotal();
                            umaFatura.setValorTotalDesconto();
                            faturaService.incluir(umaFatura);
                            System.out.println('\n' + "Fatura emitida com sucesso!");
                        }
                        // Remover fatura
                        case 2 ->{
                            List<Fatura> faturas = umCliente.getFaturas();
                            if(faturas.isEmpty()){
                                System.out.println('\n' + "Esse cliente nao possui faturas");
                                break;
                            }
                            System.out.println('\n' + "Lista de faturas do cliente " + umCliente.getNome());
                            for(Fatura fatura : faturas){
                                System.out.println('\n' + fatura.toString());
                            }
                            int idFatura = Console.readInt('\n' + "Digite o numero da fatura que deseja remover:");
                            try{
                                umaFatura = faturaService.recuperarFaturaPorId(idFatura);
                            } catch (EntidadeNaoEncontradaException e) {
                                System.out.println('\n' + e.getMessage());
                            }
                            if(umaFatura.getCancelada() == 1){
                                System.out.println('\n' + "Essa fatura nao pode ser removida pois ja foi cancelada.");
                                break;
                            }
                            System.out.println('\n' + "Deseja remover somente um item faturado ou a fatura inteira?");
                            int opcaoCancelamento = Console.readInt('\n' + "1 para fatura, 2 para item faturado");
                            switch(opcaoCancelamento){
                                case 1 -> {
                                    List<ItemFaturado> itemFaturados = umaFatura.getItensFaturados();
                                    if(itemFaturados.isEmpty()){
                                        try{
                                            faturaService.remover(umaFatura.getNumFatura());
                                        } catch (EntidadeNaoEncontradaException | ClasseComItensAssociadosException e){
                                            System.out.println('\n' + e.getMessage());
                                        }
                                        System.out.println('\n' + "Fatura removida com sucesso!");
                                        break;
                                    }
                                    List<Integer> listaDeIds = new ArrayList<Integer>();
                                    for(ItemFaturado itemFaturado : itemFaturados){
                                        listaDeIds.add(itemFaturado.getId());
                                    }
                                    try{
                                        for(Integer x : listaDeIds){
                                            itemFaturadoService.remover(x);
                                        }
                                    } catch (EntidadeNaoEncontradaException e) {
                                        System.out.println('\n' + e.getMessage());
                                    }
                                    try{
                                        faturaService.remover(umaFatura.getNumFatura());
                                    } catch (EntidadeNaoEncontradaException | ClasseComItensAssociadosException e) {
                                        System.out.println('\n' + e.getMessage());
                                    }
                                    System.out.println('\n' + "Fatura removida com sucesso!");
                                }
                                case 2 -> {
                                    List<ItemFaturado> itensFaturados = umaFatura.getItensFaturados();
                                    if(itensFaturados.isEmpty()){
                                        System.out.println('\n' + "A fatura nao possui itens faturados");
                                        break;
                                    }
                                    System.out.println('\n' + "Itens faturados:");
                                    for(ItemFaturado itemFaturado : itensFaturados){
                                        System.out.println('\n' + itemFaturado.toString());
                                    }
                                    int idItemFatura = Console.readInt('\n' + "Digite o id do item faturado que deseja remover:");
                                    try{
                                        itemFaturadoService.remover(idItemFatura);
                                    } catch (EntidadeNaoEncontradaException e) {
                                        System.out.println('\n' + e.getMessage());
                                    }
                                    System.out.println('\n' + "item faturado removido com sucesso!");
                                }
                                default -> {
                                    System.out.println('\n' + "Opção inválida!");
                                }
                            }
                        }
                        // Cancelar Fatura
                        case 3->{
                            List<Fatura> faturas = umCliente.getFaturas();
                            if(faturas.size() < 3){
                                System.out.println('\n' + "Esse cliente nao possui faturas suficientes para poder cancelar");
                                break;
                            }
                            System.out.println('\n' + "Lista de faturas do cliente " + umCliente.getNome());
                            for(Fatura fatura : faturas){
                                System.out.println('\n' + fatura.toString());
                            }
                            int idFatura = Console.readInt('\n' + "Digite o numero da fatura que deseja cancelar:");
                            try{
                                umaFatura = faturaService.recuperarFaturaPorId(idFatura);
                            } catch (EntidadeNaoEncontradaException e) {
                                System.out.println('\n' + e.getMessage());
                            }
                            if(umaFatura.getCancelada() == 1){
                                System.out.println('\n' + "Fatura ja cancelada");
                                break;
                            }
                            String dataCancelamento = Console.readLine('\n' + "Digite a data de cancelamento do cliente(DD/MM/YYYY): ");
                            try{
                                umaFatura.setDataCancelamento(dataCancelamento);
                            } catch (DataInvalidaException e) {
                                System.out.println('\n' + e.getMessage());
                            }
                            List<ItemFaturado> itensFaturados = umaFatura.getItensFaturados();
                            if(itensFaturados.isEmpty()){
                                umaFatura.setCancelada(1);
                                System.out.println('\n' + "Fatura cancelada com sucesso!" );
                                break;
                            }
                            List<Integer> listaDeIds = new ArrayList<Integer>();
                            for(ItemFaturado itemFaturado : itensFaturados){
                                listaDeIds.add(itemFaturado.getId());
                            }
                            try{
                                for(Integer x : listaDeIds){
                                    itemFaturadoService.remover(x);
                                }
                            } catch (EntidadeNaoEncontradaException e) {
                                System.out.println('\n' + e.getMessage());
                            }
                            umaFatura.setCancelada(1);
                            System.out.println('\n' + "Fatura cancelada com sucesso!");
                        }
                        // Listando Faturas
                        case 4->{
                            List<Fatura> faturas = umCliente.getFaturas();
                            if(faturas.isEmpty()){
                                System.out.println('\n' + "Cliente de nome " + umCliente.getNome() + " nao possui faturas");
                                break;
                            }
                            System.out.println('\n' + "Faturas do cliente " + umCliente.getNome());
                            for(Fatura fatura : faturas){
                                System.out.println('\n' + fatura.toString());
                                System.out.println('\n' + "Itens faturados da fatura " + fatura.getNumFatura());
                                List<ItemFaturado> itensFaturados = fatura.getItensFaturados();
                                for(ItemFaturado itemFaturado : itensFaturados){
                                    System.out.println('\n' + itemFaturado.toString());
                                }
                            }
                        }
                        default -> {
                            System.out.println('\n' + "Opção inválida!");
                        }
                    }
                }
                case 5->{
                    System.out.println('\n' + "Deseja listar todos os clientes, os pedidos de um certo cliente, ou as faturas de um certo cliente?");
                    int opcaoLista = Console.readInt('\n' + "1 para todos clientes, 2 para pedidos, 3 para faturas");
                    switch(opcaoLista){
                        case 1->{
                            List<Cliente> clientes = clienteService.recuperarClientes();
                            if(clientes.isEmpty()){
                                System.out.println('\n' + "Nao existem clientes");
                                break;
                            }
                            for(Cliente cliente : clientes){
                                System.out.println('\n' + cliente.toString());
                            }
                        }
                        case 2-> {
                            int idCliente = Console.readInt('\n' + "Digite id do cliente que deseja listar pedidos: ");
                            try {
                                Cliente clienteLista = clienteService.recuperarClientePorId(idCliente);
                                List<Pedido> pedidos = pedidoService.recuperarPedidosPorCliente(idCliente);
                                if (pedidos.isEmpty()) {
                                    System.out.println('\n' + "Cliente " + clienteLista.getNome() + " nao possui pedidos");
                                    break;
                                }
                                for (Pedido pedido : pedidos) {
                                    System.out.println('\n' + pedido.toString());
                                }
                            } catch (EntidadeNaoEncontradaException e) {
                                System.out.println('\n' + e.getMessage());
                            }
                        }
                        case 3->{
                            int idCliente = Console.readInt('\n' + "Digite id do cliente que deseja listar faturas: ");
                            try{
                                Cliente clienteFatura = clienteService.recuperarClientePorId(idCliente);
                                List<Fatura> faturas = clienteFatura.getFaturas();
                                if(faturas.isEmpty()){
                                    System.out.println('\n' + "Cliente " + clienteFatura.getNome() + " nao possui faturas");
                                    break;
                                }
                                for(Fatura fatura : faturas){
                                    System.out.println('\n' + fatura.toString());
                                }
                            } catch (EntidadeNaoEncontradaException e) {
                                System.out.println('\n' + e.getMessage());
                            }
                        }
                        default -> {
                            System.out.println('\n' + "Opção inválida!");
                        }
                    }
                }
                case 6-> continua = false;
                default -> {
                    System.out.println('\n' + "Opção inválida!");
                }
            }
        }
    }
}
