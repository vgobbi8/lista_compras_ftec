package br.com.aula.listadecompras.presentation.fragments.lista_compras_cadastro;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.aula.listadecompras.databinding.FragmentListaComprasCadastroBinding;
import br.com.aula.listadecompras.domain.models.ListaCompraItemModel;
import br.com.aula.listadecompras.domain.models.ListaComprasModel;
import br.com.aula.listadecompras.presentation.MainActivity;
import br.com.aula.listadecompras.presentation.fragments.item_compra_cadastro.ItemCompraCadastroFragment;
import br.com.aula.listadecompras.presentation.fragments.produtos_lista.ListaComprasItensRecyclerViewAdapter;
import br.com.aula.listadecompras.presentation.fragments.produtos_lista.OnItemDaListaClickListener;

public class ListaComprasCadastro extends Fragment {

    private ListaComprasCadastroViewModel mViewModel;

    private FragmentListaComprasCadastroBinding binding;
    private ListaComprasModel listaEdicao;

    public static ListaComprasCadastro newInstance(ListaComprasModel listaComprasModel) {
        Bundle args = new Bundle();
        ListaComprasCadastro fragment = new ListaComprasCadastro();
        if (listaComprasModel != null) {
            args.putParcelable("lista_compra", listaComprasModel);
            fragment.setArguments(args);
        }
        return fragment;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentListaComprasCadastroBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mViewModel = new ViewModelProvider(this).get(ListaComprasCadastroViewModel.class);
        if (getArguments() != null) {
            listaEdicao = getArguments().getParcelable("lista_compra");
            if (listaEdicao != null) {
                mViewModel.id = listaEdicao.getId();
                mViewModel.nome = listaEdicao.getNome();

                binding.editTextNomeLista.setText(mViewModel.nome);
            }
        }
        binding.descPagina.setText(listaEdicao != null ? "Edição " : "Inclusão " + "Lista de Compras");
        binding.buttonSalvarLista.setOnClickListener(v -> {
            mViewModel.nome = (binding.editTextNomeLista.getText().toString());
            if (listaEdicao != null) {
                mViewModel.alterarListaCompras();
            } else {
                mViewModel.salvarListaCompras();
            }
            MainActivity activity = (MainActivity) getActivity();
            assert activity != null;
            activity.popFragment();
        });


    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // Salvar o estado da lista de itens
    }

//    public void recarregarListaItens() {
//        RecyclerView recyclerView = binding.fragmentContainerRecyclerListaItensCompra;
//
//        ListaComprasItensRecyclerViewAdapter adapter = new ListaComprasItensRecyclerViewAdapter(mViewModel.listaCompraItens, this);
//        recyclerView.setAdapter(adapter);
//        recyclerView.addOnLayoutChangeListener((v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom) -> {
//            recyclerView.post(new Runnable() {
//                @Override
//                public void run() {
//                    recyclerView.scrollToPosition(adapter.getItemCount() - 1);
//                }
//            });
//        });
//    }


}