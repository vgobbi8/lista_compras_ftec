package br.com.aula.listadecompras.presentation.fragments.produtos_lista;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import br.com.aula.listadecompras.MyApplication;
import br.com.aula.listadecompras.R;
import br.com.aula.listadecompras.databinding.FragmentListaComprasBinding;
import br.com.aula.listadecompras.databinding.FragmentProdutosListBinding;
import br.com.aula.listadecompras.domain.models.ListaCompraItemModel;
import br.com.aula.listadecompras.domain.models.ListaComprasModel;
import br.com.aula.listadecompras.infra.data.IListaCompraItensRepository;
import br.com.aula.listadecompras.infra.data.IListaComprasRepository;
import br.com.aula.listadecompras.presentation.MainActivity;
import br.com.aula.listadecompras.presentation.fragments.item_compra_cadastro.ItemCompraCadastroFragment;

/**
 * A fragment representing a list of Items.
 */
public class ProdutosListaFragment extends Fragment implements OnItemDaListaClickListener {

    FragmentProdutosListBinding binding;

    private ListaComprasModel listaComprasModel;

    public void setListaComprasModel(ListaComprasModel listaComprasModel) {
        this.listaComprasModel = listaComprasModel;
    }

    public ProdutosListaFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ProdutosListaFragment newInstance(ListaComprasModel listaComprasModel) {
        ProdutosListaFragment fragment = new ProdutosListaFragment();
        fragment.setListaComprasModel(listaComprasModel);
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProdutosListBinding.inflate(inflater, container, false);
        binding.descPagina.setText("Produtos da lista " + listaComprasModel.getNome());
        RecyclerView recyclerView = binding.listaProdutos;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(
                new DividerItemDecoration(
                        getContext(),
                        DividerItemDecoration.VERTICAL)
        );
        loadItens();

        binding.fabAddItem.setOnClickListener(view -> {
            MainActivity activity = (MainActivity) getActivity();
            activity.pushFragment(ItemCompraCadastroFragment.newInstance(null, listaComprasModel));
        });


        return binding.getRoot();
    }

    @Override
    public void onItemClick(ListaCompraItemModel item) {
        MainActivity activity = (MainActivity) getActivity();
        activity.pushFragment(ItemCompraCadastroFragment.newInstance(item, listaComprasModel));
    }

    @Override
    public void onDeleteClick(ListaCompraItemModel item) {
        MyApplication myApplication = (MyApplication) getActivity().getApplication();
        IListaCompraItensRepository listaCompraItensRepo = myApplication.diContainer.getListaCompraItensRepository();
        listaCompraItensRepo.excluir(item.getId());
        loadItens();
    }

    public void loadItens() {
        MyApplication myApplication = (MyApplication) getActivity().getApplication();
        IListaCompraItensRepository listaComprasRepository = myApplication.diContainer.getListaCompraItensRepository();
        ArrayList<ListaCompraItemModel> lista = listaComprasRepository.retornarTodos(listaComprasModel.getId());
        binding.listaProdutos.setAdapter(new ListaComprasItensRecyclerViewAdapter(lista, this));
    }

}