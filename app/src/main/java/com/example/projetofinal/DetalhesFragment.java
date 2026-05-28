package com.example.projetofinal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalhesFragment extends Fragment {

    private TextView txtNome, txtLaboratorio, txtCategoria, txtDosagem, txtDescricao;
    private ImageView imgRemedio;
    private int remedioId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Infla o layout da tela de detalhes
        return inflater.inflate(R.layout.fragment_detalhes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 1. Vincula os componentes do XML fragment_detalhes
        // ⚠️ Verifique se esses IDs batem com o seu arquivo XML!
        txtNome = view.findViewById(R.id.txtNomeDetalhe);
        txtLaboratorio = view.findViewById(R.id.txtLaboratorioDetalhe);
        txtCategoria = view.findViewById(R.id.txtCategoriaDetalhe);
        txtDosagem = view.findViewById(R.id.txtDosagemDetalhe);
        txtDescricao = view.findViewById(R.id.txtDescricaoDetalhe);
        imgRemedio = view.findViewById(R.id.imgRemedioDetalhe);

        // 2. Recupera o ID do remédio enviado pela HomeFragment
        if (getArguments() != null) {
            remedioId = getArguments().getInt("remedioId", -1);
        }

        // 3. Se o ID for válido, busca as informações atualizadas na API
        if (remedioId != -1) {
            carregarDetalhesDoRemedio();
        } else {
            Toast.makeText(getContext(), "Erro ao carregar dados do remédio", Toast.LENGTH_SHORT).show();
        }
    }

    private void carregarDetalhesDoRemedio() {
        RemedioService service = RetrofitClient.getClient().create(RemedioService.class);
        Call<Remedio> call = service.buscarPorId(remedioId);

        call.enqueue(new Callback<Remedio>() {
            @Override
            public void onResponse(@NonNull Call<Remedio> call, @NonNull Response<Remedio> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Remedio remedio = response.body();

                    // 4. Preenche a tela com os dados vindos direto do MySQL
                    txtNome.setText(remedio.getNome());
                    txtLaboratorio.setText(remedio.getLaboratorio());
                    txtCategoria.setText(remedio.getCategoria());
                    txtDosagem.setText(remedio.getDosagem());
                    txtDescricao.setText(remedio.getDescricao());

                    // Se a imagem for 0, você pode setar uma padrão do sistema
                    if (remedio.getImagem() != 0) {
                        imgRemedio.setImageResource(remedio.getImagem());
                    } else {
                        imgRemedio.setImageResource(android.R.drawable.ic_menu_help);
                    }
                } else {
                    Toast.makeText(DetalhesFragment.this.getContext(), "Remédio não encontrado", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Remedio> call, @NonNull Throwable t) {
                Toast.makeText(DetalhesFragment.this.getContext(), "Erro de conexão: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}