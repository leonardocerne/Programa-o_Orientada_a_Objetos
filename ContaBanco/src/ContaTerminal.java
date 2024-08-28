import java.util.Scanner;

public class ContaTerminal {
    public static void main(String[] args){
        Scanner entrada = new Scanner(System.in);
        System.out.println("Digite o número da agência: ");
        String agencia = entrada.nextLine();
        System.out.println("Digite o número da conta: ");
        int num = entrada.nextInt();
        entrada.nextLine();
        System.out.println("Digite o nome do cliente: ");
        String cliente = entrada.nextLine();
        System.out.println("Digite o saldo da conta: ");
        double saldo = entrada.nextDouble();
        System.out.println("Olá " + cliente + ", obrigado por criar uma conta em nosso banco, sua agência é " + agencia + ", conta " + num + " e seu saldo " + saldo + " já está disponível para saque");
        entrada.close();
    }
}
