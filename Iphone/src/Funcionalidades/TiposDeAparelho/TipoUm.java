package Funcionalidades.TiposDeAparelho;

import Funcionalidades.Aparelho;

public class TipoUm extends Aparelho {
    public void selecionarMusica(String musica) {
        System.out.println("Selecionando musica " + musica + " pelo Spotify");
    }
    public void tocar() {
        System.out.println("Tocando musica no Spotify...");
    }
    public void pausar() {
        System.out.println("Pausando musica no Spotify...");
    }
    public void ligar(String numero) {
        System.out.println("Ligando pelo Whatsapp para o numero " + numero);
    }
    public void atender() {
        System.out.println("Atendendo chamada pelo Whatsapp");
    }
    public void iniciarCorreioDeVoz() {
        System.out.println("Iniciando correio de voz pelo Whatsapp");
    }
    public void acessarPagina(String pagina) {
        System.out.println("Acessando pagina " + pagina + " pelo Safari");
    }
    public void atualizarPagina() {
        System.out.println("Atualizando pagina no Safari");
    }
    public void adicionarNovaAba() {
        System.out.println("Adicionando nova aba ao Safari");
    }
    
}
