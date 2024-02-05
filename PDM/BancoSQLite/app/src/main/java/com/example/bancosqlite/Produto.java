package com.example.bancosqlite;

public class Produto {
    public int id;
    public String nomne;
    public double preco;
    public int quantidade;

    //Construtor
    public Produto(int id, String nomne, double preco, int quantidade) {
        this.id = id;
        this.nomne = nomne;
        this.preco = preco;
        this.quantidade = quantidade;
    }
}
