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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class Cadastro extends AppCompatActivity {

    Button cad;
    TextView tx_map;
    ImageView ativar_camera;
    Bitmap image;
    EditText usuario, profissao, senha;

    Preservador  preservador = new Preservador();
    DatabaseReference databaseReference;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        usuario = findViewById(R.id.edt_usuarioname);
        profissao = findViewById(R.id.edt_prf);
        senha = findViewById(R.id.edt_senha_cd);

        FirebaseApp.initializeApp(this);

        databaseReference = FirebaseDatabase.getInstance().getReference("preservador");

        cad = findViewById(R.id.btn_pess);
        tx_map = findViewById(R.id.text_map);
        ativar_camera= findViewById(R.id.edt_camera);

        if (ActivityCompat.checkSelfPermission(Cadastro.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(Cadastro.this, new String[] {Manifest.permission.CAMERA}, 0);


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

                if(usuario.getText().toString().trim().isEmpty()){
                    usuario.setError("Campo Vazio");
                }else if(profissao.getText().toString().trim().isEmpty()){
                    profissao.setError("Campo Vazio");

                }else if (senha.getText().toString().trim().isEmpty()){
                    senha.setError("Campo Vazio");

                }else{




                preservador.setUsuario(usuario.getText().toString());
                preservador.setProfissao(profissao.getText().toString());
                preservador.setSenha(senha.getText().toString());

                DatabaseReference reference = databaseReference.push();
                preservador.setIdPreservador(reference.getKey());
                databaseReference.child(preservador.getIdPreservador()).setValue(preservador);

                NotificationCompat.Builder builder =
                        alerta.builder
                                ("Preservar é vida"
                                        ,"Sua conta foi criada ");
                alerta.getManager().notify(new Random().nextInt(), builder.build());

                }

            }
        });


        ativar_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            image = (Bitmap) extras.get("data");
            ativar_camera.setImageBitmap(image);

        }

        super.onActivityResult(requestCode, resultCode , data);
    }



}