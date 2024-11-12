package org.example;
import java.util.ArrayList;
import java.util.Scanner;
import org.example.alunos.*;
import org.example.funcionarios.*;
public class Escola {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        ArrayList listaFun = new ArrayList<>();
        ArrayList listaAl = new ArrayList<>();
        String nome;
        int idade;
        int matricula;
        double salario;
        boolean flag = true;
        boolean flag2 = true;
        boolean flag3 = true;
        while (flag){
            System.out.println("Escolha uma das opções:");
            System.out.println("1-Menu funcionario");
            System.out.println("2-Menu aluno");
            System.out.println("3-Sair");
            int x = entrada.nextInt();
            switch(x){
                case 1:
                    while(flag2){
                        System.out.println("Escolha uma das opções:");
                        System.out.println("1 - Cadastrar aluno");
                        System.out.println("2 - Cadastrar aluna");
                        System.out.println("3 - Mandar aluno pro banheiro");
                        System.out.println("4 - Listar alunos");
                        System.out.println("5 - Voltar");
                        x = entrada.nextInt();
                        switch(x) {
                            case 1:
                                System.out.println("Digite matricula, nome e idade do aluno:");
                                matricula = entrada.nextInt();
                                entrada.nextLine();
                                nome = entrada.nextLine();
                                idade = entrada.nextInt();
                                entrada.nextLine();
                                Aluno umAluno = new Aluno();
                                umAluno.setMatricula(matricula);
                                umAluno.setNome(nome);
                                umAluno.setIdade(idade);
                                listaAl.add(umAluno);
                                break;
                            case 2:
                                System.out.println("Digite matricula, nome e idade da aluna:");
                                // Aluna umaAluna = new Aluna(entrada.nextInt(), entrada.nextLine(), entrada.nextInt());
                                // listaAl.add(umaAluna);
                                break;
                            case 3:
                                System.out.println("Insira indice do aluno que vai no banheiro");
                                int in = entrada.nextInt() - 1;
                                Alunx umAlunx = (Alunx) listaAl.get(in);
                                umAlunx.banheiro();
                                break;
                            case 4:
                                System.out.println("Aqui estão todos os alunos:");
                                for (int i = 0; i < listaAl.size(); i++) {
                                    Alunx alunx2 = (Alunx) listaAl.get(i);
                                    System.out.println("Matrícula: " + alunx2.getMatricula() + ", Nome: " + alunx2.getNome() + ", Idade: " + alunx2.getIdade());
                                }
                                break;
                            case 5:
                                flag2 = false;
                                break;
                            default:
                                System.out.println("Opçao invalida");
                        }
                    }
                    break;
                case 2:
                    while(flag3){
                        System.out.println("Escolha uma das opções:");
                        System.out.println("1 - Cadastrar professor");
                        System.out.println("2 - Cadastrar Inspetor");
                        System.out.println("3 - Cadastrar diretor");
                        System.out.println("4 - Listar Funcionarios");
                        System.out.println("5 - Trabalhar");
                        System.out.println("6 - Voltar");
                        x = entrada.nextInt();
                        switch(x){
                            case 1:
                                System.out.println("Digite o nome, o salário e a idade do professor:");
                                nome = entrada.nextLine();
                                salario = entrada.nextDouble();
                                entrada.nextLine();
                                idade = entrada.nextInt();
                                Professor professor = new Professor(nome, salario, idade);
                                listaFun.add(professor);
                                break;
                            case 2:
                                System.out.println("Digite o nome, o salário e a idade do inspetor:");
                                nome = entrada.nextLine();
                                salario = entrada.nextDouble();
                                entrada.nextLine();
                                idade = entrada.nextInt();
                                Inspetor inspetor = new Inspetor(nome, salario, idade);
                                listaFun.add(inspetor);
                                break;
                            case 3:
                                System.out.println("Digite o nome, o salário e a idade do diretor:");
                                nome = entrada.nextLine();
                                salario = entrada.nextDouble();
                                entrada.nextLine();
                                idade = entrada.nextInt();
                                Diretor diretor = new Diretor(idade, nome, salario);
                                listaFun.add(diretor);
                                break;
                            case 4:
                                System.out.println("Aqui estão todos os funcionários:");
                                for (int i = 0; i < listaFun.size(); i++) {
                                    Funcionario funcionario = (Funcionario) listaFun.get(i);
                                    System.out.println(i + " - " + funcionario.getNome());
                                }
                                break;
                            case 5:
                                System.out.println("Insira indice do funcionario que vai trabalhar");
                                int in = entrada.nextInt();
                                Funcionario funcionario = (Funcionario) listaFun.get(in);
                                funcionario.trabalhar();
                                break;
                            case 6:
                                flag3 = false;
                                break;
                            default:
                                System.out.println("Opção invalida");
                        }
                    }
                    break;
                case 3:
                    flag = false;
                    break;
                default:
                    System.out.println("Opção invalida");
            }
        }
    }
}