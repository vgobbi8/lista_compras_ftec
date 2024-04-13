package br.com.aula.listadecompras.presentation;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Stack;

import br.com.aula.listadecompras.R;
import br.com.aula.listadecompras.databinding.ActivityMainBinding;
import br.com.aula.listadecompras.presentation.fragments.categorias.ListagemCategoriasFragment;
import br.com.aula.listadecompras.presentation.fragments.home.HomeFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private Stack<Fragment> homeNavigationStack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_main);
        homeNavigationStack = new Stack<>();
        configure();
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.fragment_container, HomeFragment.class, null)
                .commit();

        // This callback is only called when MyFragment is at least started
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
                if (binding.bottomNavigation.getSelectedItemId() != R.id.item_nav_home) {
                    binding.bottomNavigation.setSelectedItemId(R.id.item_nav_home);
                    return;
                }
                if (!homeNavigationStack.isEmpty()) {
                    popFragment();
                } else {
                    finish();
                }
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);
    }

    @SuppressLint("NonConstantResourceId")
    private void configure() {
        binding.bottomNavigation.setOnItemSelectedListener(item -> {
//            switch (item.getItemId()) {
//                case R.id.item_nav_home:
//                    binding.textView.setText("Home");
//                    break;
//                case R.id.item_nav_about:
//                    binding.textView.setText("Dashboard");
//                    break;
//            }
            return true;
        });
    }


    //Custom back button handling


    public void pushFragment(Fragment fragment) {
        homeNavigationStack.push(fragment);
        setBreadcrumb(fragment);
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container, fragment, null)
                .commit();
    }

    public void popFragment() {
        homeNavigationStack.pop();
        if (homeNavigationStack.isEmpty()) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragment_container, HomeFragment.class, null)
                    .commit();
            return;
        }
        Fragment fragment = homeNavigationStack.peek();
        setBreadcrumb(fragment);
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container, fragment, null)
                .commit();
    }

    private void setBreadcrumb(Fragment fragment) {
        if (fragment instanceof HomeFragment) {
            binding.breadcrumbTextView.setText("Home");
        } else if (fragment instanceof ListagemCategoriasFragment) {
            binding.breadcrumbTextView.setText("Categorias");
        }
    }

}