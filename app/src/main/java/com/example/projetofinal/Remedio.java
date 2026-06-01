package com.example.projetofinal;

import android.os.Parcel;
import android.os.Parcelable;

public class Remedio implements Parcelable {
    private String nome, categoria, descricao;
    private int imagemResId;
    private String quantidade, preco, contraindicacoes, quandoTomar;

    public Remedio(String nome, String categoria, String descricao, int imagemResId,
                   String quantidade, String preco, String contraindicacoes, String quandoTomar) {
        this.nome = nome;
        this.categoria = categoria;
        this.descricao = descricao;
        this.imagemResId = imagemResId;
        this.quantidade = quantidade;
        this.preco = preco;
        this.contraindicacoes = contraindicacoes;
        this.quandoTomar = quandoTomar;
    }

    protected Remedio(Parcel in) {
        nome = in.readString();
        categoria = in.readString();
        descricao = in.readString();
        imagemResId = in.readInt();
        quantidade = in.readString();
        preco = in.readString();
        contraindicacoes = in.readString();
        quandoTomar = in.readString();
    }

    public static final Creator<Remedio> CREATOR = new Creator<Remedio>() {
        @Override
        public Remedio createFromParcel(Parcel in) {
            return new Remedio(in);
        }

        @Override
        public Remedio[] newArray(int size) {
            return new Remedio[size];
        }
    };

    public String getNome() { return nome; }
    public String getCategoria() { return categoria; }
    public String getDescricao() { return descricao; }
    public int getImagemResId() { return imagemResId; }
    public String getQuantidade() { return quantidade; }
    public String getPreco() { return preco; }
    public String getContraindicacoes() { return contraindicacoes; }
    public String getQuandoTomar() { return quandoTomar; }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nome);
        dest.writeString(categoria);
        dest.writeString(descricao);
        dest.writeInt(imagemResId);
        dest.writeString(quantidade);
        dest.writeString(preco);
        dest.writeString(contraindicacoes);
        dest.writeString(quandoTomar);
    }
}