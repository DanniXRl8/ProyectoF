package com.example.proyectof;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class CreateProFragment extends DialogFragment {
    Button btn_add;
    EditText Nombre,Piezas,color;
    FirebaseFirestore mfirestore;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_create_pro, container, false);
        mfirestore = FirebaseFirestore.getInstance();
        Nombre = v.findViewById(R.id.Nombre0);
        Piezas = v.findViewById(R.id.piezas0);
        color = v.findViewById(R.id.Color);
        btn_add = v.findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Nombrepro = Nombre.getText().toString().trim();
                String Piezaspro = Piezas.getText().toString().trim();
                String colorpro = color.getText().toString().trim();
                if (Nombrepro.isEmpty() && Piezaspro.isEmpty() && colorpro.isEmpty()) {

                    Toast.makeText(getContext(), "Ingresar los datos", Toast.LENGTH_SHORT).show();
                } else {
                    postPet(Nombrepro, Piezaspro, colorpro);
                }
            }
        });

        return v;
    }
    private void postPet(String Nombrepro, String Piezaspro, String colorpro) {
        Map<String, Object> map = new HashMap<>();
        map.put("Nombre", Nombrepro);
        map.put("Piezas", Piezaspro);
        map.put("color", colorpro);

        mfirestore.collection("Productos").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {

                Toast.makeText(getContext(), "Creado con exito", Toast.LENGTH_SHORT).show();
                getDialog().dismiss();



            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Error Al Ingresar", Toast.LENGTH_SHORT).show();

            }
        });

    }
}