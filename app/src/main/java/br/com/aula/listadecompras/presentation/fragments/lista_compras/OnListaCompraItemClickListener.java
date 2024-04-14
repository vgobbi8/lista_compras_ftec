package br.com.aula.listadecompras.presentation.fragments.lista_compras;
import br.com.aula.listadecompras.domain.models.ListaComprasModel;

public interface OnListaCompraItemClickListener {
    void onItemClick(ListaComprasModel categoria);

    void onDeleteClick(ListaComprasModel categoria);

    void onItensClick(ListaComprasModel categoria);

}
