package com.carlosribeiro;

import com.carlosribeiro.model.Usuario;
import com.carlosribeiro.util.Tarefa;

public class PrincipalUsuario {
    private Usuario[] usuarios1 = {
            new Usuario("Lucas Borges", "lucas@gmail.com"),
            new Usuario("Luciana Barbosa", "luciana@Gmail.com")};
    private Usuario[] usuarios2 = {
            new Usuario("Joao Borges", "joao@gmail.com"),
            new Usuario("Juliana Barbosa", "juliana@Gmail.com")};
    public void principal() {
        Tarefa umaTarefa = new Tarefa();
        Tarefa outraTarefa = new Tarefa();
        umaTarefa.setUsuarios(usuarios1);
        outraTarefa.setUsuarios(usuarios2);
        umaTarefa.start();
        try {
            umaTarefa.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        outraTarefa.start();
        try {
            outraTarefa.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}