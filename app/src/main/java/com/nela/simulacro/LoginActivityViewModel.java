package com.nela.simulacro;

import static java.lang.Integer.parseInt;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class LoginActivityViewModel extends AndroidViewModel {

    private MutableLiveData<String> mutableError;

    public LoginActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<String> getMutableError(){
        if(mutableError==null){
            mutableError = new MutableLiveData<String>();
        }
        return mutableError;
    }
    public void corroborarAcceso(String u, int c){
        if(u == null || c==0){
            mutableError.setValue("Ingrese usuario y contraseña");
        }else {

            if (u.equalsIgnoreCase("Nela")) {
                if (c == 1234) {
                    Intent intent = new Intent(getApplication(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplication().startActivity(intent);
                } else {
                    mutableError.setValue("Clave incorrecta");
                }
            } else {
                mutableError.setValue("Usuario incorrecto");
            }
        }
    }
}
