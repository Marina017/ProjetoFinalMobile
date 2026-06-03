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

    private TextInputEditText editNome, editDosagem, editQuantidade,
            editPreco, editDescricao, editQuandoTomar, editContraindicacoes, editObservacoes;
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

        editNome             = view.findViewById(R.id.editNome);
        editDosagem          = view.findViewById(R.id.editDosagem);
        editQuantidade       = view.findViewById(R.id.editQuantidade);
        editPreco            = view.findViewById(R.id.editPreco);
        editDescricao        = view.findViewById(R.id.editDescricao);
        editQuandoTomar      = view.findViewById(R.id.editQuandoTomar);
        editContraindicacoes = view.findViewById(R.id.editContraindicacoes);
        editObservacoes      = view.findViewById(R.id.editObservacoes);
        spinnerTipo          = view.findViewById(R.id.spinnerTipo);
        btnSalvar            = view.findViewById(R.id.btnSalvar);

        // Preenche o dropdown de tipos
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                tipos
        );
        spinnerTipo.setAdapter(adapter);

        btnSalvar.setOnClickListener(v -> salvarRemedio());
    }

    private String texto(TextInputEditText campo) {
        return campo.getText() != null ? campo.getText().toString().trim() : "";
    }

    private void salvarRemedio() {
        String nome             = texto(editNome);
        String dosagem          = texto(editDosagem);
        String quantidade       = texto(editQuantidade);
        String preco            = texto(editPreco);
        String descricao        = texto(editDescricao);
        String quandoTomar      = texto(editQuandoTomar);
        String contraindicacoes = texto(editContraindicacoes);
        String obs              = texto(editObservacoes);
        String tipo             = spinnerTipo.getText() != null ? spinnerTipo.getText().toString().trim() : "";

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

        // Cria o remédio com todas as informações do card
        int icone = android.R.drawable.ic_menu_help;

        Remedio novo = new Remedio(
                nome,
                tipo,
                descricao.isEmpty() ? "Sem descrição" : descricao,
                icone,
                quantidade + " unidade(s)",
                preco.isEmpty() ? "—" : "R$ " + preco,
                contraindicacoes.isEmpty() ? "—" : contraindicacoes,
                quandoTomar.isEmpty() ? ("Dosagem: " + dosagem + " mg") : quandoTomar
        );

        RemedioRepository.adicionar(novo);

        Toast.makeText(requireContext(),
                "Remédio \"" + nome + "\" cadastrado com sucesso!",
                Toast.LENGTH_SHORT).show();

        limparCampos();
    }

    private void limparCampos() {
        editNome.setText("");
        editDosagem.setText("");
        editQuantidade.setText("");
        editPreco.setText("");
        editDescricao.setText("");
        editQuandoTomar.setText("");
        editContraindicacoes.setText("");
        editObservacoes.setText("");
        spinnerTipo.setText("", false);
    }
}