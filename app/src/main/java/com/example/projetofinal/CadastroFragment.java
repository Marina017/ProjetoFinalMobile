package com.example.projetofinal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroFragment extends Fragment {

    // Declaração dos componentes da tela (Views)
    private EditText editNome, editLaboratorio, editCategoria, editDosagem, editDescricao;
    private Button btnCadastrar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Infla o layout do formulário de cadastro
        return inflater.inflate(R.layout.fragment_cadastro, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 1. Vincula os componentes Java com os IDs do seu fragment_cadastro.xml
        // ⚠️ ATENÇÃO: Se os IDs no seu XML forem diferentes, mude os nomes dentro do R.id.xxxx
        editNome = view.findViewById(R.id.editNome);
        editLaboratorio = view.findViewById(R.id.editLaboratorio);
        editCategoria = view.findViewById(R.id.editCategoria);
        editDosagem = view.findViewById(R.id.editDosagem);
        editDescricao = view.findViewById(R.id.editDescricao);
        btnCadastrar = view.findViewById(R.id.btnCadastrar);

        // 2. Configura o clique do botão de cadastrar
        btnCadastrar.setOnClickListener(v -> cadastrarNovoRemedio(v));
    }

    private void cadastrarNovoRemedio(View view) {
        // Captura os textos digitados pelo usuário
        String nome = editNome.getText().toString().trim();
        String laboratorio = editLaboratorio.getText().toString().trim();
        String categoria = editCategoria.getText().toString().trim();
        String dosagem = editDosagem.getText().toString().trim();
        String descricao = editDescricao.getText().toString().trim();

        // 3. Validação básica local (evita enviar dados vazios e gastar processamento da API)
        if (nome.isEmpty() || laboratorio.isEmpty() || categoria.isEmpty() || dosagem.isEmpty() || descricao.isEmpty()) {
            Toast.makeText(getContext(), "Por favor, preencha todos os campos obrigatórios!", Toast.LENGTH_SHORT).show();
            return;
        }

        // 4. Cria o objeto Remedio com os dados da tela (definindo imagem padrão como 0 temporariamente)
        Remedio novoRemedio = new Remedio(nome, laboratorio, categoria, descricao, dosagem, 0);

        // 5. Inicializa o Retrofit e prepara a chamada POST
        RemedioService service = RetrofitClient.getClient().create(RemedioService.class);
        Call<RemedioResponse> call = service.cadastrarRemedio(novoRemedio);

        // Envia de forma assíncrona para o Railway
        call.enqueue(new Callback<RemedioResponse>() {
            @Override
            public void onResponse(@NonNull Call<RemedioResponse> call, @NonNull Response<RemedioResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String mensagemBackend = response.body().getMensagem();
                    Toast.makeText(CadastroFragment.this.getContext(), mensagemBackend, Toast.LENGTH_SHORT).show();

                    Navigation.findNavController(view).navigateUp();
                } else {
                    Toast.makeText(CadastroFragment.this.getContext(), "Erro ao cadastrar: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<RemedioResponse> call, @NonNull Throwable t) {
                Toast.makeText(CadastroFragment.this.getContext(), "Erro de conexão: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}