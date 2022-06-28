package com.example.proyectof;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {
 Button btn_regresar,btn;
 EditText email,password;
 FirebaseAuth mAucth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mAucth = FirebaseAuth.getInstance();
        email = findViewById(R.id.correo);
        btn = findViewById(R.id.regresar);
        password = findViewById(R.id.Contrañ);
        btn_regresar = findViewById(R.id.btn_ingresar);

        btn_regresar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String emailUser = email.getText().toString().trim();
                String passUser = password.getText().toString().trim();

                if(emailUser.isEmpty() && passUser.isEmpty()) {
                    Toast.makeText(HomeActivity.this, "Ingresar los Datos", Toast.LENGTH_SHORT).show();
                }else{
                    loginUser(emailUser,passUser);
                }
            }
        });
    }
    public void abrir1(View view) {
        Intent abrirprimera = new Intent(HomeActivity.this, RegisterActivity.class);
        startActivity(abrirprimera);
    }

    private void loginUser(String emailUser, String passUser) {
        mAucth.signInWithEmailAndPassword(emailUser,passUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    finish();
                    startActivity(new Intent(HomeActivity.this, AuthActivity.class));
                    Toast.makeText(HomeActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(HomeActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(HomeActivity.this, "Error al  iniciar Sesión", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAucth.getCurrentUser();
        if(user !=null){
            startActivity(new Intent(HomeActivity.this,AuthActivity.class));
            finish();
        }
    }
}
