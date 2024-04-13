package br.com.aula.listadecompras.domain.models;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class CategoriaModel implements Parcelable {

    private int id;
    private String nome;

    public CategoriaModel() {

    }

    public CategoriaModel(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    protected CategoriaModel(Parcel in) {
        id = in.readInt();
        nome = in.readString();
    }

    public static final Creator<CategoriaModel> CREATOR = new Creator<CategoriaModel>() {
        @Override
        public CategoriaModel createFromParcel(Parcel in) {
            return new CategoriaModel(in);
        }

        @Override
        public CategoriaModel[] newArray(int size) {
            return new CategoriaModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put("nome", this.nome);
        return values;
    }
}
