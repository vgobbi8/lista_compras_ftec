package br.com.aula.listadecompras.domain.models;

public class CategoriaModel {

    private int id;
    private String nome;

    public CategoriaModel() {

    }

    public CategoriaModel(int id, String nome) {
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

}
