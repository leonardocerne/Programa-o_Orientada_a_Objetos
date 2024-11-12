package org.example.funcionarios;

public abstract class Funcionario {
    private String nome;
    private double salario;
    private int idade;
    private double conta = 0;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public double getConta() {
        return conta;
    }

    public void receber(){
        conta += salario;
        System.out.println("recebi meu salario e agora minha conta esta com " + this.conta + " reais");
    }
    public abstract void trabalhar();
}
