package com.example.proyectof;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CreteProActivity extends AppCompatActivity {
Button btn_add;
EditText Nombre,Piezas,color;
FirebaseFirestore mfirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crete_pro);

        this.setTitle("Creación de Productos");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String id = getIntent().getStringExtra("id_Pro");
        mfirestore = FirebaseFirestore.getInstance();


        Nombre = findViewById(R.id.Nombre0);
        Piezas = findViewById(R.id.piezas0);
        color = findViewById(R.id.Color);
        btn_add = findViewById(R.id.btn_add);

        if (id == null || id == "") {
            btn_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String Nombrepro = Nombre.getText().toString().trim();
                    String Piezaspro = Piezas.getText().toString().trim();
                    String colorpro = color.getText().toString().trim();

                    if (Nombrepro.isEmpty() && Piezaspro.isEmpty() && colorpro.isEmpty()) {

                        Toast.makeText(getApplicationContext(), "Ingresar los datos", Toast.LENGTH_SHORT).show();
                    } else {
                        postPet(Nombrepro, Piezaspro, colorpro);
                    }
                }
            });
        } else {
            btn_add.setText("update");
            getPro(id);
            btn_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String Nombrepro = Nombre.getText().toString().trim();
                    String Piezaspro = Piezas.getText().toString().trim();
                    String colorpro = color.getText().toString().trim();
                    if (Nombrepro.isEmpty() && Piezaspro.isEmpty() && colorpro.isEmpty()) {

                        Toast.makeText(getApplicationContext(), "Ingresar los datos", Toast.LENGTH_SHORT).show();
                    } else {
                        updatePet(Nombrepro, Piezaspro, colorpro,id);
                    }
                }
            });
        }
    }

    private void updatePet(String Nombrepro, String Piezaspro, String colorpro, String id) {
        Map<String, Object> map = new HashMap<>();
        map.put("Nombre", Nombrepro);
        map.put("Piezas", Piezaspro);
        map.put("color", colorpro);

        mfirestore.collection("Productos").document(id).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(getApplicationContext(), "Producto Actualizado", Toast.LENGTH_SHORT).show();
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Producto no Actualizado", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void postPet(String Nombrepro, String Piezaspro, String colorpro) {
        Map<String, Object> map = new HashMap<>();
        map.put("Nombre", Nombrepro);
        map.put("Piezas", Piezaspro);
        map.put("color", colorpro);

        mfirestore.collection("Productos").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {

                Toast.makeText(getApplicationContext(), "Creado con exito", Toast.LENGTH_SHORT).show();
                finish();



            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error Al Ingresar", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void getPro(String id){
        mfirestore.collection("Productos").document(id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
            String Nombrepro = documentSnapshot.getString("Nombre");
                String Piezaspro = documentSnapshot.getString("Piezas");
                String colorpro = documentSnapshot.getString("color");

                Nombre.setText(Nombrepro);
                Piezas.setText(Piezaspro);
                color.setText(colorpro);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error al tener los datos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

}