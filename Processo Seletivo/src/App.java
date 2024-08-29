import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Processo Seletivo");
        Scanner entrada = new Scanner(System.in);
        selecaoCandidatos(entrada);
        imprimirSelecionados(entrada);
        String[] candidatos = {"Leo", "Armando", "Carlos"};
        for(String candidato: candidatos){
            entrandoEmContato(candidato);
        }
        entrada.close();
    }

    static void imprimirSelecionados(Scanner entrada){
        String [] lista = new String[5];
        for(int i = 0; i < 5; i++){
            System.out.println("Insira o nome do candidato Selecionado: ");
            String candidato = entrada.nextLine();
            lista[i] = candidato;
        }
        for(String candidato: lista){
            System.out.println("O candidato selecionado foi o(a) " + candidato);
        }
    }

    static double valorPretendido(){
        return ThreadLocalRandom.current().nextDouble(1800, 2001);
    }


    static void selecaoCandidatos(Scanner entrada){
        int candidatosselecionados = 0;
        double salariobase = 2000.0;
        while(candidatosselecionados < 5){
            System.out.println("Insira o nome do candidato: ");
            String candidato = entrada.nextLine();
            double salariopretendido = valorPretendido();
            System.out.println("O candidato " + candidato + " solicitou este valor de salário: " + salariopretendido);
            if(salariobase >= salariopretendido){
                System.out.println("O candidato " + candidato + " foi selecionado para a vaga");
                candidatosselecionados++;
            }
        }
    }

    static void entrandoEmContato(String candidato){
        int tentativas = 1;
        boolean continuar = true;
        boolean atendeu = false;
        do{
            atendeu = atender();
            continuar = !atendeu;
            if(continuar) tentativas++;
            else System.out.println("CONTATO REALIZADO COM SUCESSO");
        }while(continuar && tentativas < 3);

        if(atendeu) System.out.println("Conseguimos realizar contato com o candidato " + candidato + " na tentativa " + tentativas);
        else System.out.println("Nao conseguimos realizar contato com o candidato " + candidato + ", numero maximo de tentativas atingido");
    }

    static boolean atender(){
        return new Random().nextInt(3)==1;
    }

    static void analisarCandidato(double salariopretendido){
        double salariobase = 2000.0;
        if(salariopretendido < salariobase) {
            System.out.println("LIGAR PARA O CANDIDATO");
        } else if(salariopretendido == salariobase){
            System.out.println("LIGAR PARA O CANDIDATO COM CONTRA PROPOSTA");
        } else {
            System.out.println("AGUARDANDO O RESULTADO DOS DEMAIS CANDIDATOS");
        }
    }

}
