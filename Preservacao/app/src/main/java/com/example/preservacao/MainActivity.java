package com.example.preservacao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    Button btn;
    TextView view;

    private DatabaseReference databaseReference;
    Preservador preservador;
    Preservador presev ;

    EditText usuarioLog, senhLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuarioLog = findViewById(R.id.edt_user_login);
        senhLog = findViewById(R.id.edt_senha_login);

        FirebaseApp.initializeApp(this);
        databaseReference = FirebaseDatabase.getInstance().getReference("preservador");
        btn = findViewById(R.id.ent_login);
        view = findViewById(R.id.criar_conta);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Cadastro.class);
                startActivity(intent);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (usuarioLog.getText().toString().trim().isEmpty()){
                    usuarioLog.setError("Campo Vazio");
                }else if(senhLog.getText().toString().trim().isEmpty()){
                    senhLog.setError("Campo Vazio");

                }else
                {
                    Query query = databaseReference.orderByChild("usuario")
                            .equalTo(usuarioLog.getText().toString());


                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            for (DataSnapshot snapshot1: snapshot.getChildren()){
                                preservador = snapshot1.getValue(Preservador.class);
                                presev = preservador;
                            }

                            Query query2 = databaseReference.orderByChild("senha").equalTo(senhLog.getText().toString());
                            query2.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {

                                    for (DataSnapshot snapshot1: snapshot.getChildren()){
                                        preservador = snapshot1.getValue(Preservador.class);
                                        presev = preservador;


                                    }


                                    Intent intent = new Intent(MainActivity.this, TelaUsuario.class);
                                    intent.putExtra("preservador", presev);
                                    startActivity(intent);
                                    finish();

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });

    }
}