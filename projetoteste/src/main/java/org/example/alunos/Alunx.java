package org.example.alunos;

public abstract class Alunx {
    String nome;
    int idade;
    int matricula;

    public int getMatricula() {
        return matricula;
    }
    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }
    public void estudar(){
        System.out.println("Estudando");
    }
    public abstract void banheiro();

    @Override
    public String toString() {
        return "Alunx{" +
                "nome='" + nome + '\'' +
                ", idade=" + idade +
                ", matricula=" + matricula +
                '}';
    }
}
