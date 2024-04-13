package br.com.aula.listadecompras.infra.data;

import java.util.ArrayList;

import br.com.aula.listadecompras.domain.models.CategoriaModel;

public interface ICategoriasRepository {
    void inserir(CategoriaModel categoriaModel);

    void alterar(CategoriaModel categoriaModel);

    void excluir(int id);

    ArrayList<CategoriaModel> retornarTodos();

    CategoriaModel retornarPorId(int id);
}
