package br.com.aula.listadecompras.infra.data;


import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import br.com.aula.listadecompras.domain.models.ListaCompraItemModel;

interface IListaCompraItensRepository {
    void inserir(ListaCompraItemModel listaCompraItensModel);

    void alterar(ListaCompraItemModel listaCompraItensModel);

    void excluir(int id);

    ArrayList<ListaCompraItemModel> retornarTodos();

    ListaCompraItemModel retornarPorId(int id);
}

public class ListaCompraItensRepository extends BaseRepository implements IListaCompraItensRepository {

    private static final String TABLE_NAME = "COM002_ListaCompraItens";

    public ListaCompraItensRepository(SQLiteDatabase database) {
        super(database);
    }


    @Override
    public void inserir(ListaCompraItemModel listaCompraItensModel) {

    }

    @Override
    public void alterar(ListaCompraItemModel listaCompraItensModel) {

    }

    @Override
    public void excluir(int id) {

    }

    @Override
    public ArrayList<ListaCompraItemModel> retornarTodos() {
        return null;
    }

    @Override
    public ListaCompraItemModel retornarPorId(int id) {
        return null;
    }
}
