package com.example.goazen.Trabajador.ui.vacaciones;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class VacacionesTrabajadorViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public VacacionesTrabajadorViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is tools fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}