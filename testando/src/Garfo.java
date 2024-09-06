public class Garfo extends Talher {
    
    public Garfo(String tamanho, boolean lavado) {
        super(tamanho, lavado);
    }

    public void usar(){
        lavado = false;
        System.out.println("Comendo...");
    }
    
}
