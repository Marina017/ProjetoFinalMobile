package com.example.projetofinal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilFragment extends Fragment {

    private TextView txtNomeUsuario, txtSubtituloUsuario, txtQtdRemedios;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_perfil, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Vincula os componentes do XML
        txtNomeUsuario = view.findViewById(R.id.txtNomeUsuario);
        txtSubtituloUsuario = view.findViewById(R.id.txtSubtituloUsuario);
        txtQtdRemedios = view.findViewById(R.id.txtQtdRemedios);

        // Carrega os dados reais do usuário logado
        carregarDadosDoUsuarioLogado();

        // Puxa as estatísticas de remédios do banco
        obterEstatisticasDoBanco();
    }

    private void carregarDadosDoUsuarioLogado() {
        if (getContext() != null) {
            // Abre o arquivo de sessão local chamado "SessaoUsuario"
            SharedPreferences preferences = getContext().getSharedPreferences("SessaoUsuario", Context.MODE_PRIVATE);

            // Busca as strings gravadas. Se não encontrar nada, exibe valores padrão
            String nome = preferences.getString("nome_usuario", "Usuário");
            String email = preferences.getString("email_usuario", "não-identificado@email.com");

            // Aplica os dados do banco nos TextViews correspondentes
            txtNomeUsuario.setText(nome);
            txtSubtituloUsuario.setText(email);
        }
    }

    private void obterEstatisticasDoBanco() {
        RemedioService service = RetrofitClient.getClient().create(RemedioService.class);
        Call<List<Remedio>> call = service.listarRemedios();

        call.enqueue(new Callback<List<Remedio>>() {
            @Override
            public void onResponse(@NonNull Call<List<Remedio>> call, @NonNull Response<List<Remedio>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    int total = response.body().size();
                    txtQtdRemedios.setText(String.valueOf(total));
                } else {
                    txtQtdRemedios.setText("0");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Remedio>> call, @NonNull Throwable t) {
                txtQtdRemedios.setText("?");
            }
        });
    }
}