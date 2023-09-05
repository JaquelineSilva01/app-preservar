package com.example.preservacao;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Cadastro extends AppCompatActivity {

    Button cad;
    TextView tx_map;

    Bitmap bitmap;
    ImageView imageView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        cad = findViewById(R.id.btn_pess);
        tx_map = findViewById(R.id.text_map);

        imageView = findViewById(R.id.edt_camera);
        if (ActivityCompat.checkSelfPermission(Cadastro.this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(Cadastro.this, new String[] {Manifest.permission.CAMERA},0);

        }
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
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ligar();
            }
        });

    }
    public void Ligar(){

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && resultCode == Cadastro.RESULT_OK ){

            Bundle extras = data.getExtras();
            bitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(bitmap);

        }

        super.onActivityResult(requestCode, resultCode,data);
}
}