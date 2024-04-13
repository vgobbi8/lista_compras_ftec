package br.com.aula.listadecompras.domain.models;

import android.content.ContentValues;

public class ListaComprasModel {


    private int id;
    private String nome;


    public ListaComprasModel(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }


    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("nome", nome);
        return values;
    }


}
