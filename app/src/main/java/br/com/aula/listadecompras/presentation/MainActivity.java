package br.com.aula.listadecompras.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import br.com.aula.listadecompras.R;
import br.com.aula.listadecompras.databinding.ActivityMainBinding;
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

}