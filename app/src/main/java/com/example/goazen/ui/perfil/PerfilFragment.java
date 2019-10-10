package com.example.goazen.ui.perfil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.example.goazen.R;

public class PerfilFragment extends Fragment {

    private PerfilViewModel perfilViewModel;
    private Button btneditar;
    private EditText etnombre;
    private EditText etapellido;
    private EditText etfnacimiento;
    private EditText ettelefono;
    private EditText etdni;
    private EditText etemail;
    private EditText etdireccion;
    private Button btncambiarcontraseña;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        perfilViewModel =
                ViewModelProviders.of(this).get(PerfilViewModel.class);
        View root = inflater.inflate(R.layout.fragment_perfil, container, false);

        btneditar = root.findViewById(R.id.btn_editar);
        etnombre = root.findViewById(R.id.et_perfil_nombre);
        etapellido = root.findViewById(R.id.et_perfil_apellido);
        etfnacimiento = root.findViewById(R.id.et_fnacimiento);
        ettelefono = root.findViewById(R.id.et_telefono);
        etdni = root.findViewById(R.id.et_dni);
        etemail = root.findViewById(R.id.et_email);
        etdireccion = root.findViewById(R.id.et_direccion);
        btncambiarcontraseña = root.findViewById(R.id.btn_cambiar_contraseña);

        btneditar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (btneditar.getText().equals("Editar")) {
                etnombre.setEnabled(true);
                etapellido.setEnabled(true);
                etdireccion.setEnabled(true);
                etdni.setEnabled(true);
                etemail.setEnabled(true);
                etfnacimiento.setEnabled(true);
                ettelefono.setEnabled(true);
                btncambiarcontraseña.setEnabled(true);

                btneditar.setText("Cancelar");

            } else if (btneditar.getText().equals("Cancelar")){

                /*Fragment currentFragment = getActivity().getFragmentManager().findFragmentById(R.id.fragment_container);
                if (currentFragment instanceof "PerfilFragment") {
                    FragmentTransaction fragTransaction =   (getActivity()).getFragmentManager().beginTransaction();
                    fragTransaction.detach(currentFragment);
                    fragTransaction.attach(currentFragment);
                    fragTransaction.commit();}
            }*/

               // Fragment currentFragment = getFragmentManager().findFragmentByTag(FRAGMENT_TAG);
                Fragment currentFragment = PerfilFragment.this;
                FragmentTransaction fragTransaction = getFragmentManager().beginTransaction();
                fragTransaction.detach(currentFragment);
                fragTransaction.attach(currentFragment);
                fragTransaction.commit();

            }
         }
        });



        return root;
    }


}