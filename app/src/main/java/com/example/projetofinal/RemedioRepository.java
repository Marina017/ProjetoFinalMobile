package com.example.projetofinal;

import java.util.ArrayList;
import java.util.List;

public class RemedioRepository {

    private static final List<Remedio> lista = new ArrayList<>();

    public static List<Remedio> getLista() {
        return lista;
    }

    public static void adicionar(Remedio remedio) {
        lista.add(remedio);
    }
}