package com.example.projetofinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText edtEmailLogin, edtSenhaLogin;
    private Button btnEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inicializa os componentes
        edtEmailLogin = findViewById(R.id.edtEmailLogin);
        edtSenhaLogin = findViewById(R.id.edtSenhaLogin);
        btnEntrar = findViewById(R.id.btnEntrar);

        btnEntrar.setOnClickListener(v -> {
            String email = edtEmailLogin.getText().toString().trim();
            String senha = edtSenhaLogin.getText().toString().trim();

            if (email.isEmpty() || senha.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            } else {
                // Executa o fluxo de login
                realizarLogin(email, senha);
            }
        });
    }

    private void realizarLogin(String email, String senha) {
        // TODO: Futuramente aqui faremos a chamada de rede usando Retrofit para a sua rota do Node.js
        // Por enquanto, vamos simular que o login deu certo para testar o fluxo das telas:

        if (email.contains("@")) { // Validação simples temporária

            // 1. Criamos a sessão gravando no SharedPreferences
            SharedPreferences preferences = getSharedPreferences("SessaoUsuario", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();

            // Aqui simulamos que o banco nos retornou o nome do usuário cadastrado
            editor.putString("nome_usuario", "Cauã");
            editor.putString("email_usuario", email);
            editor.apply(); // Grava os dados fisicamente no celular

            Toast.makeText(this, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show();

            // 2. Redireciona para a MainActivity (onde estão seus Fragments)
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // Fecha a tela de login para o usuário não voltar nela ao clicar em "Voltar"

        } else {
            Toast.makeText(this, "E-mail ou senha inválidos!", Toast.LENGTH_SHORT).show();
        }
    }
}