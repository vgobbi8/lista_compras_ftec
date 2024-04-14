package br.com.aula.listadecompras.presentation.fragments.item_compra_cadastro;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.com.aula.listadecompras.R;
import br.com.aula.listadecompras.databinding.FragmentCategoriaCadastroBinding;
import br.com.aula.listadecompras.databinding.FragmentItemCompraCadastroBinding;
import br.com.aula.listadecompras.domain.models.CategoriaModel;
import br.com.aula.listadecompras.domain.models.ListaCompraItemModel;
import br.com.aula.listadecompras.domain.models.ListaComprasModel;
import br.com.aula.listadecompras.presentation.MainActivity;
import br.com.aula.listadecompras.presentation.fragments.categoria_cadastro.CategoriaCadastro;
import br.com.aula.listadecompras.presentation.fragments.categoria_cadastro.CategoriaCadastroViewModel;
import br.com.aula.listadecompras.presentation.fragments.lista_compras_cadastro.ListaComprasCadastro;
import br.com.aula.listadecompras.presentation.fragments.lista_compras_cadastro.ListaComprasCadastroViewModel;

public class ItemCompraCadastroFragment extends Fragment {


    private ItemCompraCadastroViewModel mViewModel;
    private FragmentItemCompraCadastroBinding binding;

    private ListaCompraItemModel itemEdicao;

    private ArrayAdapter<CategoriaModel> spinnerAdapter;

    private ListaComprasModel listaCompra;

    public void setListaCompra(ListaComprasModel listaCompra) {
        this.listaCompra = listaCompra;
    }

    public static ItemCompraCadastroFragment newInstance(ListaCompraItemModel item, ListaComprasModel listaCompra) {
        Bundle args = new Bundle();
        ItemCompraCadastroFragment fragment = new ItemCompraCadastroFragment();
        fragment.setListaCompra(listaCompra);
        if (item != null) {
            args.putParcelable("item", item);
            fragment.setArguments(args);
        }
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentItemCompraCadastroBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mViewModel = new ViewModelProvider(this).get(ItemCompraCadastroViewModel.class);
        mViewModel.carregarCategorias();

        CategoriaAdapter adapter = new CategoriaAdapter(getContext(), mViewModel.categorias);
        binding.spinnerCategoria.setAdapter(adapter);


        if (getArguments() != null) {
            itemEdicao = getArguments().getParcelable("item");
            if (itemEdicao != null) {
                mViewModel.id = itemEdicao.getId();
                mViewModel.idCategoria = itemEdicao.getIdCategoria();
                mViewModel.idListaCompra = itemEdicao.getIdListaCompra();
                mViewModel.quantidade = itemEdicao.getQuantidade();
                mViewModel.comprado = itemEdicao.getComprado();
                mViewModel.nome = itemEdicao.getNome();

                binding.setViewModel(mViewModel);
                binding.spinnerCategoria.setSelection(mViewModel.categorias.indexOf(mViewModel.categorias.stream().filter(c -> c.getId() == mViewModel.idCategoria).findFirst().get()));
            }
        }
        binding.descPagina.setText((itemEdicao != null ? "Edição " : "Inclusão ") + "Item da lista " + listaCompra.getNome());
        mViewModel.idListaCompra = listaCompra.getId();

        binding.buttonSalvarLista.setOnClickListener(v -> {
            try {
                mViewModel.quantidade = Integer.parseInt(binding.editTextQuantidade.getText().toString());
                mViewModel.comprado = binding.checkboxComprado.isChecked() ? 1 : 0;
                mViewModel.nome = binding.editTextNome.getText().toString();
                mViewModel.idCategoria = mViewModel.categorias.get(binding.spinnerCategoria.getSelectedItemPosition()).getId();
                if (itemEdicao != null) {
                    mViewModel.alterarItemListaCompra();
                } else {
                    mViewModel.salvarItemListaCompra();
                }
                MainActivity activity = (MainActivity) getActivity();
                assert activity != null;
                activity.popFragment();
            } catch (Exception e) {
                Toast.makeText(getActivity(), "Falha ao gravar!", Toast.LENGTH_SHORT).show();
            }

        });
    }


}

class CategoriaAdapter extends ArrayAdapter<CategoriaModel> {
    public CategoriaAdapter(Context context, List<CategoriaModel> categorias) {
        super(context, 0, categorias);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_spinner_item, parent, false);
        }

        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(getItem(position).getNome());

        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        }

        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(getItem(position).getNome());

        return convertView;
    }
}