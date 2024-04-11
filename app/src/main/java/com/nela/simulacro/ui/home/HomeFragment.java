package com.nela.simulacro.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.nela.simulacro.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.btLlamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                String numero = binding.etNumero.getText().toString();
                homeViewModel.hacerLlamada(numero);
            }
        });
        homeViewModel.getErrorMutable().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.tvErrorNumero.setText(homeViewModel.getErrorMutable().getValue().toString());
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}