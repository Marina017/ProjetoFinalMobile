package com.example.projetofinal;

import android.os.Bundle;
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

            if (usuario.isEmpty() || senha.isEmpty()) {
                Toast.makeText(requireContext(), "Preencha usuário e senha", Toast.LENGTH_SHORT).show();
                return;
            }

            if (validarLogin(usuario, senha)) {
                NavHostFragment.findNavController(LoginFragment.this)
                        .navigate(R.id.action_login_to_home);
            } else {
                Toast.makeText(requireContext(), "Usuário ou senha inválidos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // ===== Por enquanto: validação fixa =====
    // Quando tiver backend, troque APENAS este método pela chamada à API.
    private boolean validarLogin(String usuario, String senha) {
        return usuario.equals("admin") && senha.equals("1234");
    }
}