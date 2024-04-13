package br.com.aula.listadecompras.infra.data;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import br.com.aula.listadecompras.domain.models.CategoriaModel;

public class CategoriasRepository extends BaseRepository implements ICategoriasRepository {

    private static final String TABLE_NAME = "TAB001_Categorias";

    public CategoriasRepository(SQLiteDatabase database) {
        super(database);
    }

    @Override
    public void inserir(CategoriaModel categoriaModel) {
        categoriaModel.setId((int) database.insert(TABLE_NAME, null, categoriaModel.toContentValues()));
    }

    @Override
    public void alterar(CategoriaModel categoriaModel) {
        database.update(TABLE_NAME, categoriaModel.toContentValues(), "id = ?", new String[]{String.valueOf(categoriaModel.getId())});
    }

    @Override
    public void excluir(int id) {
        database.delete(TABLE_NAME, "id = ?", new String[]{String.valueOf(id)});
    }

    @SuppressLint("Range")
    @Override
    public ArrayList<CategoriaModel> retornarTodos() {
        Cursor cursor = database.query(TABLE_NAME, new String[]{"id", "nome"}, null, null, null, null, null);
        ArrayList<CategoriaModel> categorias = new ArrayList<>();
        while (cursor.moveToNext()) {
            CategoriaModel categoria = new CategoriaModel();
            categoria.setId(cursor.getInt(cursor.getColumnIndex("id")));
            categoria.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            categorias.add(categoria);
        }
        cursor.close();
        return categorias;
    }

    @Override
    public CategoriaModel retornarPorId(int id) {
        return retornarTodos().stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }
}
