public class Faca extends Talher {
    
    public Faca(String tamanho, boolean lavado){
        super(tamanho, lavado);
    }

    public void usar(){
        lavado = false;
        System.out.println("Cortando...");
    }
}
