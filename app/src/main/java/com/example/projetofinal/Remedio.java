package com.example.projetofinal;

public class Remedio {
    private int id;
    private String nome;
    private String laboratorio;
    private String categoria;
    private String descricao;
    private String dosagem;
    private int imagem;

    // Construtor padrão (obrigatório para o Retrofit)
    public Remedio() {
    }

    // Construtor auxiliar para facilitar o cadastro
    public Remedio(String nome, String laboratorio, String categoria, String descricao, String dosagem, int imagem) {
        this.nome = nome;
        this.laboratorio = laboratorio;
        this.categoria = categoria;
        this.descricao = descricao;
        this.dosagem = dosagem;
        this.imagem = imagem;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getLaboratorio() { return laboratorio; }
    public void setLaboratorio(String laboratorio) { this.laboratorio = laboratorio; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getDosagem() { return dosagem; }
    public void setDosagem(String dosagem) { this.dosagem = dosagem; }

    public int getImagem() { return imagem; }
    public void setImagem(int imagem) { this.imagem = imagem; }
}