package Funcionalidades.TiposDeAparelho;

import Funcionalidades.Aparelho;

public class TipoDois extends Aparelho {
    public void selecionarMusica(String musica) {
        System.out.println("Selecionando musica " + musica + " pelo Youtube Music");
    }
    public void tocar() {
        System.out.println("Tocando musica no Youtube Music...");
    }
    public void pausar() {
        System.out.println("Pausando musica no Youtube Music...");
    }
    public void ligar(String numero) {
        System.out.println("Ligando pelo Telefone para o numero " + numero);
    }
    public void atender() {
        System.out.println("Atendendo chamada pelo Telefone");
    }
    public void iniciarCorreioDeVoz() {
        System.out.println("Iniciando correio de voz pelo Telefone");
    }
    public void acessarPagina(String pagina) {
        System.out.println("Acessando pagina " + pagina + " pelo Google Chrome");
    }
    public void atualizarPagina() {
        System.out.println("Atualizando pagina no Google Chrome");
    }
    public void adicionarNovaAba() {
        System.out.println("Adicionando nova aba ao Google Chrome");
    }
}
