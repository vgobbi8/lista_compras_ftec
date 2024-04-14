package br.com.aula.listadecompras.presentation.fragments.categorias;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.recyclerview.widget.DividerItemDecoration;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.aula.listadecompras.MyApplication;
import br.com.aula.listadecompras.R;
import br.com.aula.listadecompras.databinding.FragmentListagemCategoriasBinding;
import br.com.aula.listadecompras.domain.models.CategoriaModel;
import br.com.aula.listadecompras.domain.models.ListaCompraItemModel;
import br.com.aula.listadecompras.infra.data.CategoriasRepository;
import br.com.aula.listadecompras.infra.data.ICategoriasRepository;
import br.com.aula.listadecompras.infra.data.IListaCompraItensRepository;
import br.com.aula.listadecompras.infra.data.SqliteHelper;
import br.com.aula.listadecompras.presentation.MainActivity;
import br.com.aula.listadecompras.presentation.fragments.categoria_cadastro.CategoriaCadastro;
import br.com.aula.listadecompras.presentation.fragments.home.HomeFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListagemCategoriasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListagemCategoriasFragment extends Fragment implements OnCategoriaItemClickListener {

    private FragmentListagemCategoriasBinding binding;

    public static ListagemCategoriasFragment newInstance() {
        ListagemCategoriasFragment fragment = new ListagemCategoriasFragment();
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
        binding = FragmentListagemCategoriasBinding.inflate(inflater, container, false);
        RecyclerView recyclerView = binding.fragmentContainerRecyclerCategorias;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(
                new DividerItemDecoration(
                        getContext(),
                        DividerItemDecoration.VERTICAL)
        );
        loadCategorias();
        binding.descPagina.setText("Categorias");
        binding.fabAddCategoria.setOnClickListener(view -> {
            MainActivity activity = (MainActivity) getActivity();
            activity.pushFragment(CategoriaCadastro.newInstance(null));
        });


        return binding.getRoot();
    }

    public void loadCategorias() {
        SqliteHelper helper = new SqliteHelper(getContext());
        SQLiteDatabase db = helper.getReadableDatabase();
        ICategoriasRepository categoriasRepository = new CategoriasRepository(db);
        ArrayList<CategoriaModel> lista = categoriasRepository.retornarTodos();
        binding.fragmentContainerRecyclerCategorias.setAdapter(new ListaCategoriasRecyclerViewAdapter(lista, this));
    }

    @Override
    public void onItemClick(CategoriaModel categoria) {
        MainActivity activity = (MainActivity) getActivity();
        activity.pushFragment(CategoriaCadastro.newInstance(categoria));
    }

    @Override
    public void onDeleteClick(CategoriaModel categoria) {
        MyApplication myApplication = (MyApplication) getActivity().getApplication();

        ICategoriasRepository categoriasRepository = myApplication.diContainer.getCategoriasRepository();
        IListaCompraItensRepository listaCompraItensRepository = myApplication.diContainer.getListaCompraItensRepository();

        ArrayList<ListaCompraItemModel> itens = listaCompraItensRepository.retornarPorCategoria(categoria.getId());
        if (!itens.isEmpty()) {
            Toast.makeText(myApplication, "Não é possível apagar esta categoria pois existem itens relacionados com ela!", Toast.LENGTH_LONG).show();
            return;
        }

        categoriasRepository.excluir(categoria.getId());
        loadCategorias();
    }
}