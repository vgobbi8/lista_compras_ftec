package br.com.aula.listadecompras.infra.data;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SqliteHelper extends SQLiteOpenHelper {

    private static String CREATE_TAB001 = "CREATE TABLE TAB001_Categorias (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT)";

    private static String CREATE_COM001 = "CREATE TABLE COM001_ListasDeCompras (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT)";

    private static String CREATE_COM002 = "CREATE TABLE COM002_ListasDeComprasItens (id INTEGER PRIMARY KEY AUTOINCREMENT, idListaCompra INTEGER, idCategoria INTEGER, nome TEXT, quantidade INTEGER, comprado INTEGER)";

    private static String POPULATE_TAB001 = "INSERT INTO TAB001_Categorias (nome) VALUES ('Alimentos'),('Bebidas'),('Higiene'),('Limpeza')";

    private static String POPULATE_COM001 = "INSERT INTO COM001_ListasDeCompras (nome) VALUES ('Compras do mês'),('Compras do final de semana'),('Compras do churrasco')";
    
    private static final String DATABASE_NAME = "ListaDeCompras.db";
    private static final int DATABASE_VERSION = 1;

    public SqliteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TAB001);
        db.execSQL(CREATE_COM001);
        db.execSQL(CREATE_COM002);
        db.execSQL(POPULATE_TAB001);
        db.execSQL(POPULATE_COM001);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
