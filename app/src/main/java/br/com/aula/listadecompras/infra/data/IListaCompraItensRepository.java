package br.com.aula.listadecompras.infra.data;

import java.util.ArrayList;

import br.com.aula.listadecompras.domain.models.ListaCompraItemModel;

public interface IListaCompraItensRepository {
    void inserir(ListaCompraItemModel listaCompraItensModel);

    void alterar(ListaCompraItemModel listaCompraItensModel);

    void excluirTodos(int id);
    void excluir(int id);

    ArrayList<ListaCompraItemModel> retornarTodos(int idListaCompra);

    ListaCompraItemModel retornarPorId(int id);

    ArrayList<ListaCompraItemModel> retornarPorCategoria(int idCategoria);
}
