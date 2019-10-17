package com.example.goazen.perfil;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.example.goazen.DatosCliente;
import com.example.goazen.R;

public class PopUpCambiarContrasena extends Activity {

    private Button btnguardarcontrasena;
    private EditText antiguacontrasena;
    private EditText nuevacontrasenacomprobar;
    private EditText nuevacontrasena;
    private TextView infocontrasena;
    private CheckBox vercontrasenas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_cambiar_contrasena);

        btnguardarcontrasena = findViewById(R.id.btn_guardar_contrasena);
        antiguacontrasena = findViewById(R.id.et_antigua_contrasena);
        nuevacontrasenacomprobar = findViewById(R.id.et_nueva_contrasena_comprobar);
        nuevacontrasena = findViewById(R.id.et_nueva_contrasena);
        infocontrasena = findViewById(R.id.info_contrasena);
        vercontrasenas = findViewById(R.id.ch_ver_contrasenas);

        //Creamos el PopUp
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        //Le damos medidas
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout(width, (int)(height/1.3));

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
                    if (nuevacontrasena.getText().toString().equals(nuevacontrasenacomprobar.getText().toString())) {
                        if (!(antiguacontrasena.getText().toString().equals(nuevacontrasena.getText().toString()))) {
                            DatosCliente.setContrasena(nuevacontrasena.getText().toString());
                            DatosCliente.writeUsuario();
                            infocontrasena.setText(R.string.st_tv_ok);
                        } else {
                            infocontrasena.setText(R.string.st_tv_fail_nya);
                        }
                    } else {
                        infocontrasena.setText(R.string.st_tv_fail_nyn);
                    }
                } else {
                    infocontrasena.setText(R.string.st_tv_fail_a);
                }
            }
        });

        vercontrasenas.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    antiguacontrasena.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    nuevacontrasenacomprobar.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    nuevacontrasena.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                } else {
                    antiguacontrasena.setInputType(129);
                    nuevacontrasenacomprobar.setInputType(129);
                    nuevacontrasena.setInputType(129);
                }
            }
        });


    }


}
