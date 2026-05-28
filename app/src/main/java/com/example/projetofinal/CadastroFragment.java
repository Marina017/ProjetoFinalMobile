package com.example.projetofinal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class CadastroFragment extends Fragment {

    private TextInputEditText editNome, editDosagem, editQuantidade, editObservacoes;
    private AutoCompleteTextView spinnerTipo;
    private MaterialButton btnSalvar;

    private final String[] tipos = {
            "Comprimido", "Cápsula", "Líquido / Xarope", "Injetável", "Pomada / Creme", "Outro"
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cadastro, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editNome        = view.findViewById(R.id.editNome);
        editDosagem     = view.findViewById(R.id.editDosagem);
        editQuantidade  = view.findViewById(R.id.editQuantidade);
        editObservacoes = view.findViewById(R.id.editObservacoes);
        spinnerTipo     = view.findViewById(R.id.spinnerTipo);
        btnSalvar       = view.findViewById(R.id.btnSalvar);

        // Preenche o dropdown de tipos
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                tipos
        );
        spinnerTipo.setAdapter(adapter);

        btnSalvar.setOnClickListener(v -> salvarRemedio());
    }

    private void salvarRemedio() {
        String nome       = editNome.getText() != null ? editNome.getText().toString().trim() : "";
        String dosagem    = editDosagem.getText() != null ? editDosagem.getText().toString().trim() : "";
        String quantidade = editQuantidade.getText() != null ? editQuantidade.getText().toString().trim() : "";
        String tipo       = spinnerTipo.getText() != null ? spinnerTipo.getText().toString().trim() : "";
        String obs        = editObservacoes.getText() != null ? editObservacoes.getText().toString().trim() : "";

        // Validação dos campos obrigatórios
        if (nome.isEmpty()) {
            editNome.setError("Informe o nome do remédio");
            editNome.requestFocus();
            return;
        }
        if (dosagem.isEmpty()) {
            editDosagem.setError("Informe a dosagem");
            editDosagem.requestFocus();
            return;
        }
        if (quantidade.isEmpty()) {
            editQuantidade.setError("Informe a quantidade");
            editQuantidade.requestFocus();
            return;
        }
        if (tipo.isEmpty()) {
            spinnerTipo.setError("Selecione o tipo");
            spinnerTipo.requestFocus();
            return;
        }

        // Por enquanto apenas exibe confirmação
        // Aqui você conectará ao Room ou Firebase futuramente
        String mensagem = "Remédio \"" + nome + "\" cadastrado com sucesso!";
        Toast.makeText(requireContext(), mensagem, Toast.LENGTH_SHORT).show();

        limparCampos();
    }

    private void limparCampos() {
        editNome.setText("");
        editDosagem.setText("");
        editQuantidade.setText("");
        editObservacoes.setText("");
        spinnerTipo.setText("", false);
    }
}