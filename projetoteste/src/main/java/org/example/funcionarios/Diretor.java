package org.example.funcionarios;

public class Diretor extends Funcionario{
   int idade;
   String nome;
   double salario;
   double conta = 0;

    public Diretor(int idade, String nome, double salario) {
        this.idade = idade;
        this.nome = nome;
        this.salario = salario;
    }
    public void trabalhar(){
        System.out.println("Ola escola meu nome e " + nome + " e isso e um discurso");
    }
}
