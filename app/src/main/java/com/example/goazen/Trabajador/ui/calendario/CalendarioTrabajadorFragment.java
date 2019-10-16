package com.example.goazen.Trabajador.ui.calendario;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.goazen.R;

public class CalendarioTrabajadorFragment extends Fragment {

    private CalendarioTrabajadorViewModel calendarioTrabajadorViewModelViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        calendarioTrabajadorViewModelViewModel =
                ViewModelProviders.of(this).get(CalendarioTrabajadorViewModel.class);
        View root = inflater.inflate(R.layout.fragment_calendario_trabajador, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        calendarioTrabajadorViewModelViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}