package br.com.aula.listadecompras.presentation.fragments.produtos_lista;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;


import br.com.aula.listadecompras.databinding.FragmentProdutoItemBinding;
import br.com.aula.listadecompras.domain.models.ListaCompraItemModel;

import java.util.List;

public class ListaComprasItensRecyclerViewAdapter extends RecyclerView.Adapter<ListaComprasItensRecyclerViewAdapter.ViewHolder> {

    private OnItemDaListaClickListener listener;
    private final List<ListaCompraItemModel> mValues;

    public ListaComprasItensRecyclerViewAdapter(List<ListaCompraItemModel> items, OnItemDaListaClickListener listener) {
        mValues = items;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentProdutoItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false), listener);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(String.valueOf(mValues.get(position).getId()));
        holder.mContentView.setText(mValues.get(position).getNome());
        holder.mObservacaoView.setText(mValues.get(position).getObservacao());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mContentView;

        public final TextView mObservacaoView;
        public ListaCompraItemModel mItem;

        public ViewHolder(FragmentProdutoItemBinding binding, final OnItemDaListaClickListener listener) {
            super(binding.getRoot());
            mIdView = binding.listaItemId;
            mContentView = binding.listaItemNome;
            mObservacaoView = binding.listaItemObs;
            // Configura o listener de clique no item
            binding.getRoot().setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(mItem);
                }
            });

            binding.deletarButton.setOnClickListener(v -> {
                listener.onDeleteClick(mItem);
            });
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}