package br.com.aula.listadecompras.infra.data;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import br.com.aula.listadecompras.domain.models.CategoriaModel;

interface ICategoriasRepository {
    void inserir(CategoriaModel categoriaModel);

    void alterar(CategoriaModel categoriaModel);

    void excluir(int id);

    ArrayList<CategoriaModel> retornarTodos();

    CategoriaModel retornarPorId(int id);
}

public class CategoriasRepository extends BaseRepository implements ICategoriasRepository {

    private static final String TABLE_NAME = "COM003_Categorias";

    public CategoriasRepository(SQLiteDatabase database) {
        super(database);
    }

    @Override
    public void inserir(CategoriaModel categoriaModel) {

    }

    @Override
    public void alterar(CategoriaModel categoriaModel) {

    }

    @Override
    public void excluir(int id) {

    }

    @Override
    public ArrayList<CategoriaModel> retornarTodos() {
        return null;
    }

    @Override
    public CategoriaModel retornarPorId(int id) {
        return null;
    }
}
