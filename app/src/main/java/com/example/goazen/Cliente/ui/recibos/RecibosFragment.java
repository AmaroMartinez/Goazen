package com.example.goazen.Cliente.ui.recibos;

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
    private Button btnDescargarPrimerRecibo, btnDescargarSegundoRecibo,btnDescargarTercerRecibo, btnDescargarCuartoRecibo;
    private String numeroDescarga;
    private TextView codReciboNumero1, resumenReciboNumero1, precioReciboNumero1;
    private TextView codReciboNumero2, resumenReciboNumero2, precioReciboNumero2;
    private TextView codReciboNumero3, resumenReciboNumero3, precioReciboNumero3;
    private TextView codReciboNumero4, resumenReciboNumero4, precioReciboNumero4;



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

        btnDescargarPrimerRecibo= root.findViewById(R.id.buttonDescargaRecibo1);
        btnDescargarSegundoRecibo= root.findViewById(R.id.buttonDescargaRecibo2);
        btnDescargarTercerRecibo= root.findViewById(R.id.buttonDescargaRecibo3);
        btnDescargarCuartoRecibo= root.findViewById(R.id.buttonDescargaRecibo4);

        codReciboNumero1=root.findViewById(R.id.textViewCod_recibo1);
        resumenReciboNumero1=root.findViewById(R.id.textViewResumenRecibo1);
        precioReciboNumero1=root.findViewById(R.id.textViewPrecioRecibo1);

        codReciboNumero2=root.findViewById(R.id.textViewCod_recibo2);
        resumenReciboNumero2=root.findViewById(R.id.textViewResumenRecibo2);
        precioReciboNumero2=root.findViewById(R.id.textViewPrecioRecibo2);

        codReciboNumero3=root.findViewById(R.id.textViewCod_recibo3);
        resumenReciboNumero3=root.findViewById(R.id.textViewResumenRecibo3);
        precioReciboNumero3=root.findViewById(R.id.textViewPrecioRecibo3);

        codReciboNumero4=root.findViewById(R.id.textViewCod_recibo4);
        resumenReciboNumero4=root.findViewById(R.id.textViewResumenRecibo4);
        precioReciboNumero4=root.findViewById(R.id.textViewPrecioRecibo4);






        btnDescargarPrimerRecibo.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 if (Build.VERSION.SDK_INT> Build.VERSION_CODES.M){
                     numeroDescarga= "DescargaUno";
                     savePdf();

                 }
                 else{
                     savePdf();
                 }

             }
         });

        btnDescargarSegundoRecibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT> Build.VERSION_CODES.M){
                    numeroDescarga= "DescargaDos";
                    savePdf();

                }
                else{
                    savePdf();
                }

            }
        });

        btnDescargarTercerRecibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT> Build.VERSION_CODES.M){
                    numeroDescarga= "DescargaTres";
                    savePdf();

                }
                else{
                    savePdf();
                }

            }
        });

        btnDescargarCuartoRecibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT> Build.VERSION_CODES.M){
                    numeroDescarga= "DescargaCuatro";
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

        if (numeroDescarga.equals("DescargaUno")){

            try{
                PdfWriter.getInstance(mDoc, new FileOutputStream(mFilePath));
                mDoc.open();
                String mcod= codReciboNumero1.getText().toString();
                String mresumen= resumenReciboNumero1.getText().toString();
                String mprecio= precioReciboNumero1.getText().toString();

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

        if (numeroDescarga.equals("DescargaDos")){

            try{
                PdfWriter.getInstance(mDoc, new FileOutputStream(mFilePath));
                mDoc.open();
                String mcod= codReciboNumero2.getText().toString();
                String mresumen= resumenReciboNumero2.getText().toString();
                String mprecio= precioReciboNumero2.getText().toString();

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

        if (numeroDescarga.equals("DescargaTres")){

            try{
                PdfWriter.getInstance(mDoc, new FileOutputStream(mFilePath));
                mDoc.open();
                String mcod= codReciboNumero3.getText().toString();
                String mresumen= resumenReciboNumero3.getText().toString();
                String mprecio= precioReciboNumero3.getText().toString();

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

        if (numeroDescarga.equals("DescargaCuatro")){

            try{
                PdfWriter.getInstance(mDoc, new FileOutputStream(mFilePath));
                mDoc.open();
                String mcod= codReciboNumero4.getText().toString();
                String mresumen= resumenReciboNumero4.getText().toString();
                String mprecio= precioReciboNumero4.getText().toString();

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