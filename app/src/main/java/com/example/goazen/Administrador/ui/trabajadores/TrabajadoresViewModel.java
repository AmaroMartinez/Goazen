package com.example.goazen.Administrador.ui.trabajadores;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TrabajadoresViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TrabajadoresViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}