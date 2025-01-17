package projeto.Principais;

import corejava.Console;
import projeto.exceptions.ClasseComItensAssociadosException;
import projeto.exceptions.EntidadeNaoEncontradaException;
import projeto.model.ItemFaturado;
import projeto.model.Livro;
import projeto.service.LivroService;

import java.util.List;

public class PrincipalLivro {
    public LivroService livroService = new LivroService();
    public void principal(){
        Livro umLivro;
        boolean continua = true;
        while(continua){
            System.out.println('\n' + "========================================================");
            System.out.println('\n' + "O que você deseja fazer?");
            System.out.println('\n' + "1. Cadastrar um livro");
            System.out.println('\n' + "2. Alterar um livro");
            System.out.println('\n' + "3. Remover um livro");
            System.out.println('\n' + "4. Listar todos livros");
            System.out.println('\n' + "5. Listar livros nunca faturados");
            System.out.println('\n' + "6. Listar itens faturados de um livro em um mês e ano");
            System.out.println('\n' + "7. Listar livros faturados em um mês e ano");
            System.out.println('\n' + "8. Voltar");
            int opcao = Console.readInt('\n' + "Digite um número de 1 a 8: ");
            switch (opcao){
                case 1->{
                    String titulo = Console.readLine('\n' + "Digite o titulo do livro: ");
                    String isbn = Console.readLine('\n' + "Digite o isbn do livro: ");
                    String descricao = Console.readLine('\n' + "Digite o descricao do livro: ");
                    int qtdEstoque = Console.readInt('\n' + "Digite o quantidade do livro no estoque: ");
                    double preco = Console.readDouble('\n' + "Digite o preço do livro: ");
                    umLivro = new Livro(titulo, isbn, descricao, qtdEstoque, preco);
                    livroService.incluir(umLivro);
                    System.out.println('\n' + "Livro cadastrado com sucesso!");
                }
                case 2->{
                    int idAlteracao = Console.readInt('n' + "Digite o id do livro que deseja alterar: ");
                    try{
                        umLivro = livroService.recuperarLivroPorId(idAlteracao);
                        System.out.println('\n' + "O que você deseja alterar?");
                        System.out.println('\n' + "1. Titulo");
                        System.out.println('\n' + "2. ISBN");
                        System.out.println('\n' + "3. Descrição");
                        System.out.println('\n' + "4. Quantidade no estoque");
                        int opcaoAlteracao = Console.readInt('\n' + "Digite um numero de 1 a 4: ");
                        switch (opcaoAlteracao){
                            case 1->{
                                String novoTitulo = Console.readLine('\n' + "Digite o novo titulo do livro: ");
                                umLivro.setTitulo(novoTitulo);
                                System.out.println('\n' + "Titulo alterado com sucesso!");
                            }
                            case 2->{
                                String novoISBN = Console.readLine('\n' + "Digite o novo ISBN do livro: ");
                                umLivro.setIsbn(novoISBN);
                                System.out.println('\n' + "ISBN alterado com sucesso!");
                            }
                            case 3->{
                                String novaDescricao = Console.readLine('\n' + "Digite a nova descricao do livro: ");
                                umLivro.setDescricao(novaDescricao);
                                System.out.println('\n' + "Descricao alterado com sucesso!");
                            }
                            case 4->{
                                int soma = Console.readInt('\n' + "Digite quanto quer adicionar ao estoque: ");
                                umLivro.setQtdEstoque(soma + umLivro.getQtdEstoque());
                                System.out.println('\n' + "Estoque alterado com sucesso!");
                            }
                            default -> {
                                System.out.println('\n' + "Opção inválida!");
                            }
                        }
                    } catch (EntidadeNaoEncontradaException e) {
                        System.out.println('\n' + e.getMessage());
                    }


                }
                case 3->{
                    int idRemocao = Console.readInt('\n' + "Digite o id do livro que deseja remover: ");
                    try{
                        umLivro = livroService.recuperarLivroPorId(idRemocao);
                        livroService.remover(idRemocao);
                        System.out.println('\n' + "Livro removido com sucesso");
                    } catch (EntidadeNaoEncontradaException | ClasseComItensAssociadosException e) {
                        System.out.println('\n' + e.getMessage());
                    }
                }
                case 4->{
                    List<Livro> livros = livroService.recuperarLivros();
                    if(livros.isEmpty()){
                        System.out.println('\n' + "Sem livros cadastrados.");
                        break;
                    }
                    for(Livro livro : livros){
                        System.out.println('\n' + livro.toString());
                    }
                }
                case 5-> {
                    List<Livro> livrosNunca = livroService.recuperarTodosLivrosNuncaFaturados();
                    List<Livro> livros = livroService.recuperarLivros();
                    if(livros.isEmpty()){
                        System.out.println('\n' + "Sem livros cadastrados.");
                        break;
                    }
                    if(livrosNunca.isEmpty()){
                        System.out.println('\n' + "Todos livros ja foram faturados");
                        break;
                    }
                    for(Livro livro : livrosNunca){
                        System.out.println('\n' + livro.toString());
                    }
                }
                case 6-> {
                    int mes = Console.readInt('\n' + "Digite o mes:");
                    int ano = Console.readInt('\n' + "Digite o ano:");
                    int id = Console.readInt('\n' + "Digite o id do livro: ");
                    try{
                        List<ItemFaturado> itensMesAno = livroService.recuperarItensFaturadosMesAno(id, mes, ano);
                        System.out.println('\n' + "Lista de itens faturados do livro " + id + " no mes " + mes + " e ano " + ano);
                        for(ItemFaturado item : itensMesAno){
                            System.out.println('\n' + item.toString());
                        }
                    } catch(EntidadeNaoEncontradaException e) {
                        System.out.println('\n' + e.getMessage());
                    }
                }
                case 7-> {
                    int mes = Console.readInt('\n' + "Digite o mes:");
                    int ano = Console.readInt('\n' + "Digite o ano:");
                    try{
                        List<Livro> lista = livroService.recuperarLivrosFaturadosMesAno(mes, ano);
                        if(lista.isEmpty()){
                            System.out.println('\n' + "Sem livros faturados nesse mes e ano");
                            break;
                        }
                        for(Livro livro : lista){
                            List<ItemFaturado> itens = livroService.recuperarItensFaturadosMesAno(livro.getId(), mes, ano);
                            int c = 0;
                            for(ItemFaturado item : itens) c += item.getQtdFaturada();
                            System.out.println('\n' + "O livro " + livro.getTitulo() + " foi faturado " + c + " vezes nesse período.");
                        }
                    } catch (EntidadeNaoEncontradaException e) {
                        System.out.println('\n' + e.getMessage());
                    }
                }
                case 8-> continua = false;
                default -> {
                    System.out.println('\n' + "Opção inválida!");
                }
            }
        }
    }
}
