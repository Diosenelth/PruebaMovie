package com.example.pruebamovie;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pruebamovie.databinding.FragmentMoviesBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MoviesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MoviesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    FragmentMoviesBinding binding;
    RecyclerView recyclerView;
    private MoviesRes response;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MoviesFragment() {
        // Required empty public constructor
    }

    public MoviesFragment(MoviesRes response) {
        this.response = response;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MoviesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MoviesFragment newInstance(String param1, String param2) {
        MoviesFragment fragment = new MoviesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMoviesBinding.inflate(inflater, container, false);
        return binding.getRoot();
        //return inflater.inflate(R.layout.fragment_movies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView= binding.rv1;
        init();
    }

    public void init(){
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        AdaptadorMovies adaptadorMovies= new AdaptadorMovies(response.getResults(), getView());
        DividerItemDecoration decoration= new DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL);
        DividerItemDecoration decoration2= new DividerItemDecoration(requireContext(),DividerItemDecoration.HORIZONTAL);
        recyclerView.addItemDecoration(decoration);
        recyclerView.addItemDecoration(decoration2);
        recyclerView.setAdapter(adaptadorMovies);
    }
}