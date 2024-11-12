package com.carlosribeiro.util;

import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

public class FabricaDeServicos {

    public static <T> T getService(Class<T> tipo)
    {

        // Permite que a gente investigue as classes no package "com.carlosribeiro.dao.impl"
        Reflections reflections = new Reflections("com.carlosribeiro.service");
        // Nesse momento a variável tipo estará valendo ProdutoDAO.class
        // A linha abaixo verifica se no package "com.carlosribeiro.dao.impl"
        // existe uma classe subtipo de ProdutoDAO.class.
        Set<Class<? extends T>> conjunto = reflections.getSubTypesOf(tipo);
        // Vai retornar um Set contendo a classe JPAProdutoDAO.
        if(conjunto.size() != 1)  throw new RuntimeException("Deve haver uma e apenas uma classe do tipo " + tipo.getName());

        // Não pode haver mais de uma classe nesse package que estenda ProdutoDAO
        // caso contrário a gente não saberia qual utilizar.

        // Retorna a classe JPAProdutoDAO na variável classe.
        Class<? extends T> classe = conjunto.iterator().next();
        // Instancia um objeto do tipo JPAProdutoDAO usando o construtor Padrão
        try {
            return classe.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
