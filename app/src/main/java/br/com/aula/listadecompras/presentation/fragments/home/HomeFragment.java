package br.com.aula.listadecompras.presentation.fragments.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.aula.listadecompras.databinding.FragmentHomeBinding;
import br.com.aula.listadecompras.presentation.MainActivity;
import br.com.aula.listadecompras.presentation.fragments.categorias.ListagemCategoriasFragment;
import br.com.aula.listadecompras.presentation.fragments.lista_compras.ListaComprasFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_HOME_NAV_STACK = "HOME_NAV_STACK";
    private static final String ARG_ACTIVITY = "ACTIVITY";
    // TODO: Rename and change types of parameters
    private FragmentHomeBinding binding;

    public HomeFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
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


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        binding.categoriasButton.setOnClickListener(v -> {
            MainActivity activity = (MainActivity) getActivity();
            activity.pushFragment(ListagemCategoriasFragment.newInstance());
        });
        binding.comprasButton.setOnClickListener(v -> {
            MainActivity activity = (MainActivity) getActivity();
            activity.pushFragment(ListaComprasFragment.newInstance());
        });

        //configure();
        return binding.getRoot();
//        return inflater.inflate(R.layout.fragment_home, container, false);
    }

}