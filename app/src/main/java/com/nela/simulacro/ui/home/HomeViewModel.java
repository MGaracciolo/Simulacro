package com.nela.simulacro.ui.home;

import static java.lang.Integer.parseInt;

import android.app.Application;
import android.content.Intent;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends AndroidViewModel {

    private MutableLiveData<String> errorMutable;
    public HomeViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<String> getErrorMutable(){
        if(errorMutable==null){
            errorMutable = new MutableLiveData<String>();
        }
        return errorMutable;
    }

    public void hacerLlamada(String numero){
        if(numero == null || numero.trim().isEmpty() || parseInt(numero) < 99999){
            errorMutable.setValue("Error, ingrese un numero de telefono");
        }else {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + numero));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getApplication().startActivity(intent);
        }
    }
}