package com.example.proyectof;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class AuthActivity extends AppCompatActivity {
Button btn_ad,btn,btn_salir,btn_fragemnet;

FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        mAuth = FirebaseAuth.getInstance();
        btn_fragemnet=findViewById(R.id.btn_agregar0);
        btn_salir = findViewById(R.id.btn_close);
        btn_ad = findViewById(R.id.button);
        btn=findViewById(R.id.btn3);

        btn_ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AuthActivity.this, CreteProActivity.class));
            }
        });
        btn_salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            mAuth.signOut();
            finish();
            startActivity(new Intent(AuthActivity.this,HomeActivity.class));
            }
        });

btn_fragemnet.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        CreateProFragment fm=new CreateProFragment();
        fm.show(getSupportFragmentManager(),"Navegar al Fragment");
    }
});
    }
    public void abrir2(View view) {
        Intent abrirprimera2 = new Intent(AuthActivity.this, MostrarProActivity.class);
        startActivity(abrirprimera2);
    }

    }


