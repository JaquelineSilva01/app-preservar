package com.example.preservacao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class TelaUsuario extends AppCompatActivity {

    Button bt_qmd, bt_dsm, bt_psv, bt_sair, bt_pf;

    Preservador preservador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_usuario);

        bt_pf = findViewById(R.id.bt_pf);
        bt_sair = findViewById(R.id.sair_app);
        bt_psv = findViewById(R.id.bt_psv);

        Bundle prev = getIntent().getExtras();
        if ((prev!= null) && (prev.containsKey("preservador"))) {
            preservador  = (Preservador) prev.getSerializable("preservador");

        }else {

            Intent intent = new Intent(TelaUsuario.this, MainActivity.class);
            Toast.makeText(TelaUsuario.this,
                    "Conta não autorizada!", Toast.LENGTH_LONG).show();
            startActivity(intent);
            finish();

        }

        bt_psv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(TelaUsuario.this, MinhaAPI.class);
                intent.putExtra("preservador", preservador);
                startActivity(intent);
                finish();
            }
        });

        bt_pf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaUsuario.this, Perfil.class);
                intent.putExtra("preservador", preservador);
                startActivity(intent);
                finish();
            }
        });



    }
}