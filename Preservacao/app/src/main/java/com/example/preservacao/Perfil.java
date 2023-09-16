package com.example.preservacao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Perfil extends AppCompatActivity {

    EditText user, prof, senha;
    Button voltar, update, delete;

    Preservador preservador;

    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        user = findViewById(R.id.up_usuario);
        prof = findViewById(R.id.up_prf);
        senha = findViewById(R.id.up_senha_cd);

        FirebaseApp.initializeApp(this);
        databaseReference = FirebaseDatabase.getInstance().getReference();

        voltar = findViewById(R.id.btn_r);
        update = findViewById(R.id.btn_up);
        delete = findViewById(R.id.btn_delete);

        Bundle prev = getIntent().getExtras();
        if ((prev!= null) && (prev.containsKey("preservador"))) {
            preservador  = (Preservador) prev.getSerializable("preservador");

        }else {

            Intent intent = new Intent(Perfil.this, MainActivity.class);
            Toast.makeText(Perfil.this,
                    "Conta não autorizada", Toast.LENGTH_LONG).show();
            startActivity(intent);
            finish();

        }

        user.setHint(preservador.getUsuario().toString());
        prof.setHint(preservador.getProfissao().toString());
        senha.setHint(preservador.getSenha().toString());

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!user.getText().toString().trim().isEmpty()){
                    preservador.setUsuario(user.getText().toString());
                }else if( !prof.getText().toString().trim().isEmpty()){
                    preservador.setProfissao(prof.getText().toString());

                }else if( !senha.getText().toString().trim().isEmpty()){
                    preservador.setSenha(senha.getText().toString());

                }

                databaseReference.child("preservador").child(preservador.getIdPreservador()).setValue(preservador);
                Intent intent = new Intent(Perfil.this, TelaUsuario.class);
                intent.putExtra("preservador", preservador);
                startActivity(intent);
                finish();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("preservador").child(preservador.getIdPreservador()).removeValue();
                Intent intent = new Intent(Perfil.this, MainActivity.class);
                startActivity(intent);
                finish();
                System.exit(0);
            }
        });

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Perfil.this, TelaUsuario.class);
                intent.putExtra("preservador", preservador);
                startActivity(intent);
                finish();
            }
        });

    }
}