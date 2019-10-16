package com.example.goazen.Trabajador.ui.nominas;

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

public class NominasFragment extends Fragment {

    private NominasViewModel nominasViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        nominasViewModel =
                ViewModelProviders.of(this).get(NominasViewModel.class);
        View root = inflater.inflate(R.layout.fragment_nominas, container, false);
        final TextView textView = root.findViewById(R.id.tv_nominas);
        nominasViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}