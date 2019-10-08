package com.example.goazen;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.WindowManager;

public class PopUpPagos extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Creamos el PopUp
        setContentView(R.layout.activity_pop_up_pagos);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        //Le damos medidas
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width/0.5), (int)(height/1.5));

        //Se las asignamos
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);

        //Trabajamos con las opciones de popUp

    }
}
