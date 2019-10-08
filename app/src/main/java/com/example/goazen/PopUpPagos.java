package com.example.goazen;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class PopUpPagos extends Activity {

    private RadioButton rb_pago_tarjeta;
    private RadioButton rb_pago_transferencia;

    private TextView tv_pago_tarjeta_n_tarjeta;
    private TextView tv_pago_tarjeta_cod_seguridad;
    private TextView tv_pago_transferencia;
    private TextView tv_pago_transferencia_numero_cuenta;

    private EditText et_numero_tarjeta;
    private EditText et_pago_tarjeta_cod_seguridad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Inicialización de Variables
        rb_pago_tarjeta = findViewById(R.id.rb_pago_tarjeta);
        rb_pago_transferencia = findViewById(R.id.rb_pago_transferencia);

        tv_pago_tarjeta_n_tarjeta = findViewById(R.id.tv_numero_tarjeta);
        tv_pago_tarjeta_cod_seguridad = findViewById(R.id.tv_cod_seguridad_tarjeta);
        tv_pago_transferencia = findViewById(R.id.tv_pago_transderencia);
        tv_pago_transferencia_numero_cuenta = findViewById(R.id.tv_pago_trasferencia_numero_cuenta);

        et_numero_tarjeta = findViewById(R.id.et_numero_tarjeta);
        et_pago_tarjeta_cod_seguridad = findViewById(R.id.et_cod_seguridad_tarjeta);


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
        rb_pago_tarjeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Comprobamso que la otra opción no este activa y de estarlo la deseleccionamos
                if(rb_pago_transferencia.isChecked()){
                    rb_pago_transferencia.setSelected(false);
                    tv_pago_transferencia.setVisibility(View.INVISIBLE);
                    tv_pago_transferencia_numero_cuenta.setVisibility(View.INVISIBLE);
                }

                //Hacemos visibles las partes que nos interesan
                tv_pago_tarjeta_n_tarjeta.setVisibility(View.VISIBLE);
                tv_pago_tarjeta_cod_seguridad.setVisibility(View.VISIBLE);
                et_numero_tarjeta.setVisibility(View.VISIBLE);
                et_pago_tarjeta_cod_seguridad.setVisibility(View.VISIBLE);
            }
        });

        /*cb_pago_transferencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cb_pago_tarjeta.isSelected()){
                    cb_pago_tarjeta.setSelected(false);
                    tv_pago_tarjeta_n_tarjeta.setVisibility(View.INVISIBLE);
                    tv_pago_tarjeta_cod_seguridad.setVisibility(View.INVISIBLE);
                    et_numero_tarjeta.setVisibility(View.INVISIBLE);
                    et_pago_tarjeta_cod_seguridad.setVisibility(View.INVISIBLE);
                }

                tv_pago_transferencia.setVisibility(View.VISIBLE);
                tv_pago_transferencia_numero_cuenta.setVisibility(View.VISIBLE);
            }
        });*/

         /*cb_pago_tarjeta.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(cb_pago_tarjeta.isChecked()){
                    //Comprobamso que la otra opción no este activa y de estarlo la deseleccionamos
                    if(cb_pago_transferencia.isSelected()){
                        cb_pago_transferencia.setSelected(false);
                        tv_pago_transferencia.setVisibility(View.INVISIBLE);
                        tv_pago_transferencia_numero_cuenta.setVisibility(View.INVISIBLE);
                    }

                    //Hacemos visibles las partes que nos interesan
                    tv_pago_tarjeta_n_tarjeta.setVisibility(View.VISIBLE);
                    tv_pago_tarjeta_cod_seguridad.setVisibility(View.VISIBLE);
                    et_numero_tarjeta.setVisibility(View.VISIBLE);
                    et_pago_tarjeta_cod_seguridad.setVisibility(View.VISIBLE);
                }
                else{
                    if(cb_pago_tarjeta.isSelected()){
                        cb_pago_tarjeta.setSelected(false);
                        tv_pago_tarjeta_n_tarjeta.setVisibility(View.INVISIBLE);
                        tv_pago_tarjeta_cod_seguridad.setVisibility(View.INVISIBLE);
                        et_numero_tarjeta.setVisibility(View.INVISIBLE);
                        et_pago_tarjeta_cod_seguridad.setVisibility(View.INVISIBLE);
                    }

                    tv_pago_transferencia.setVisibility(View.VISIBLE);
                    tv_pago_transferencia_numero_cuenta.setVisibility(View.VISIBLE);
                }
            }
        });*/

    }


}
