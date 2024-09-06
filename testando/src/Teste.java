public class Teste {
    int x;
    String nome;
    double valor;
    public int getX() {
        return x;
    }
    public void  setX(int x) throws ExceptionX {
        if(x > 50) throw new ExceptionX();
        this.x = x;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Teste(int x, String nome, double valor) {
        this.x = x;
        this.nome = nome;
        this.valor = valor;
    }
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
}
