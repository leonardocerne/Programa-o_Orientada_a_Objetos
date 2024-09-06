public abstract class Talher {
    String tamanho;
    boolean lavado;

    public Talher(String tamanho, boolean lavado) {
        this.tamanho = tamanho;
        this.lavado = lavado;
    }

    public void lavar(){
        if(!this.lavado) lavado = true;
    }

    public abstract void usar();
    
    public void ls(){
        if(this.lavado) System.out.println("Limpo");
        else System.out.println("Sujo");
    }

}
