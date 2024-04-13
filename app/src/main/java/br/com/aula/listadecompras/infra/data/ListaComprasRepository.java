package br.com.aula.listadecompras.infra.data;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import br.com.aula.listadecompras.domain.models.ListaComprasModel;

interface IListaComprasRepository {
    void inserir(ListaComprasModel listaComprasModel);

    void alterar(ListaComprasModel listaComprasModel);

    void excluir(int id);

    ArrayList<ListaComprasModel> retornarTodos();

    ListaComprasModel retornarPorId(int id);
}

public class ListaComprasRepository extends BaseRepository implements IListaComprasRepository {

    private static final String TABLE_NAME = "COM001_ListaCompras";

    public ListaComprasRepository(SQLiteDatabase database) {
        super(database);
    }


    @Override
    public void inserir(ListaComprasModel listaComprasModel) {
        long id = database.insert(TABLE_NAME, null, listaComprasModel.toContentValues());
        listaComprasModel.setId((int) id);

    }

    @Override
    public void alterar(ListaComprasModel listaComprasModel) {
        database.update(TABLE_NAME, listaComprasModel.toContentValues(), "id = ?", new String[]{String.valueOf(listaComprasModel.getId())});
    }

    @Override
    public void excluir(int id) {
        database.delete(TABLE_NAME, "id = ?", new String[]{String.valueOf(id)});
    }

    @Override
    public ArrayList<ListaComprasModel> retornarTodos() {
        ArrayList<ListaComprasModel> listas = new ArrayList<>();
        Cursor cursor = database.query(TABLE_NAME, null, null, null, null, null, "nome");
        while (cursor.moveToNext()) {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
            @SuppressLint("Range") String nome = cursor.getString(cursor.getColumnIndex("nome"));
            listas.add(new ListaComprasModel(id, nome));
        }
        return listas;
    }

    @Override
    public ListaComprasModel retornarPorId(int id) {
        Cursor cursor = database.query(TABLE_NAME, null, "id = ?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor.moveToNext()) {
            @SuppressLint("Range") String nome = cursor.getString(cursor.getColumnIndex("nome"));
            return new ListaComprasModel(id, nome);
        }
        return null;
    }

}
