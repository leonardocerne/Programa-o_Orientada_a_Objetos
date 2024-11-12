package org.example.funcionarios;

public class Professor extends Funcionario {
    private String nome;
    private double salario;
    private int idade;
    private double conta = 0;
    public Professor(String nome, double salario, int idade) {
        this.nome = nome;
        this.salario = salario;
        this.idade = idade;
    }
    public void trabalhar(){
        System.out.println("materiamateria aula exercicio aula");
    }
}
