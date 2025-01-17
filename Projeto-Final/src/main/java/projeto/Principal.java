package projeto;

import corejava.Console;
import projeto.Principais.PrincipalCliente;
import projeto.Principais.PrincipalLivro;
import projeto.Principais.PrincipalPedido;
import projeto.dao.*;
import projeto.exceptions.ClasseComItensAssociadosException;
import projeto.exceptions.DataInvalidaException;
import projeto.exceptions.EntidadeNaoEncontradaException;
import projeto.model.*;
import projeto.service.*;
import projeto.util.FabricaDeDaos;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Principal {
    public static void main(String[] args) {
        //recuperarDados();
        PrincipalCliente principalCliente = new PrincipalCliente();
        PrincipalLivro principalLivro = new PrincipalLivro();
        PrincipalPedido principalPedido = new PrincipalPedido();
        boolean continua = true;
        while (continua) {
            System.out.println('\n' + "========================================================");
            System.out.println('\n' + "O que você deseja fazer?");
            System.out.println('\n' + "1. Tratar Clientes");
            System.out.println("2. Tratar Livros");
            System.out.println("3. Tratar Pedidos");
            System.out.println("4. Testar Sistema");
            System.out.println("5. Sair");

            int opcao = Console.readInt('\n' + "Digite um número entre 1 e 5:");

            System.out.println();

            switch (opcao) {
                case 1 -> {
                    principalCliente.principal();
                }
                case 2 -> {
                    principalLivro.principal();
                }
                case 3 -> {
                    principalPedido.principal();
                }
                case 4->{
                    testarSistema();
                }
                case 5 -> {
                    salvarDados();
                    continua = false;
                }

                default -> System.out.println('\n' + "Opção inválida!");
            }
        }
    }

    private static void salvarDados() {
        ClienteDao clienteDao = FabricaDeDaos.getDAO(ClienteDao.class);
        FaturaDao faturaDao = FabricaDeDaos.getDAO(FaturaDao.class);
        ItemFaturadoDao itemFaturadoDao = FabricaDeDaos.getDAO(ItemFaturadoDao.class);
        ItemPedidoDao itemPedidoDao = FabricaDeDaos.getDAO(ItemPedidoDao.class);
        LivroDao livroDao = FabricaDeDaos.getDAO(LivroDao.class);
        PedidoDao pedidoDao = FabricaDeDaos.getDAO(PedidoDao.class);

        Map<Integer, Cliente> mapDeClientes = clienteDao.getMap();
        int contadorClientes = clienteDao.getContador();

        Map<Integer, Fatura> mapDeFaturas = faturaDao.getMap();
        int contadorFaturas = faturaDao.getContador();

        Map<Integer, ItemFaturado> mapDeItemFaturado = itemFaturadoDao.getMap();
        int contadorItemFaturado = itemFaturadoDao.getContador();

        Map<Integer, ItemPedido> mapDeItemPedido = itemPedidoDao.getMap();
        int contadorItemPedido = itemPedidoDao.getContador();

        Map<Integer, Livro> mapDeLivros = livroDao.getMap();
        int contadorLivros = livroDao.getContador();

        Map<Integer, Pedido> mapDePedidos = pedidoDao.getMap();
        int contadorPedidos = pedidoDao.getContador();

        try {
            FileOutputStream fos = new FileOutputStream("arquivo.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(mapDeClientes);
            oos.writeInt(contadorClientes);
            oos.writeObject(mapDeFaturas);
            oos.writeInt(contadorFaturas);
            oos.writeObject(mapDeItemFaturado);
            oos.writeInt(contadorItemFaturado);
            oos.writeObject(mapDeItemPedido);
            oos.writeInt(contadorItemPedido);
            oos.writeObject(mapDeLivros);
            oos.writeInt(contadorLivros);
            oos.writeObject(mapDePedidos);
            oos.writeInt(contadorPedidos);
            oos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void recuperarDados() {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("arquivo.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Map<Integer, Cliente> mapDeClientes = (Map<Integer, Cliente>) ois.readObject();
            int contadorClientes = ois.readInt();

            Map<Integer, Fatura> mapDeFaturas = (Map<Integer, Fatura>) ois.readObject();
            int contadorFaturas = ois.readInt();

            Map<Integer, ItemFaturado> mapDeItemFaturado = (Map<Integer, ItemFaturado>) ois.readObject();
            int contadorItemFaturado = ois.readInt();

            Map<Integer, ItemPedido> mapDeItemPedido = (Map<Integer, ItemPedido>) ois.readObject();
            int contadorItemPedido = ois.readInt();

            Map<Integer, Livro> mapDeLivros = (Map<Integer, Livro>) ois.readObject();
            int contadorLivros = ois.readInt();

            Map<Integer, Pedido> mapDePedidos = (Map<Integer, Pedido>) ois.readObject();
            int contadorPedidos = ois.readInt();

            ClienteDao clienteDao = FabricaDeDaos.getDAO(ClienteDao.class);
            FaturaDao faturaDao = FabricaDeDaos.getDAO(FaturaDao.class);
            ItemFaturadoDao itemFaturadoDao = FabricaDeDaos.getDAO(ItemFaturadoDao.class);
            ItemPedidoDao itemPedidoDao = FabricaDeDaos.getDAO(ItemPedidoDao.class);
            LivroDao livroDao = FabricaDeDaos.getDAO(LivroDao.class);
            PedidoDao pedidoDao = FabricaDeDaos.getDAO(PedidoDao.class);
            clienteDao.setMap(mapDeClientes);
            clienteDao.setContador(contadorClientes);
            faturaDao.setMap(mapDeFaturas);
            faturaDao.setContador(contadorFaturas);
            itemFaturadoDao.setMap(mapDeItemFaturado);
            itemFaturadoDao.setContador(contadorItemFaturado);
            itemPedidoDao.setMap(mapDeItemPedido);
            itemPedidoDao.setContador(contadorItemPedido);
            livroDao.setMap(mapDeLivros);
            livroDao.setContador(contadorLivros);
            pedidoDao.setMap(mapDePedidos);
            pedidoDao.setContador(contadorPedidos);
            ois.close();
        } catch (FileNotFoundException e) {
            System.out.println("o arquivo não existe e será criado.");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void testarSistema(){
        LivroService livroService = new LivroService();
        ClienteService clienteService = new ClienteService();
        ItemPedidoService itemPedidoService = new ItemPedidoService();
        PedidoService pedidoService = new PedidoService();
        ItemFaturadoService itemFaturadoService = new ItemFaturadoService();
        FaturaService faturaService = new FaturaService();
        //Cadastrando livros
        Livro livro_1 = new Livro ("Aaa", "10", "Aaaa", 10, 100);
        Livro livro_2 = new Livro ("Bbb", "20", "Bbbb", 20, 200);
        Livro livro_3 = new Livro ("Ccc", "30", "Cccc", 30, 300);
        Livro livro_4 = new Livro ("Ddd", "40", "Dddd", 40, 400);
        Livro livro_5 = new Livro ("Eee", "50", "Eeee", 50, 500);
        livroService.incluir(livro_1);
        livroService.incluir(livro_2);
        livroService.incluir(livro_3);
        livroService.incluir(livro_4);
        livroService.incluir(livro_5);
        List<Livro> livros = livroService.recuperarLivros();
        System.out.println('\n' + "Lista de livros:");
        for(Livro livro : livros){
            System.out.println('\n' + livro.toString());
        }

        //Cadastrando clientes
        Cliente cliente_1 = new Cliente("Xxxx", "111", "", "11111111", "xxxx@gmail.com");
        Cliente cliente_2 = new Cliente("Yyyy", "222", "", "22222222", "yyyy@gmail.com");
        clienteService.incluir(cliente_1);
        clienteService.incluir(cliente_2);

        //Cadastrando pedidos
        try{
            Pedido pedido_1 = new Pedido("01/01/2025", cliente_1, "");
            ItemPedido itemPedido_1 = new ItemPedido(5,livro_1, pedido_1);
            ItemPedido itemPedido_2 = new ItemPedido(15,livro_2, pedido_1);
            itemPedidoService.incluir(itemPedido_1);
            itemPedidoService.incluir(itemPedido_2);
            pedidoService.incluir(pedido_1);

            Pedido pedido_2 = new Pedido("02/01/2025", cliente_1, "");
            ItemPedido itemPedido_3 = new ItemPedido(10,livro_1, pedido_2);
            ItemPedido itemPedido_4 = new ItemPedido(40, livro_3, pedido_2);
            itemPedidoService.incluir(itemPedido_3);
            itemPedidoService.incluir(itemPedido_4);
            pedidoService.incluir(pedido_2);

            Pedido pedido_3 = new Pedido("03/01/2025", cliente_1, "");
            ItemPedido itemPedido_5 = new ItemPedido(5,livro_1, pedido_3);
            ItemPedido itemPedido_6 = new ItemPedido(10,livro_3, pedido_3);
            itemPedidoService.incluir(itemPedido_5);
            itemPedidoService.incluir(itemPedido_6);
            pedidoService.incluir(pedido_3);

            Pedido pedido_4 = new Pedido("04/01/2025", cliente_1, "");
            ItemPedido itemPedido_7 = new ItemPedido(10,livro_2, pedido_4);
            ItemPedido itemPedido_8 = new ItemPedido(10,livro_3, pedido_4);
            ItemPedido itemPedido_9 = new ItemPedido(10, livro_4, pedido_4);
            itemPedidoService.incluir(itemPedido_7);
            itemPedidoService.incluir(itemPedido_8);
            itemPedidoService.incluir(itemPedido_9);
            pedidoService.incluir(pedido_4);

            Pedido pedido_5 = new Pedido("05/01/2025", cliente_1, "");
            ItemPedido itemPedido_10 = new ItemPedido(5,livro_2, pedido_5);
            ItemPedido itemPedido_11 = new ItemPedido(5,livro_3, pedido_5);
            ItemPedido itemPedido_12 = new ItemPedido(5, livro_4, pedido_5);
            itemPedidoService.incluir(itemPedido_10);
            itemPedidoService.incluir(itemPedido_11);
            itemPedidoService.incluir(itemPedido_12);
            pedidoService.incluir(pedido_5);

            List<Pedido> pedidos = pedidoService.recuperarPedidos();
            System.out.println('\n' + "Lista de pedidos:");
            for(Pedido pedido : pedidos){
                pedido.listarPedidoComItens();
            }

            //Listando livros novamente
            System.out.println('\n' + "Lista de livros:");
            for(Livro livro : livros) System.out.println('\n' + livro.toString());

            //Faturando faturas 1 e 2
            Fatura fatura_1 = new Fatura("01/01/2025", cliente_1);
            ItemFaturado itemFaturado_1 = new ItemFaturado(5, itemPedido_1, fatura_1);
            ItemFaturado itemFaturado_2 = new ItemFaturado(15, itemPedido_2, fatura_1);
            itemFaturadoService.incluir(itemFaturado_1);
            itemFaturadoService.incluir(itemFaturado_2);
            fatura_1.setValorTotal();
            fatura_1.setValorTotalDesconto();
            faturaService.incluir(fatura_1);

            Fatura fatura_2 = new Fatura("02/01/2025", cliente_1);
            ItemFaturado itemFaturado_3 = new ItemFaturado(10, itemPedido_3, fatura_2);
            ItemFaturado itemFaturado_4 = new ItemFaturado(40, itemPedido_4, fatura_2);
            itemFaturadoService.incluir(itemFaturado_3);
            itemFaturadoService.incluir(itemFaturado_4);
            fatura_2.setValorTotal();
            fatura_2.setValorTotalDesconto();
            faturaService.incluir(fatura_2);

            //Tentando cancelar fatura_2
            faturaService.cancelar(2);

            //Faturando faturas 3 e 4
            Fatura fatura_3 = new Fatura("03/01/2025", cliente_1);
            ItemFaturado itemFaturado_5 = new ItemFaturado(5, itemPedido_5, fatura_3);
            ItemFaturado itemFaturado_6 = new ItemFaturado(10, itemPedido_6, fatura_3);
            itemFaturadoService.incluir(itemFaturado_5);
            itemFaturadoService.incluir(itemFaturado_6);
            fatura_3.setValorTotal();
            fatura_3.setValorTotalDesconto();
            if(!fatura_3.getItensFaturados().isEmpty()) faturaService.incluir(fatura_3);

            Fatura fatura_4 = new Fatura("04/01/2025", cliente_1);
            ItemFaturado itemFaturado_7 = new ItemFaturado(10, itemPedido_7, fatura_4);
            ItemFaturado itemFaturado_8 = new ItemFaturado(10, itemPedido_8, fatura_4);
            ItemFaturado itemFaturado_9 = new ItemFaturado(10, itemPedido_9, fatura_4);
            itemFaturadoService.incluir(itemFaturado_7);
            itemFaturadoService.incluir(itemFaturado_8);
            itemFaturadoService.incluir(itemFaturado_9);
            fatura_4.setValorTotal();
            fatura_4.setValorTotalDesconto();
            if(!fatura_4.getItensFaturados().isEmpty()) faturaService.incluir(fatura_4);

            //Faturando fatura 5
            Fatura fatura_5 = new Fatura("01/02/2025", cliente_1);
            ItemFaturado itemFaturado_10 = new ItemFaturado(5, itemPedido_10, fatura_5);
            ItemFaturado itemFaturado_11 = new ItemFaturado(5, itemPedido_11, fatura_5);
            ItemFaturado itemFaturado_12 = new ItemFaturado(5, itemPedido_12, fatura_5);
            itemFaturadoService.incluir(itemFaturado_10);
            itemFaturadoService.incluir(itemFaturado_11);
            itemFaturadoService.incluir(itemFaturado_12);
            fatura_5.setValorTotal();
            fatura_5.setValorTotalDesconto();
            if(!fatura_5.getItensFaturados().isEmpty()) faturaService.incluir(fatura_5);

            //Lista de livros
            System.out.println('\n' + "Lista de livros:");
            for(Livro livro: livros){
                System.out.println('\n' + livro.toString());
            }

            List<Fatura> faturas = faturaService.recuperarFaturas();
            System.out.println('\n' + "Lista de faturas:");
            for(Fatura fatura : faturas){
                System.out.println('\n' + fatura.toString());
                System.out.println('\n' + "Itens faturados da fatura:");
                for(ItemFaturado itemFaturado : fatura.getItensFaturados()){
                    System.out.println('\n' + "Livro " + itemFaturado.getItemPedido().getLivro().getTitulo() + " - " + itemFaturado.getQtdFaturada());
                }
            }

            //Tentando cancelar pedido 5
            pedidoService.cancelarDefinido(pedido_5, "06/01/2025");

            //Tentando cancelar fatura 3
            faturaService.cancelarDefinido(3, "06/01/2005");

            //Tentando remover fatura 3
            faturaService.remover(3);

            //Tentando remover fatura 4
            Fatura umaFatura = faturaService.recuperarFaturaPorId(4);
            List<Integer> listaDeIds = new ArrayList<Integer>();
            List<ItemFaturado> itemFaturados = umaFatura.getItensFaturados();
            for(ItemFaturado itemFaturado : itemFaturados){
                listaDeIds.add(itemFaturado.getId());
            }
            for(Integer x : listaDeIds){
                itemFaturadoService.remover(x);
            }
            faturaService.remover(4);
            System.out.println('\n' + "Fatura " + 4 +  " removida com sucesso!");

            //Listando livros
            for(Livro livro: livros){
                System.out.println('\n' + livro.toString());
            }

            //Abastecendo estoques
            livro_1.setQtdEstoque(livro_1.getQtdEstoque() + 100);
            livro_2.setQtdEstoque(livro_2.getQtdEstoque() + 200);
            livro_3.setQtdEstoque(livro_3.getQtdEstoque() + 300);
            livro_4.setQtdEstoque(livro_4.getQtdEstoque() + 400);
            livro_5.setQtdEstoque(livro_5.getQtdEstoque() + 500);

            //Listando livros
            for(Livro livro: livros){
                System.out.println('\n' + livro.toString());
            }

            //Faturando faturas 1 e 2
            Fatura fatura_1_2 = new Fatura("01/02/2025", cliente_1);
            ItemFaturado itemFaturado_1_2 = new ItemFaturado(5, itemPedido_1, fatura_1_2);
            ItemFaturado itemFaturado_2_2 = new ItemFaturado(15, itemPedido_2, fatura_1_2);
            itemFaturadoService.incluir(itemFaturado_1_2);
            itemFaturadoService.incluir(itemFaturado_2_2);
            fatura_1_2.setValorTotal();
            fatura_1_2.setValorTotalDesconto();
            if(!fatura_1_2.getItensFaturados().isEmpty()) faturaService.incluir(fatura_1_2);

            Fatura fatura_2_2 = new Fatura("02/02/2025", cliente_1);
            ItemFaturado itemFaturado_3_2 = new ItemFaturado(10, itemPedido_3, fatura_2_2);
            ItemFaturado itemFaturado_4_2 = new ItemFaturado(40, itemPedido_4, fatura_2_2);
            itemFaturadoService.incluir(itemFaturado_3_2);
            itemFaturadoService.incluir(itemFaturado_4_2);
            fatura_2_2.setValorTotal();
            fatura_2_2.setValorTotalDesconto();
            if(!fatura_2_2.getItensFaturados().isEmpty()) faturaService.incluir(fatura_2_2);

            //Faturando faturas 3 e 4
            Fatura fatura_3_2 = new Fatura("03/02/2025", cliente_1);
            ItemFaturado itemFaturado_5_2 = new ItemFaturado(5, itemPedido_5, fatura_3_2);
            ItemFaturado itemFaturado_6_2 = new ItemFaturado(10, itemPedido_6, fatura_3_2);
            itemFaturadoService.incluir(itemFaturado_5_2);
            itemFaturadoService.incluir(itemFaturado_6_2);
            fatura_3_2.setValorTotal();
            fatura_3_2.setValorTotalDesconto();
            if(!fatura_3_2.getItensFaturados().isEmpty()) faturaService.incluir(fatura_3_2);

            Fatura fatura_4_2 = new Fatura("04/02/2025", cliente_1);
            ItemFaturado itemFaturado_7_2 = new ItemFaturado(10, itemPedido_7, fatura_4_2);
            ItemFaturado itemFaturado_8_2 = new ItemFaturado(10, itemPedido_8, fatura_4_2);
            ItemFaturado itemFaturado_9_2 = new ItemFaturado(10, itemPedido_9, fatura_4_2);
            itemFaturadoService.incluir(itemFaturado_7_2);
            itemFaturadoService.incluir(itemFaturado_8_2);
            itemFaturadoService.incluir(itemFaturado_9_2);
            fatura_4_2.setValorTotal();
            fatura_4_2.setValorTotalDesconto();
            if(!fatura_4_2.getItensFaturados().isEmpty()) faturaService.incluir(fatura_4_2);

            //Faturando fatura 5
            Fatura fatura_5_2 = new Fatura("05/02/2025", cliente_1);
            ItemFaturado itemFaturado_10_2 = new ItemFaturado(5, itemPedido_10, fatura_5_2);
            ItemFaturado itemFaturado_11_2 = new ItemFaturado(5, itemPedido_11, fatura_5_2);
            ItemFaturado itemFaturado_12_2 = new ItemFaturado(5, itemPedido_12, fatura_5_2);
            itemFaturadoService.incluir(itemFaturado_10_2);
            itemFaturadoService.incluir(itemFaturado_11_2);
            itemFaturadoService.incluir(itemFaturado_12_2);
            fatura_5_2.setValorTotal();
            fatura_5_2.setValorTotalDesconto();
            if(!fatura_5_2.getItensFaturados().isEmpty()) faturaService.incluir(fatura_5_2);

            //Relatorio 1

            System.out.println('\n' + '\n' + "RELATORIO 1:");
            List<ItemFaturado> itensLivro1 = livroService.recuperarItensFaturadosMesAno(livro_1.getId(), 01, 2025);
            System.out.println('\n' + "Lista de itens faturados do livro " + livro_1.getId() + " no mes " + 1 + " e ano " + 2025);
            for(ItemFaturado item : itensLivro1){
                System.out.println('\n' + item.toString());
            }

            //Relatorio 2

            System.out.println('\n' + '\n' + "RELATORIO 2:");
            List<Livro> livrosNunca = livroService.recuperarTodosLivrosNuncaFaturados();
            List<Livro> livrosaux = livroService.recuperarLivros();
            if(livros.isEmpty()){
                System.out.println('\n' + "Sem livros cadastrados.");
            }
            else if(livrosNunca.isEmpty()){
                System.out.println('\n' + "Todos livros ja foram faturados");
            }
            else {
                System.out.println('\n' + "Lista de livros nunca faturados:");
                for (Livro livro : livrosNunca) {
                    System.out.println('\n' + livro.toString());
                }
            }

            //Relatorio 3

            System.out.println('\n' + '\n' + "RELATORIO 3:");
            List<Livro> lista = livroService.recuperarLivrosFaturadosMesAno(02, 2025);
            if(lista.isEmpty()){
                System.out.println('\n' + "Sem livros faturados nesse mes e ano");
            } else {
                System.out.println('\n' + "Lista de livros faturados em fevereiro de 2025:");
                for (Livro livro : lista) {
                    List<ItemFaturado> itens = livroService.recuperarItensFaturadosMesAno(livro.getId(), 02, 2025);
                    int c = 0;
                    for (ItemFaturado item : itens) c += item.getQtdFaturada();
                    System.out.println('\n' + "O livro " + livro.getTitulo() + " foi faturado " + c + " vezes nesse período.");
                }
            }

            System.out.println("\n\n\nFIM DA EXECUCAO.");
        }catch (DataInvalidaException | EntidadeNaoEncontradaException | ClasseComItensAssociadosException e){
            System.out.println('\n' + e.getMessage());
        }
    }

}


