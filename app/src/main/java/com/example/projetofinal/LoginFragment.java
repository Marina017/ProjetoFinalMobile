package com.example.projetofinal;

import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class LoginFragment extends Fragment {

    public LoginFragment() { }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextInputEditText inputUsuario = view.findViewById(R.id.input_usuario);
        TextInputEditText inputSenha = view.findViewById(R.id.input_senha);
        MaterialButton btnEntrar = view.findViewById(R.id.btn_entrar);

        btnEntrar.setOnClickListener(v -> {
            String usuario = inputUsuario.getText() != null ? inputUsuario.getText().toString().trim() : "";
            String senha = inputSenha.getText() != null ? inputSenha.getText().toString().trim() : "";

            // Campos vazios
            if (usuario.isEmpty() || senha.isEmpty()) {
                Toast.makeText(requireContext(), "Preencha email e senha", Toast.LENGTH_SHORT).show();
                return;
            }

            // Valida o email
            if (!emailValido(usuario)) {
                inputUsuario.setError("Digite um email válido");
                inputUsuario.requestFocus();
                return;
            }

            // Valida a senha (exatamente 5 números)
            if (!senhaValida(senha)) {
                inputSenha.setError("A senha deve ter exatamente 5 números");
                inputSenha.requestFocus();
                return;
            }

            // Tudo certo: entra no app
            NavHostFragment.findNavController(LoginFragment.this)
                    .navigate(R.id.action_login_to_home);
        });
    }

    // Verifica se o texto é um email válido
    private boolean emailValido(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    // Verifica se a senha tem exatamente 5 dígitos numéricos
    private boolean senhaValida(String senha) {
        return senha.matches("\\d{5}");
    }
}