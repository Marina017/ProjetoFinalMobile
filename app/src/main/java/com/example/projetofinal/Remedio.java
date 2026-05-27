package com.example.projetofinal;

public class Remedio {
    private String nome;
    private String categoria;
    private String descricao;
    private int imagemResId;

    public Remedio(String nome, String categoria, String descricao, int imagemResId) {
        this.nome = nome;
        this.categoria = categoria;
        this.descricao = descricao;
        this.imagemResId = imagemResId;
    }

    public String getNome() { return nome; }
    public String getCategoria() { return categoria; }
    public String getDescricao() { return descricao; }
    public int getImagemResId() { return imagemResId; }
}