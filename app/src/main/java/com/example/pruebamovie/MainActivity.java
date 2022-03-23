package com.example.pruebamovie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Toast;

import com.example.pruebamovie.databinding.ActivityMainBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //setContentView(R.layout.activity_main);

        MovieService movieService = new MovieService();
        movieService.getRutas().getPopularMovies(1).enqueue(new Callback<MoviesRes>() {
            @Override
            public void onResponse(Call<MoviesRes> call, Response<MoviesRes> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Correcto", Toast.LENGTH_SHORT).show();
                    MoviesFragment moviesFragment= new MoviesFragment(response.body());
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragmentContainerView,moviesFragment)
                            .addToBackStack("principal")
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit();

                }
            }

            @Override
            public void onFailure(Call<MoviesRes> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}