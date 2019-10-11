package com.example.goazen;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class PopUpPagos extends Activity {

    private CheckBox cb_pago_tarjeta;
    private CheckBox cb_pago_transferencia;

    private TextView tv_pago_tarjeta_n_tarjeta;
    private TextView tv_pago_tarjeta_cod_seguridad;
    private TextView tv_pago_transferencia;
    private TextView tv_pago_transferencia_numero_cuenta;

    private EditText et_numero_tarjeta;
    private EditText et_pago_tarjeta_cod_seguridad;

    private Button btn_pagar_contrato;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_pagos);


        //Inicialización de Variables
        cb_pago_tarjeta = findViewById(R.id.cb_pago_tarjeta);
        cb_pago_transferencia = findViewById(R.id.cb_pago_transferencia);

        tv_pago_tarjeta_n_tarjeta = findViewById(R.id.tv_numero_tarjeta);
        tv_pago_tarjeta_cod_seguridad = findViewById(R.id.tv_cod_seguridad_tarjeta);
        tv_pago_transferencia = findViewById(R.id.tv_pago_transderencia);
        tv_pago_transferencia_numero_cuenta = findViewById(R.id.tv_pago_trasferencia_numero_cuenta);

        et_numero_tarjeta = findViewById(R.id.et_numero_tarjeta);
        et_pago_tarjeta_cod_seguridad = findViewById(R.id.et_cod_seguridad_tarjeta);

        btn_pagar_contrato = findViewById(R.id.btn_pagar_contrato);

        // btn pagar invisible
        btn_pagar_contrato.setVisibility(View.INVISIBLE);

        //Creamos el PopUp
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        //Le damos medidas
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width), (int)(height/1.5));

        //Se las asignamos
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);

        /*Trabajamos con las opciones de popUp para que no se puuedan seleccionar
        * ambas a la vez y además te muestre los datos necesarios para poder
        * finalizar los pagos*/
        cb_pago_transferencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cb_pago_tarjeta.isChecked()){
                    cb_pago_tarjeta.setChecked(false);
                    tv_pago_tarjeta_n_tarjeta.setVisibility(View.INVISIBLE);
                    tv_pago_tarjeta_cod_seguridad.setVisibility(View.INVISIBLE);
                    et_numero_tarjeta.setVisibility(View.INVISIBLE);
                    et_pago_tarjeta_cod_seguridad.setVisibility(View.INVISIBLE);
                }

                tv_pago_transferencia.setVisibility(View.VISIBLE);
                tv_pago_transferencia_numero_cuenta.setVisibility(View.VISIBLE);
                btn_pagar_contrato.setVisibility(View.VISIBLE);

            }
        });

        cb_pago_tarjeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Comprobamso que la otra opción no este activa y de estarlo la deseleccionamos
                if(cb_pago_transferencia.isChecked()){
                    cb_pago_transferencia.setChecked(false);
                    tv_pago_transferencia.setVisibility(View.INVISIBLE);
                    tv_pago_transferencia_numero_cuenta.setVisibility(View.INVISIBLE);
                }

                //Hacemos visibles las partes que nos interesan
                tv_pago_tarjeta_n_tarjeta.setVisibility(View.VISIBLE);
                tv_pago_tarjeta_cod_seguridad.setVisibility(View.VISIBLE);
                et_numero_tarjeta.setVisibility(View.VISIBLE);
                et_pago_tarjeta_cod_seguridad.setVisibility(View.VISIBLE);
                btn_pagar_contrato.setVisibility(View.VISIBLE);
        }
        });

        btn_pagar_contrato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


}
