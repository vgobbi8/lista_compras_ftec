package br.com.aula.listadecompras.presentation.fragments.item_compra_cadastro;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import br.com.aula.listadecompras.MyApplication;
import br.com.aula.listadecompras.domain.models.CategoriaModel;
import br.com.aula.listadecompras.domain.models.ListaCompraItemModel;
import br.com.aula.listadecompras.infra.data.ICategoriasRepository;
import br.com.aula.listadecompras.infra.data.IListaCompraItensRepository;

public class ItemCompraCadastroViewModel extends ViewModel {
    public int id;
    public int idListaCompra;
    public String nome;
    public int quantidade;
    public int comprado;
    public int idCategoria;

    public CategoriaModel categoria;

    public String getQuantidadeString() {
        return String.valueOf(quantidade);
    }

    public void setQuantidadeString(String quantidade) {
        this.quantidade = Integer.parseInt(quantidade);
    }

    public boolean getIsComprado() {
        return comprado == 1;
    }

    public void setIsComprado(boolean comprado) {
        this.comprado = comprado ? 1 : 0;
    }

    public ArrayList<CategoriaModel> categorias = new ArrayList<>();


    private ICategoriasRepository categoriaRepository;

    private IListaCompraItensRepository listaCompraItensRepository;

    public ItemCompraCadastroViewModel() {
        MyApplication app = new MyApplication();
        categoriaRepository = app.diContainer.getCategoriasRepository();
        listaCompraItensRepository = app.diContainer.getListaCompraItensRepository();
    }

    public void carregarCategorias() {
        categorias = categoriaRepository.retornarTodos();
    }

    public ListaCompraItemModel getItem() {
        return new ListaCompraItemModel(id, idListaCompra, nome, quantidade, comprado, idCategoria);
    }

    public void salvarItemListaCompra() {
        listaCompraItensRepository.inserir(getItem());
    }

    public void alterarItemListaCompra() {
        listaCompraItensRepository.alterar(getItem());
    }


}