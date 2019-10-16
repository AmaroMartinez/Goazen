package com.example.goazen.Trabajador.ui.calendario;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CalendarioTrabajadorViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CalendarioTrabajadorViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}