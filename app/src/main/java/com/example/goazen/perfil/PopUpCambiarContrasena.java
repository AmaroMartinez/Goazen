package com.example.goazen.perfil;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.goazen.DatosCliente;
import com.example.goazen.R;

public class PopUpCambiarContrasena extends Activity {

    private Button btnguardarcontrasena;
    private EditText antiguacontrasena;
    private EditText nuevacontrasena;
    private TextView infocontrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_cambiar_contrasena);

        btnguardarcontrasena = findViewById(R.id.btn_guardar_contrasena);
        antiguacontrasena = findViewById(R.id.et_antigua_contrasena);
        nuevacontrasena = findViewById(R.id.et_nueva_contrasena);
        infocontrasena = findViewById(R.id.info_contrasena);

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

        btnguardarcontrasena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (antiguacontrasena.getText().toString().equals(DatosCliente.getContrasena())) {
                    DatosCliente.setContrasena(nuevacontrasena.getText().toString());
                    DatosCliente.writeUsuario();
                    infocontrasena.setText(R.string.st_tv_ok);
                } else {
                    infocontrasena.setText(R.string.st_tv_fail);
                }
            }
        });


    }


}
