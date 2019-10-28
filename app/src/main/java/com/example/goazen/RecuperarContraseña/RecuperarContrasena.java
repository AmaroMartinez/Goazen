package com.example.goazen.RecuperarContraseña;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.goazen.LoginActivity;
import com.example.goazen.R;
import com.example.goazen.Values;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class RecuperarContrasena extends AppCompatActivity {

    private Button btnEnviarRecu;
    private EditText etRCEmail;
    private TextView tvEmailNoExiste;
    private String  fbcontrasena;
    private static FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_contrasena);

        btnEnviarRecu=findViewById(R.id.buttonEnviarRecuContra);
        etRCEmail= findViewById(R.id.editTextRecuCuenta);
        tvEmailNoExiste=findViewById(R.id.textViewNoExiste);


        btnEnviarRecu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //El siguente metodo accede a la base de datos y depues envia un email con la contraseña.
                FirebaseRecuperarContraseña();

            }
        });


        //Creamos el PopUp
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        //Le damos medidas
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width), (int)(height/1.5));
    }


    private void FirebaseRecuperarContraseña(){

        db = FirebaseFirestore.getInstance();

        // Hace la busqueda del email introducido
        db.collection("Usuarios")
                .whereEqualTo("email", etRCEmail.getText().toString())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        //Si el email existe
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                fbcontrasena=document.get("Contrasena").toString();

                                // Envia el mensaje
                                final ProgressDialog dialog = new ProgressDialog(RecuperarContrasena.this);
                                dialog.setTitle("Enviando email");
                                dialog.setMessage("Espera por favor");
                                dialog.show();
                                Thread sender = new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            // Se introduce el email y la contraseña. Este email enviara los correos.
                                            GMailSender sender = new GMailSender("bagoazgoazen@gmail.com", "1234goazen");
                                            // Asuto, mensaje, quien lo envia, edittext de quien lo envia
                                            sender.sendMail("Goazen",
                                                    "Hola.\n\n\nGracias por ponerte en contacto con nosotros. Tu contraseña es " +fbcontrasena,
                                                    "bagoazgoazen@gmail.com", etRCEmail.getText().toString() );
                                            dialog.dismiss();
                                        } catch (Exception e) {
                                            Log.d("mylog", "Error: " + e.getMessage());
                                        }
                                    }
                                });
                                sender.start();

                                // Una vex enviado el correo, vuelve a la pantalla de login
                                Intent myIntent = new Intent(RecuperarContrasena.this, LoginActivity.class);
                                startActivity(myIntent);

                            }

                        }

                        if (!task.isSuccessful()) {
                            //System.out.println("correo invalido");
                        }

                        else { //Si el correo no existe en la base de datos

                            Log.d(Values.tag_log, "Error getting documents: ", task.getException());
                            System.out.println("correo invalido");
                            tvEmailNoExiste.setVisibility(View.VISIBLE);


                        }
                    }
                });


    }
}
