package com.example.goazen.ui.recibos;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.goazen.R;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class RecibosFragment extends Fragment {

    private RecibosViewModel recibosViewModel;
    private static final int STORAGE_CODE = 1000;
    private Button btnDescargarRecibo;
    private TextView codRecibo, resumenRecibo, precioRecibo;



    // Permisos de almacenamiento
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        recibosViewModel =
                ViewModelProviders.of(this).get(RecibosViewModel.class);
        View root = inflater.inflate(R.layout.fragment_recibos, container, false);

        checkReadPermission();
        checkWritePermission();

        btnDescargarRecibo= root.findViewById(R.id.buttonDescarga);
        codRecibo=root.findViewById(R.id.textViewCod_recibo1);
        resumenRecibo=root.findViewById(R.id.textViewResumen);
        precioRecibo=root.findViewById(R.id.textViewPrecio);

        btnDescargarRecibo.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 if (Build.VERSION.SDK_INT> Build.VERSION_CODES.M){

                         savePdf();
                 }
                 else{
                     savePdf();
                 }

             }
         });

        return root;
    }

    private void savePdf() {
        Document mDoc =new Document();
        String mFileName =new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(System.currentTimeMillis());
        String mFilePath= Environment.getExternalStorageDirectory()+"/" + mFileName+ ".pdf";

        try{
            PdfWriter.getInstance(mDoc, new FileOutputStream(mFilePath));
            mDoc.open();
            String mcod= codRecibo.getText().toString();
            String mresumen= resumenRecibo.getText().toString();
            String mprecio= precioRecibo.getText().toString();

            mDoc.addAuthor("Goazen");

            mDoc.add(new Paragraph(mcod));
            mDoc.add(new Paragraph(mresumen));
            mDoc.add(new Paragraph(mprecio));
            mDoc.close();
            Toast.makeText(getActivity(), mFileName+".pdf\n is saved to \n"+ mFilePath, Toast.LENGTH_LONG).show();
        }
        catch (Exception e){
            System.out.println(e);
            Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }


    private void checkReadPermission(){
        int permissionCheck = ContextCompat.checkSelfPermission(
                getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            Log.i("Mensaje", "No se tiene permiso suficiente.");
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 225);
        } else {
            Log.i("Mensaje", "Tienes permiso.");
        }
    }

    private void checkWritePermission(){
        int permissionCheck = ContextCompat.checkSelfPermission(
                getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            Log.i("Mensaje", "No se tiene permiso suficiente.");
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 225);
        } else {
            Log.i("Mensaje", "Tienes permiso.");
        }
    }




}