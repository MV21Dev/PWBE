package com.example.bancosqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText campoNome = findViewById(R.id.campoNome);
        EditText campoPreco = findViewById(R.id.campoPreco);
        EditText campoQtde = findViewById(R.id.campoQtde);
        Button btCadastrar = findViewById(R.id.btCadastrar);

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Recuperar os valores informados na tela
                String nome = campoNome.getText().toString();
                double preco = Double.parseDouble(campoPreco.getText().toString());
                int qtde = Integer.parseInt(campoQtde.getText().toString());
                //Chamar a classe do BancoDeDados e criar um objeto
                BancoDeDados bd = new BancoDeDados(MainActivity.this, 1);
                //Chamar o método cadastrar() e testar o seu resultado
                if(bd.cadastrar(nome, preco, qtde)){
                    Toast.makeText(MainActivity.this,
                        "Produto cadastrado", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,
                            "Erro ao cadastrar", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}