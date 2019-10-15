package com.example.goazen.Administrador.ui.servicios;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AdminServiciosViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AdminServiciosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is send fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}