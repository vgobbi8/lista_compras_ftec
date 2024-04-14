package br.com.aula.listadecompras.infra.data;

import java.util.ArrayList;

import br.com.aula.listadecompras.domain.models.ListaComprasModel;

public interface IListaComprasRepository {
    void inserir(ListaComprasModel listaComprasModel);

    void alterar(ListaComprasModel listaComprasModel);

    void excluir(int id);

    ArrayList<ListaComprasModel> retornarTodos();

    ListaComprasModel retornarPorId(int id);
}
