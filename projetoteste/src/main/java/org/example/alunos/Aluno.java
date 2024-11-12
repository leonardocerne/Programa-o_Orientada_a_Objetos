package org.example.alunos;

public class Aluno extends Alunx {
    int matricula;
    String nome;
    int idade;

    public void banheiro(){
        System.out.println("eu, " + super.nome + ", estou mijando");
    }
    public void jogarBola(){
        System.out.println("eu, " + super.nome + ", estou jogando bola");
    }
}
