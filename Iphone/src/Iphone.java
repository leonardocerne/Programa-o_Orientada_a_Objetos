import Funcionalidades.Aparelho;
import Funcionalidades.TiposDeAparelho.TipoDois;
import Funcionalidades.TiposDeAparelho.TipoUm;

public class Iphone{
    public static void main(String[] args) throws Exception {
        Aparelho iphone = new TipoDois();
        iphone.acessarPagina("Youtube");
        iphone.adicionarNovaAba();
        iphone.ligar("21-988172804");
        Aparelho iphone2 = new TipoUm();
        iphone2.acessarPagina("Telegram");
        iphone2.adicionarNovaAba();
        iphone2.selecionarMusica("Lover, You Should've Come Over");
    }
}
