package br.com.aula.listadecompras.presentation.fragments.categorias;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.aula.listadecompras.R;

public class ListaCategoriasFragment extends Fragment {

    private ListaCategoriasViewModel mViewModel;

    public static ListaCategoriasFragment newInstance() {
        return new ListaCategoriasFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lista_categorias, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ListaCategoriasViewModel.class);
        // TODO: Use the ViewModel
    }

}