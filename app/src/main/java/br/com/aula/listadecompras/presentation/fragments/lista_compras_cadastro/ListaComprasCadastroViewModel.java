package br.com.aula.listadecompras.presentation.fragments.lista_compras_cadastro;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import br.com.aula.listadecompras.MyApplication;
import br.com.aula.listadecompras.domain.models.ListaCompraItemModel;
import br.com.aula.listadecompras.domain.models.ListaComprasModel;
import br.com.aula.listadecompras.infra.data.IListaCompraItensRepository;
import br.com.aula.listadecompras.infra.data.IListaComprasRepository;

public class ListaComprasCadastroViewModel extends ViewModel {

    public int id;
    public String nome;
    ArrayList<ListaCompraItemModel> listaCompraItens = new ArrayList<>();

    IListaComprasRepository listaComprasRepository;

    public ListaComprasCadastroViewModel() {
        MyApplication app = new MyApplication();
        listaComprasRepository = app.diContainer.getListaComprasRepository();
    }


    public void salvarListaCompras() {
        ListaComprasModel model = new ListaComprasModel(id, nome);
        listaComprasRepository.inserir(model);
    }

    public void alterarListaCompras() {
        listaComprasRepository.alterar(new ListaComprasModel(id, nome));
    }



}