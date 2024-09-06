package Funcionalidades;

public abstract class Aparelho implements AparelhoTelefônico, Navegador, ReprodutorMusical {
    public abstract void selecionarMusica(String musica);
    public abstract void tocar();
    public abstract void pausar();
    public abstract void ligar(String numero);
    public abstract void atender();
    public abstract void iniciarCorreioDeVoz();
    public abstract void acessarPagina(String pagina);
    public abstract void atualizarPagina();
    public abstract void adicionarNovaAba();
}
