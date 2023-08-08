package com.example.preservacao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class Cadastro extends AppCompatActivity {

    Button cad;
    TextView tx_map;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        cad = findViewById(R.id.btn_pess);
        tx_map = findViewById(R.id.text_map);

        tx_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cadastro.this, regiao.class);
                startActivity(intent);
            }
        });

        gerarNotificacao alerta = new
                gerarNotificacao(getApplicationContext());

        cad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder builder =
                        alerta.builder
                                ("Preservar é vida"
                                        ,"Sua conta foi criada ");
                alerta.getManager().notify(new Random().nextInt(), builder.build());
            }
        });

    }
}