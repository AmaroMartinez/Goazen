package com.example.goazen.Trabajador.ui.nominas;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.goazen.DatosUsuario;
import com.example.goazen.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/*------------------------------------------------------------------------------------------------
 * EN ESTA CLASE SE VA A CONFIGURAR LA PANTALLA DE VISUALIZACIÓN DE LA ÚLTIMA NOMINA DEL TRABAJADOR
 * -------------------------------------- QUE SE HA LOGUEADO --------------------------------------
 * ------------------------------------------------------------------------------------------------*/

public class NominasFragment extends Fragment {

    private NominasViewModel nominaViewModel;
    private FirebaseFirestore db;
    private Button btnDescargar;
    private EditText domicilio;
    private EditText trabajador;
    private EditText dni;
    private EditText telefono;
    private EditText fecha_de_ingreso;
    private TextView deduccionSB;
    private TextView deduccionKM;
    private TextView deduccionANT;
    private EditText totalNomina;
    private TextView cuantiaKM;
    private TextView cuantiaAnt;
    private double porcentajekm = 0.08;

    //Para el pdf
    private Bitmap bitmap;
    private LinearLayout llPdf;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        nominaViewModel =
                ViewModelProviders.of(this).get(NominasViewModel.class);
        View root = inflater.inflate(R.layout.fragment_nominas, container, false);
        nominaViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });

        // Access a Cloud Firestore instance from your Activity
        db = FirebaseFirestore.getInstance();

        //Enlazamod los textos con nuestras variables
        domicilio = root.findViewById(R.id.et_direccion);
        trabajador = root.findViewById(R.id.et_nombre_apellido);
        dni = root.findViewById(R.id.et_dni);
        telefono = root.findViewById(R.id.et_telefono);
        fecha_de_ingreso = root.findViewById(R.id.et_fecha_ingreso);
        deduccionSB = root.findViewById(R.id.deduccionSB);
        deduccionKM = root.findViewById(R.id.deduccionKM);
        deduccionANT = root.findViewById(R.id.deduccionANT);
        totalNomina = root.findViewById(R.id.et_total);
        cuantiaAnt = root.findViewById(R.id.cuantiaAnt);
        cuantiaKM = root.findViewById(R.id.cuantiaKM);
        //llPdf = root.findViewById(R.id.nominas);

        //Cargamos lo datos de la base de datos, para anexarlos a los textos.

        domicilio.setText(DatosUsuario.getAdress());

        String nombreyapellido = DatosUsuario.getNombre() + " " + DatosUsuario.getApellido();
        trabajador.setText(nombreyapellido);

        dni.setText(DatosUsuario.getDNI());
        telefono.setText(DatosUsuario.getMovil());
        fecha_de_ingreso.setText(DatosUsuario.getAntiguedad());
        deduccionSB.setText(DatosUsuario.getSueldo());
        cuantiaKM.setText(DatosUsuario.getKm());

        Double km = Double.parseDouble(DatosUsuario.getKm());
        km = km * porcentajekm;
        String calculokm = km.toString();
        deduccionKM.setText(km.toString());

        String antiguedad = DatosUsuario.getAntiguedad();
        String año = String.valueOf(antiguedad.charAt(6))+ String.valueOf(antiguedad.charAt(7)) + String.valueOf(antiguedad.charAt(8)) + String.valueOf(antiguedad.charAt(9));
        SimpleDateFormat formatofecha = new SimpleDateFormat("yyyy", Locale.getDefault());
        Date date = new Date();
        String añoactual = formatofecha.format(date);
        int ant = (Integer.parseInt(añoactual) - Integer.parseInt(año));
        cuantiaAnt.setText(Integer.toString(ant));
        deduccionANT.setText(Integer.toString(ant*20));

        Double total = km + (ant*20) + Double.parseDouble(DatosUsuario.getSueldo());
        totalNomina.setText(Double.toString(total));

        /*Gestionamos la descarga de datos en formato pdf*/
        btnDescargar = root.findViewById(R.id.btnDescargar);
        btnDescargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bitmap = loadBitmapFromView(llPdf, llPdf.getWidth(), llPdf.getHeight());
                //createPdf();
            }
        });

        return root;

    }

    public static Bitmap loadBitmapFromView(View v, int width, int height) {
        Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.draw(c);

        return b;
    }

   /* private void createPdf(){
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        //  Display display = wm.getDefaultDisplay();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        float hight = displaymetrics.heightPixels ;
        float width = displaymetrics.widthPixels ;

        int convertHighet = (int) hight, convertWidth = (int) width;

//        Resources mResources = getResources();
//        Bitmap bitmap = BitmapFactory.decodeResource(mResources, R.drawable.screenshot);

        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(convertWidth, convertHighet, 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);

        Canvas canvas = page.getCanvas();

        Paint paint = new Paint();
        canvas.drawPaint(paint);

        bitmap = Bitmap.createScaledBitmap(bitmap, convertWidth, convertHighet, true);

        paint.setColor(Color.BLUE);
        canvas.drawBitmap(bitmap, 0, 0 , null);
        document.finishPage(page);

        // write the document content
        String targetPdf = "/sdcard/pdffromlayout.pdf";
        File filePath;
        filePath = new File(targetPdf);
        try {
            document.writeTo(new FileOutputStream(filePath));

        } catch (IOException e) {
            e.printStackTrace();
        }

        // close the document
        document.close();


        openGeneratedPDF();

    }

    */


    private void openGeneratedPDF(){
        File file = new File("/sdcard/pdffromlayout.pdf");
        if (file.exists())
        {
            Intent intent=new Intent(Intent.ACTION_VIEW);
            Uri uri = Uri.fromFile(file);
            intent.setDataAndType(uri, "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            try
            {
                startActivity(intent);
            }
            catch(ActivityNotFoundException e)
            {
                Toast.makeText(getActivity(), "No Application available to view pdf", Toast.LENGTH_LONG).show();
            }
        }
    }


}