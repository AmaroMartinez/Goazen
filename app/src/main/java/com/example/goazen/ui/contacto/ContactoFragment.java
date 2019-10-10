package com.example.goazen.ui.contacto;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.goazen.R;

public class ContactoFragment extends Fragment {

    private ContactoViewModel contactoViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        contactoViewModel =
                ViewModelProviders.of(this).get(ContactoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_contacto, container, false);

        contactoViewModel.getText().observe(this, new Observer<String>() {
                @Override
                public void onChanged(@Nullable String s) {

                }
            }
        );

        return root;
    }




}