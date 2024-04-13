package br.com.aula.listadecompras.domain.models;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class ListaComprasModel implements Parcelable  {
    private int id;
    private String nome;




    public ListaComprasModel(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }


    protected ListaComprasModel(Parcel in) {
        id = in.readInt();
        nome = in.readString();
    }

    public static final Creator<ListaComprasModel> CREATOR = new Creator<ListaComprasModel>() {
        @Override
        public ListaComprasModel createFromParcel(Parcel in) {
            return new ListaComprasModel(in);
        }

        @Override
        public ListaComprasModel[] newArray(int size) {
            return new ListaComprasModel[size];
        }
    };

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.nome);
    }
}
