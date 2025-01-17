package com.carlosribeiro.util;

import com.carlosribeiro.model.Usuario;

public class Tarefa extends Thread {
    private Usuario[] usuarios;
    public void setUsuarios(Usuario[] usuarios) {
        this.usuarios = usuarios;
    }
    @Override
    public void run() {
        for (Usuario usuario : usuarios) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Enviando email para " + usuario.getEmail());
        }
        System.out.println("Todos os emails foram enviados.");
    }
}
