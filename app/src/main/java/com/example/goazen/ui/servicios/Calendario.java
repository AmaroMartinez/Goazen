package com.example.goazen.ui.servicios;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.goazen.R;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class Calendario extends AppCompatActivity {

    /*Declaramos las variables necesarias para el calendario*/
    CompactCalendarView calendario;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("dd - mm - yyyy", Locale.getDefault());

    //Declaramos las variables necesarias para el guardado de datos.
    private int dia;

    //Declaramos los objetos necesarios
    private TextView tv_calendario;
    private RadioGroup rg_calendario;
    private LinearLayout ll_contenedor_botones;
    private Button btn_aceptar;
    private Button btn_Cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        tv_calendario = findViewById(R.id.tv_calendario);
        rg_calendario = findViewById(R.id.rg_calendario);
        ll_contenedor_botones = findViewById(R.id.ll_horizontal);
        btn_aceptar = findViewById(R.id.btn_asignar);
        btn_Cancelar = findViewById(R.id.btn_cancelar);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setTitle(null);

        //Inicializamos el calendario
        calendario = findViewById(R.id.compactcalendar_view);

        calendario.setUseThreeLetterAbbreviation(true);


        //Añadimos un listener para las acciones.
        calendario.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {

                //Guaradamos el día
                dia = dateClicked.getDay();

                //Hacemos visibles las horas disponibles
                tv_calendario.setVisibility(View.VISIBLE);
                rg_calendario.setVisibility(View.VISIBLE);

            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                actionBar.setTitle(dateFormatMonth.format(firstDayOfNewMonth));
            }
        });

        rg_calendario.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                ll_contenedor_botones.setVisibility(View.VISIBLE);
            }
        });

        btn_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });

    }
}
