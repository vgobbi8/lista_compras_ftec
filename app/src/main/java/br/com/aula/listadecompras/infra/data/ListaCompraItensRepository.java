package br.com.aula.listadecompras.infra.data;


import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import br.com.aula.listadecompras.domain.models.CategoriaModel;
import br.com.aula.listadecompras.domain.models.ListaCompraItemModel;
import br.com.aula.listadecompras.domain.models.ListaComprasModel;

public class ListaCompraItensRepository extends BaseRepository implements IListaCompraItensRepository {

    public static final String TABLE_NAME = "COM002_ListasDeComprasItens";

    public ListaCompraItensRepository(SQLiteDatabase database) {
        super(database);
    }


    @Override
    public void inserir(ListaCompraItemModel listaCompraItensModel) {
        long id = database.insert(TABLE_NAME, null, listaCompraItensModel.toContentValues());
        listaCompraItensModel.setId((int) id);
    }

    @Override
    public void alterar(ListaCompraItemModel listaCompraItensModel) {
        database.update(TABLE_NAME, listaCompraItensModel.toContentValues(), "id = ?", new String[]{String.valueOf(listaCompraItensModel.getId())});
    }

    @Override
    public void excluirTodos(int idListaCompra) {
        database.delete(TABLE_NAME, "idListaCompra = ?", new String[]{String.valueOf(idListaCompra)});
    }

    @Override
    public void excluir(int id) {
        database.delete(TABLE_NAME, "id = ?", new String[]{String.valueOf(id)});
    }

    @SuppressLint("Range")
    @Override
    public ArrayList<ListaCompraItemModel> retornarTodos(int idListaCompra) {
        ArrayList<ListaCompraItemModel> listas = new ArrayList<>();
        ArrayList<CategoriaModel> categorias = new ArrayList<>();
        CategoriasRepository categoriasRepository = new CategoriasRepository(database);
        categorias = categoriasRepository.retornarTodos();
        Cursor cursor = database.query(TABLE_NAME, null, null, null, null, null, "nome");
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            int idListaCompra1 = cursor.getInt(cursor.getColumnIndex("idListaCompra"));
            String nome = cursor.getString(cursor.getColumnIndex("nome"));
            int quantidade = cursor.getInt(cursor.getColumnIndex("quantidade"));
            int comprado = cursor.getInt(cursor.getColumnIndex("comprado"));
            int idCategoria = cursor.getInt(cursor.getColumnIndex("idCategoria"));
            ListaCompraItemModel listaCompraItemModel = new ListaCompraItemModel(id, idListaCompra1, nome, quantidade, comprado, idCategoria);
            try {
                CategoriaModel categoria = categorias.stream().filter(c -> c.getId() == idCategoria).findFirst().get();
                listaCompraItemModel.setCategoria(categoria);
            } catch (Exception e) {
                CategoriaModel categoria = new CategoriaModel();
                categoria.setId(0);
                categoria.setNome("Sem categoria");
                listaCompraItemModel.setCategoria(categoria);
            }
            listas.add(listaCompraItemModel);
        }
        return listas;
    }

    @SuppressLint("Range")
    @Override
    public ListaCompraItemModel retornarPorId(int id) {
        ArrayList<ListaCompraItemModel> listas = new ArrayList<>();
        ArrayList<CategoriaModel> categorias = new ArrayList<>();
        CategoriasRepository categoriasRepository = new CategoriasRepository(database);
        categorias = categoriasRepository.retornarTodos();
        Cursor cursor = database.query(TABLE_NAME, null, null, null, null, null, "nome");
        while (cursor.moveToNext()) {
            int id1 = cursor.getInt(cursor.getColumnIndex("id"));
            int idListaCompra1 = cursor.getInt(cursor.getColumnIndex("idListaCompra"));
            String nome = cursor.getString(cursor.getColumnIndex("nome"));
            int quantidade = cursor.getInt(cursor.getColumnIndex("quantidade"));
            int comprado = cursor.getInt(cursor.getColumnIndex("comprado"));
            int idCategoria = cursor.getInt(cursor.getColumnIndex("idCategoria"));
            ListaCompraItemModel listaCompraItemModel = new ListaCompraItemModel(id1, idListaCompra1, nome, quantidade, comprado, idCategoria);
            try {
                CategoriaModel categoria = categorias.stream().filter(c -> c.getId() == idCategoria).findFirst().get();
                listaCompraItemModel.setCategoria(categoria);
            } catch (Exception e) {
                CategoriaModel categoria = new CategoriaModel();
                categoria.setId(0);
                categoria.setNome("Sem categoria");
                listaCompraItemModel.setCategoria(categoria);
            }
            listas.add(listaCompraItemModel);
        }
        try {
            return listas.stream().filter(c -> c.getId() == id).findFirst().get();
        } catch (Exception e) {
            return null;
        }
    }

    @SuppressLint("Range")
    @Override
    public ArrayList<ListaCompraItemModel> retornarPorCategoria(int idCategoria) {
        ArrayList<ListaCompraItemModel> listas = new ArrayList<>();
        ArrayList<CategoriaModel> categorias = new ArrayList<>();
        CategoriasRepository categoriasRepository = new CategoriasRepository(database);
        categorias = categoriasRepository.retornarTodos();
        Cursor cursor = database.query(TABLE_NAME, null, null, null, null, null, "nome");
        while (cursor.moveToNext()) {
            int id1 = cursor.getInt(cursor.getColumnIndex("id"));
            int idListaCompra1 = cursor.getInt(cursor.getColumnIndex("idListaCompra"));
            String nome = cursor.getString(cursor.getColumnIndex("nome"));
            int quantidade = cursor.getInt(cursor.getColumnIndex("quantidade"));
            int comprado = cursor.getInt(cursor.getColumnIndex("comprado"));
            int idCategoria1 = cursor.getInt(cursor.getColumnIndex("idCategoria"));
            ListaCompraItemModel listaCompraItemModel = new ListaCompraItemModel(id1, idListaCompra1, nome, quantidade, comprado, idCategoria1);
            try {
                CategoriaModel categoria = categorias.stream().filter(c -> c.getId() == idCategoria).findFirst().get();
                listaCompraItemModel.setCategoria(categoria);
            } catch (Exception e) {
                CategoriaModel categoria = new CategoriaModel();
                categoria.setId(0);
                categoria.setNome("Sem categoria");
                listaCompraItemModel.setCategoria(categoria);
            }
            listas.add(listaCompraItemModel);
        }
        return listas;
    }
}
