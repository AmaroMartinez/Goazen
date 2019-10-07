package com.example.goazen.ui.servicios;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.EventLog;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.goazen.R;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.example.goazen.R.string.st_limpieza_general;


public class Calendario extends AppCompatActivity {

    /*Declaramos las variables necesarias para el calendario*/
    CompactCalendarView calendario;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("dd - mm - yyyy", Locale.getDefault());

    //Declaramos las variables necesarias para el guardado de datos.
    private long dia;

    //Declaramos los objetos necesarios
    private TextView tv_calendario;
    private RadioGroup rg_calendario;
    private RadioButton rb_primera_hora;
    private RadioButton rb_segunda_hora;
    private RadioButton rb_tercera_hora;
    private LinearLayout ll_contenedor_botones;
    private Button btn_asignar;
    private Button btn_Atras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        tv_calendario = findViewById(R.id.tv_calendario);
        rg_calendario = findViewById(R.id.rg_calendario);
        rb_primera_hora = findViewById(R.id.rb_Hora_10_00);
        rb_segunda_hora = findViewById(R.id.rb_Hora_11_00);
        rb_tercera_hora = findViewById(R.id.rb_Hora_12_00);
        ll_contenedor_botones = findViewById(R.id.ll_horizontal);
        btn_asignar = findViewById(R.id.btn_asignar);
        btn_Atras = findViewById(R.id.btn_atras);

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
                dia = dateClicked.getTime();

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

        rb_primera_hora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_asignar.setVisibility(View.VISIBLE);
            }
        });

        rb_segunda_hora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_asignar.setVisibility(View.VISIBLE);
            }
        });

        rb_tercera_hora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_asignar.setVisibility(View.VISIBLE);
            }
        });

        btn_asignar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(getIntent().getStringExtra("servicio"));
                if(getIntent().getStringExtra("servicio").equals(getString(R.string.st_limpieza_general))){
                    calendario.addEvent(new Event(getColor(R.color.color_limpieza_general),dia, R.string.st_limpieza_general));
                    tv_calendario.setVisibility(View.INVISIBLE);
                    btn_asignar.setVisibility(View.INVISIBLE);
                    rg_calendario.setVisibility(View.INVISIBLE);
                }
            }
        });

        btn_Atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}
