public class Interace {
    public static void main(String[] args) throws ExceptionX {
        Teste testador = new Teste(5, "leo", 5.5);
        System.out.println("testador = " + testador.getNome() + " " + testador.getX() + " " + testador.getValor());
        testador.setNome("Leo");
        testador.setX(1);
        testador.setValor(10.5);
        System.out.println("testador = " + testador.getNome() + " " + testador.getX() + " " + testador.getValor());

        Funcionarios leo = Funcionarios.MILENA;

        System.out.println(leo.getNomeMae());

        Garfo garfo = new Garfo("grande", false);
        Faca faca = new Faca("grande", false);
        faca.lavar();
        faca.ls();
        garfo.lavar();
        garfo.ls();
        faca.usar();
        garfo.usar();
        garfo.ls();
        faca.ls();
    }
}
