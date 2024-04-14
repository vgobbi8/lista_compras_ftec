package br.com.aula.listadecompras.presentation.fragments.lista_compras;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import br.com.aula.listadecompras.MyApplication;
import br.com.aula.listadecompras.databinding.FragmentListaComprasBinding;
import br.com.aula.listadecompras.domain.models.CategoriaModel;
import br.com.aula.listadecompras.domain.models.ListaComprasModel;
import br.com.aula.listadecompras.infra.data.CategoriasRepository;
import br.com.aula.listadecompras.infra.data.ICategoriasRepository;
import br.com.aula.listadecompras.infra.data.IListaComprasRepository;
import br.com.aula.listadecompras.infra.data.SqliteHelper;
import br.com.aula.listadecompras.presentation.MainActivity;
import br.com.aula.listadecompras.presentation.fragments.categoria_cadastro.CategoriaCadastro;
import br.com.aula.listadecompras.presentation.fragments.lista_compras_cadastro.ListaComprasCadastro;
import br.com.aula.listadecompras.presentation.fragments.produtos_lista.ProdutosListaFragment;

/**
 * A fragment representing a list of Items.
 */
public class ListaComprasFragment extends Fragment implements OnListaCompraItemClickListener {

    FragmentListaComprasBinding binding;

    public ListaComprasFragment() {
    }


    @SuppressWarnings("unused")
    public static ListaComprasFragment newInstance() {
        ListaComprasFragment fragment = new ListaComprasFragment();
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
        binding = FragmentListaComprasBinding.inflate(inflater, container, false);
        RecyclerView recyclerView = binding.fragmentContainerRecyclerListaCompras;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(
                new DividerItemDecoration(
                        getContext(),
                        DividerItemDecoration.VERTICAL)
        );
        loadListasDeCompras();

        binding.descPagina.setText("Listas de Compras");
        binding.fabAddListaCompras.setOnClickListener(view -> {
            MainActivity activity = (MainActivity) getActivity();
            activity.pushFragment(ListaComprasCadastro.newInstance(null));
        });


        return binding.getRoot();
    }

    public void loadListasDeCompras() {
        MyApplication myApplication = (MyApplication) getActivity().getApplication();
        IListaComprasRepository listaComprasRepository = myApplication.diContainer.getListaComprasRepository();
        ArrayList<ListaComprasModel> lista = listaComprasRepository.retornarTodos();
        binding.fragmentContainerRecyclerListaCompras.setAdapter(new ListaComprasRecyclerViewAdapter(lista, this));
    }

    @Override
    public void onItemClick(ListaComprasModel lista) {
        MainActivity activity = (MainActivity) getActivity();
        activity.pushFragment(ListaComprasCadastro.newInstance(lista));
    }

    @Override
    public void onDeleteClick(ListaComprasModel lista) {
        MyApplication myApplication = (MyApplication) getActivity().getApplication();
        IListaComprasRepository categoriasRepository = myApplication.diContainer.getListaComprasRepository();
        categoriasRepository.excluir(lista.getId());
        loadListasDeCompras();
    }

    @Override
    public void onItensClick(ListaComprasModel lista) {
        MainActivity activity = (MainActivity) getActivity();
        activity.pushFragment(ProdutosListaFragment.newInstance(lista));
    }
}