package br.com.aula.listadecompras.domain.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import br.com.aula.listadecompras.domain.enums.StatusProduto;

public class ListaCompraItemModel implements Parcelable {
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

    protected ListaCompraItemModel(Parcel in) {
        id = in.readInt();
        idListaCompra = in.readInt();
        nome = in.readString();
        quantidade = in.readInt();
        valor = in.readDouble();
        comprado = StatusProduto.values()[in.readInt()];
        idCategoria = in.readInt();
        categoria = in.readParcelable(CategoriaModel.class.getClassLoader());

    }

    public static final Creator<ListaCompraItemModel> CREATOR = new Creator<ListaCompraItemModel>() {
        @Override
        public ListaCompraItemModel createFromParcel(Parcel in) {
            return new ListaCompraItemModel(in);
        }

        @Override
        public ListaCompraItemModel[] newArray(int size) {
            return new ListaCompraItemModel[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.idListaCompra);
        dest.writeString(this.nome);
        dest.writeInt(this.quantidade);
        dest.writeDouble(this.valor);
        dest.writeInt(this.comprado == null ? -1 : this.comprado.ordinal());
        dest.writeInt(this.idCategoria);
        dest.writeParcelable(this.categoria, flags);

    }
}
