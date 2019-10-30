package com.example.goazen.Cliente;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.goazen.Cliente.ui.servicios.Calendario;
import com.example.goazen.DatosUsuario;
import com.example.goazen.R;
import com.example.goazen.Values;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
    private FirebaseFirestore db;

    private Date curretDate;
    private SimpleDateFormat formatter;


    private String DescServicio="";
    private double PrecioServicio=0;
    private double precioValor=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_pagos);

        db = FirebaseFirestore.getInstance();

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

                // arraylist de la clase Calendario
                for(int i=0;i<Calendario.getArrlEvento().size();i++){

                    //Condiciones para aplicar el precio dependiendo del servicio
                    if (Calendario.getArrlEvento().get(i).getTitulo().equals("Limpieza general") ){
                        precioValor=13;
                    }
                    if (Calendario.getArrlEvento().get(i).getTitulo().equals("Limpieza de cristales") ){
                        precioValor=8;
                    }
                    if (Calendario.getArrlEvento().get(i).getTitulo().equals("Cocina") ){
                        precioValor=11;
                    }
                    if (Calendario.getArrlEvento().get(i).getTitulo().equals("Lavanderia") ){
                        precioValor=6.50;
                    }
                    if (Calendario.getArrlEvento().get(i).getTitulo().equals("Plancha") ){
                        precioValor=5;
                    }
                    if (Calendario.getArrlEvento().get(i).getTitulo().equals("Regado de plantas") ){
                        precioValor=4;
                    }
                    if (Calendario.getArrlEvento().get(i).getTitulo().equals("Paseo de mascotas") ){
                        precioValor=6;
                    }

                    //Segun avanza en el arraylist, se van añadiendo las descripciones y se hace el calculo del precio total
                    DescServicio=DescServicio+" "+Calendario.getArrlEvento().get(i).getTitulo();
                    PrecioServicio= PrecioServicio+precioValor;

                    //System.out.println(DescServicio);
                    //System.out.println(PrecioServicio);
                }

                //Llamamos al metodo para crear el recibo en la base de datos
                CrearRecibo();

                finish();
            }
        });

    }


    private void CrearRecibo(){

        formatter= new SimpleDateFormat("dd-MM-yyyy HH:mm:ss z");
        curretDate = new Date(System.currentTimeMillis());
        //System.out.println(formatter.format(date));

        CollectionReference Recibos = db.collection("Recibos");

        Map<String, Object> datos = new HashMap<>();
        datos.put("Nombre", DescServicio);
        datos.put("Precio", PrecioServicio+"€");
        datos.put("Cliente", DatosUsuario.getDNI());
        //Le damos la fecha actual al documento que se crea en la base de datos
        Recibos.document(formatter.format(curretDate)).set(datos);
        
    }


}
