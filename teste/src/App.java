import java.util.Scanner;

public class App {
    public static void main(String[] args) throws CepException{

        Scanner entrada = new Scanner(System.in);

        double c = Opp.add(10, 2);
        System.out.println(c);

        System.out.println("Insira seu CEP: ");
        int cep = entrada.nextInt();

        try{   
            String cepf = Transformador.formatarCep(cep);

            System.out.println(cepf);

        } catch (CepException e) {
            System.out.println("Você inseriu um CEP invalido");
        }

        entrada.close();
    }
}
