public class Transformador {
    public static void main(String[] args) {
        
    }
    static String formatarCep(int cep) throws CepException{
        String numero = Integer.toString(cep);
        if(numero.length() != 8) 
            throw new CepException();
        return numero;
    }
}
