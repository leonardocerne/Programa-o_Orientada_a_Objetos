public enum Funcionarios {
    LEONARDO("Leonardo Brasil", 44, "Renata"),
    MARIANA("Mariana Brasil", 38, "Renata"),
    VICTOR("Victor Teles", 42,"Priscila"),
    MILENA("Milena Freitas", 36, "Marcela")
    ;

    private String nome;
    private int tamanhope;
    private String nomemae;

    private Funcionarios(String nome, int tamanhope, String nomemae){
        this.nome = nome;
        this.tamanhope = tamanhope;
        this.nomemae = nomemae;
    }

    public String getNomeF(){
        return nome;
    }
    public int getTamanhoPe(){
        return tamanhope;
    }

    public String getNomeMae(){
        return nomemae;
    }

    public String getNomeMai(){
        return nome.toUpperCase();
    }
}
