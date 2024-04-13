package br.com.aula.listadecompras.presentation.fragments.categorias;

import br.com.aula.listadecompras.domain.models.CategoriaModel;

public interface OnCategoriaItemClickListener {
    void onItemClick(CategoriaModel categoria);

    void onDeleteClick(CategoriaModel categoria);
}
