package br.com.aula.listadecompras.domain.models;

import br.com.aula.listadecompras.domain.enums.StatusProduto;

public class ListaCompraItemModel {
    private int id;
    private int idListaCompra;
    private String nome;
    private int quantidade;
    private double valor;
    private StatusProduto comprado;
    private int idCategoria;

    private CategoriaModel categoria;

    public ListaCompraItemModel() {

    }

    public ListaCompraItemModel(int id, int idListaCompra, String nome, int quantidade, double valor, StatusProduto comprado, int idCategoria) {
        this.id = id;
        this.idListaCompra = idListaCompra;
        this.nome = nome;
        this.quantidade = quantidade;
        this.valor = valor;
        this.comprado = comprado;
        this.idCategoria = idCategoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdListaCompra() {
        return idListaCompra;
    }

    public void setIdListaCompra(int idListaCompra) {
        this.idListaCompra = idListaCompra;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public StatusProduto getComprado() {
        return comprado;
    }

    public void setComprado(StatusProduto comprado) {
        this.comprado = comprado;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public CategoriaModel getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaModel categoria) {
        this.categoria = categoria;
    }
}
