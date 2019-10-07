package com.example.goazen.ui.recibos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.goazen.R;

public class RecibosFragment extends Fragment {

    private RecibosViewModel recibosViewModel;
    private Button btnDescargar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        recibosViewModel =
                ViewModelProviders.of(this).get(RecibosViewModel.class);
        View root = inflater.inflate(R.layout.fragment_recibos, container, false);

         btnDescargar= root.findViewById(R.id.buttonDescarga);

         btnDescargar.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {


             }
         });






        return root;
    }
}