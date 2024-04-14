package br.com.aula.listadecompras.presentation.fragments.produtos_lista;

import br.com.aula.listadecompras.domain.models.ListaCompraItemModel;
import br.com.aula.listadecompras.domain.models.ListaComprasModel;

public interface OnItemDaListaClickListener {
    void onItemClick(ListaCompraItemModel item);

    void onDeleteClick(ListaCompraItemModel item);
}
