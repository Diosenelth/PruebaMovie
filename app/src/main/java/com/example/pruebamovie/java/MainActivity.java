package com.example.pruebamovie.java;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.pruebamovie.R;
import com.example.pruebamovie.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements IPresentador {
    ActivityMainBinding binding;
    int page = 1;
    MainPresentador mainPresentador;
    MoviesFragment moviesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //setContentView(R.layout.activity_main);
        cargar(page);
    }

    public void cargar(int page) {
        this.page = page;
        binding.lottie.setVisibility(View.VISIBLE);
        binding.lottie.setAnimation(R.raw.laa);
        binding.lottie.playAnimation();
        binding.fragmentContainerView.setVisibility(View.INVISIBLE);
        moviesFragment=null;
        mainPresentador = new MainPresentador(this, page);
        mainPresentador.cargar();
    }

    public void mostrarAlerta(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    public void cargarMovies(MoviesRes moviesRes) {
        moviesFragment = new MoviesFragment(moviesRes, page, this);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainerView, moviesFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
        binding.lottie.setVisibility(View.INVISIBLE);
        binding.fragmentContainerView.setVisibility(View.VISIBLE);
    }

    public void error() {
        binding.lottie.setAnimation(R.raw.error);
        binding.lottie.playAnimation();
    }
}