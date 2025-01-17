package com.carlosribeiro.util;

import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

public class FabricaDeDaos {
    public static <T> T getDAO(Class<T> tipo) {
        Reflections reflections = new Reflections("com.carlosribeiro.dao.impl");
        Set<Class<? extends T>> conjunto = reflections.getSubTypesOf(tipo);
        if (conjunto.size() != 1) {
            throw new RuntimeException(
                "Deve haver uma e apenas uma classe que implementa a interface " +
                    tipo.getName());
        }
        Class<? extends T> classe = conjunto.iterator().next();
        try {
            return classe.getDeclaredConstructor().newInstance();
        }
        catch (InstantiationException |
                 IllegalAccessException |
                 InvocationTargetException |
                 NoSuchMethodException e) {
                throw new RuntimeException(e);
        }
    }
}
