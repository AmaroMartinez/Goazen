package com.example.goazen.Cliente.ui.servicios;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.goazen.DatosUsuario;
import com.example.goazen.R;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class Calendario extends AppCompatActivity {

    /*Declaramos las variables necesarias para el calendario*/
    CompactCalendarView calendario;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("dd - mm - yyyy");

    //Declaramos las variables necesarias para el guardado de datos.
    private long dia;
    private DateFormat simple = new SimpleDateFormat("dd MMM yyyy");
    private  String hora;


    //Declaramos los objetos necesarios
    private TextView tv_calendario;
    private RadioGroup rg_calendario;
    private RadioButton rb_primera_hora;
    private RadioButton rb_segunda_hora;
    private RadioButton rb_tercera_hora;
    private LinearLayout ll_contenedor_botones;
    private Button btn_asignar;
    private Button btn_Atras;
    private static FirebaseFirestore db;
    private ArrayList EventosCliente;

    private String servicio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Inflamos el Activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        //Inicialización de objetos
        tv_calendario = findViewById(R.id.tv_calendario);
        rg_calendario = findViewById(R.id.rg_calendario);
        rb_primera_hora = findViewById(R.id.rb_Hora_10_00);
        rb_segunda_hora = findViewById(R.id.rb_Hora_11_00);
        rb_tercera_hora = findViewById(R.id.rb_Hora_12_00);
        ll_contenedor_botones = findViewById(R.id.ll_horizontal);
        btn_asignar = findViewById(R.id.btn_asignar);
        btn_Atras = findViewById(R.id.btn_atras);

        //Configuramos la cabecera
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
                System.out.println(simple.format(dia));




                //Hacemos visibles las horas disponibles
                tv_calendario.setVisibility(View.VISIBLE);
                rg_calendario.setVisibility(View.VISIBLE);

            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                actionBar.setTitle(dateFormatMonth.format(firstDayOfNewMonth));
            }
        });

        //Configuramos la acción de los radioButton para que se hagan visibles
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
                hora="10:00";
            }
        });

        rb_segunda_hora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_asignar.setVisibility(View.VISIBLE);
                hora="11:00";
            }
        });

        rb_tercera_hora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_asignar.setVisibility(View.VISIBLE);
                hora="12:00";
            }
        });

        /*Configuramos el boton asignar para que cree los eventos
        * y los añada a la lista de eventos del calendario*/
        btn_asignar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(getIntent().getStringExtra("servicio"));
                if(getIntent().getStringExtra("servicio").equals(getString(R.string.st_limpieza_general))){
                    calendario.addEvent(new Event(getColor(R.color.color_limpieza_general),dia, R.string.st_limpieza_general));
                    servicio="Limpieza general";
                    //EventosCliente.add();

                    crearEventobd();


                    tv_calendario.setVisibility(View.INVISIBLE);
                    btn_asignar.setVisibility(View.INVISIBLE);
                    rg_calendario.setVisibility(View.INVISIBLE);
                }
                else if(getIntent().getStringExtra("servicio").equals(getString(R.string.st_limpieza_cristales))){
                    calendario.addEvent(new Event(getColor(R.color.color_limpieza_cristales),dia, R.string.st_limpieza_cristales));
                    servicio="Limpieza de cristales";

                    crearEventobd();

                    tv_calendario.setVisibility(View.INVISIBLE);
                    btn_asignar.setVisibility(View.INVISIBLE);
                    rg_calendario.setVisibility(View.INVISIBLE);
                }
                else if(getIntent().getStringExtra("servicio").equals(getString(R.string.st_cocina))){
                    calendario.addEvent(new Event(getColor(R.color.color_cocina),dia, R.string.st_cocina));
                    servicio="Cocina";

                    crearEventobd();
                    tv_calendario.setVisibility(View.INVISIBLE);
                    btn_asignar.setVisibility(View.INVISIBLE);
                    rg_calendario.setVisibility(View.INVISIBLE);
                }
                else if(getIntent().getStringExtra("servicio").equals(getString(R.string.st_plancha))){
                    calendario.addEvent(new Event(getColor(R.color.color_plancha),dia, R.string.st_plancha));
                    servicio="Plancha";

                    crearEventobd();
                    tv_calendario.setVisibility(View.INVISIBLE);
                    btn_asignar.setVisibility(View.INVISIBLE);
                    rg_calendario.setVisibility(View.INVISIBLE);
                }
                else if(getIntent().getStringExtra("servicio").equals(getString(R.string.st_paseo_mascotas))){
                    calendario.addEvent(new Event(getColor(R.color.color_paseo_mascotas),dia, R.string.st_paseo_mascotas));
                    servicio="Paseo de mascotas";

                    crearEventobd();
                    tv_calendario.setVisibility(View.INVISIBLE);
                    btn_asignar.setVisibility(View.INVISIBLE);
                    rg_calendario.setVisibility(View.INVISIBLE);
                }
                else if(getIntent().getStringExtra("servicio").equals(getString(R.string.st_regado_plantas))){
                    calendario.addEvent(new Event(getColor(R.color.color_regado_plantas),dia, R.string.st_regado_plantas));
                    servicio="Regado de plantas";

                    crearEventobd();
                    tv_calendario.setVisibility(View.INVISIBLE);
                    btn_asignar.setVisibility(View.INVISIBLE);
                    rg_calendario.setVisibility(View.INVISIBLE);
                }
                else if(getIntent().getStringExtra("servicio").equals(getString(R.string.st_lavanderia))){
                    calendario.addEvent(new Event(getColor(R.color.color_lavanderia),dia, R.string.st_lavanderia));
                    servicio="Lavanderia";

                    crearEventobd();
                    tv_calendario.setVisibility(View.INVISIBLE);
                    btn_asignar.setVisibility(View.INVISIBLE);
                    rg_calendario.setVisibility(View.INVISIBLE);
                }
            }
        });

        //Configuramos un botón de atras para volver a la pantalla de servicios.
        btn_Atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private void crearEventobd(){
        db = FirebaseFirestore.getInstance();
        CollectionReference Eventos = db.collection("Evento");




        Map<String, Object> datos = new HashMap<>();
        datos.put("Adress", DatosUsuario.getAdress());
        datos.put("Fecha",simple.format(dia)+" "+hora);
        datos.put("Titulo",servicio);
        datos.put("Trabajador","22222222A");
        datos.put("Cliente",DatosUsuario.getDNI());
        Eventos.document(servicio+ " "+simple.format(dia) ).set(datos);


    }
}
