package br.com.aula.listadecompras.presentation.fragments.lista_compras;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.aula.listadecompras.databinding.FragmentItemCategoriaBinding;
import br.com.aula.listadecompras.databinding.FragmentListaComprasBinding;
import br.com.aula.listadecompras.domain.models.ListaComprasModel;

import br.com.aula.listadecompras.databinding.FragmentListaCompraItemBinding;

import java.util.List;

public class ListaComprasRecyclerViewAdapter extends RecyclerView.Adapter<ListaComprasRecyclerViewAdapter.ViewHolder> {

    private OnListaCompraItemClickListener listener;
    private final List<ListaComprasModel> mValues;

    public ListaComprasRecyclerViewAdapter(List<ListaComprasModel> items, OnListaCompraItemClickListener listener) {
        mValues = items;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentListaCompraItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false), listener);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
       // holder.mIdView.setText(String.valueOf(mValues.get(position).getId()));
        holder.mContentView.setText(mValues.get(position).getNome());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
      //  public final TextView mIdView;
        public final TextView mContentView;
        public ListaComprasModel mItem;

        public ViewHolder(FragmentListaCompraItemBinding binding, final OnListaCompraItemClickListener listener) {
            super(binding.getRoot());
          //  mIdView = binding.listaId;
            mContentView = binding.listaNome;

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
            binding.itensButton.setOnClickListener(v -> {
                listener.onItensClick(mItem);
            });
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}