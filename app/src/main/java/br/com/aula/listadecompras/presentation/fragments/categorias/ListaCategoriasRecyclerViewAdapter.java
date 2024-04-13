package br.com.aula.listadecompras.presentation.fragments.categorias;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.aula.listadecompras.databinding.FragmentItemCategoriaBinding;
import br.com.aula.listadecompras.domain.models.CategoriaModel;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link br.com.aula.listadecompras.domain.models.CategoriaModel}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ListaCategoriasRecyclerViewAdapter extends RecyclerView.Adapter<ListaCategoriasRecyclerViewAdapter.ViewHolder> {

    private OnCategoriaItemClickListener listener;
    private final List<CategoriaModel> mValues;

    public ListaCategoriasRecyclerViewAdapter(List<CategoriaModel> items, OnCategoriaItemClickListener listener) {
        mValues = items;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentItemCategoriaBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false), listener);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(String.valueOf(mValues.get(position).getId()));
        holder.mContentView.setText(mValues.get(position).getNome());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mContentView;
        public CategoriaModel mItem;

        public ViewHolder(FragmentItemCategoriaBinding binding, final OnCategoriaItemClickListener listener) {
            super(binding.getRoot());
            mIdView = binding.itemNumber;
            mContentView = binding.content;

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

