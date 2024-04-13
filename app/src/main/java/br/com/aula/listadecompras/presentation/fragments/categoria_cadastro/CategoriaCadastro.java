package br.com.aula.listadecompras.presentation.fragments.categoria_cadastro;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.aula.listadecompras.R;
import br.com.aula.listadecompras.databinding.FragmentCategoriaCadastroBinding;
import br.com.aula.listadecompras.databinding.FragmentListagemCategoriasBinding;
import br.com.aula.listadecompras.domain.models.CategoriaModel;
import br.com.aula.listadecompras.presentation.MainActivity;

public class CategoriaCadastro extends Fragment {

    private FragmentCategoriaCadastroBinding binding;

    private CategoriaCadastroViewModel mViewModel;

    private CategoriaModel categoriaEdicao;

    public static CategoriaCadastro newInstance(CategoriaModel categoriaModel) {
        Bundle args = new Bundle();
        CategoriaCadastro fragment = new CategoriaCadastro();
        if (categoriaModel != null) {
            args.putParcelable("categoria", categoriaModel);
            fragment.setArguments(args);
        }
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentCategoriaCadastroBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mViewModel = new ViewModelProvider(this).get(CategoriaCadastroViewModel.class);
        if (getArguments() != null) {
            categoriaEdicao = getArguments().getParcelable("categoria");
            if (categoriaEdicao != null) {
                mViewModel.id = categoriaEdicao.getId();
                mViewModel.nome.setValue(categoriaEdicao.getNome());
                binding.setCategoria(mViewModel);
            }
        }

        binding.buttonSalvarCategoria.setOnClickListener(v -> {
            mViewModel.nome.setValue(binding.editTextNomeCategoria.getText().toString());
            if (categoriaEdicao != null) {
                mViewModel.alterarCategoria();
            } else {
                mViewModel.salvarCategoria();
            }
            MainActivity activity = (MainActivity) getActivity();
            assert activity != null;
            activity.popFragment();
        });
    }

}