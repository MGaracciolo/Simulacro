package com.nela.simulacro;

import static android.Manifest.permission.CALL_PHONE;

import static java.lang.Integer.parseInt;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.nela.simulacro.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    private Conectado conectado;
    private ActivityLoginBinding binding;
    private LoginActivityViewModel mv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        super.onCreate(savedInstanceState);
        solicitarPermisos();
        registrarBroadcast();
        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(LoginActivityViewModel.class);

       Button bt = binding.btIngresar;
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mv.corroborarAcceso(binding.etUsuario.getText().toString(), parseInt(binding.etClave.getText().toString()));
            }
        });
        mv.getMutableError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.tvError.setText(mv.getMutableError().getValue());
            }
        });
    }
    private void solicitarPermisos(){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M
                && checkSelfPermission(CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{CALL_PHONE},1000);
        }
    }
    private void registrarBroadcast(){
        this.conectado=new Conectado();
        registerReceiver(conectado,new IntentFilter("android.net.wifi.supplicant.CONNECTION_CHANGE"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}