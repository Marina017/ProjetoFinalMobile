package com.example.projetofinal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private RemedioAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        obterRemediosDoBanco();
    }

    private void obterRemediosDoBanco() {
        RemedioService service = RetrofitClient.getClient().create(RemedioService.class);

        Call<List<Remedio>> call = service.listarRemedios();

        call.enqueue(new Callback<List<Remedio>>() {
            @Override
            public void onResponse(@NonNull Call<List<Remedio>> call, @NonNull Response<List<Remedio>> response) {
                // Verifica se a resposta HTTP veio com sucesso (Status 200 OK) e se o corpo não está vazio
                if (response.isSuccessful() && response.body() != null) {
                    List<Remedio> listaDeRemedios = response.body();

                    // 4. Instancia o seu Adapter passando a lista real da API
                    adapter = new RemedioAdapter(listaDeRemedios, remedio -> {
                        // Mantém a ação de clique do Toast que você já tinha criado!
                        Toast.makeText(getContext(), "Selecionado: " + remedio.getNome(), Toast.LENGTH_SHORT).show();
                    });

                    // 5. Vincula o adapter configurado ao seu RecyclerView da tela
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(getContext(), "Erro ao processar a lista de remédios", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Remedio>> call, @NonNull Throwable t) {
                // Caso ocorra falha de rede (Ex: Sem internet ou servidor Railway derrubado)
                Toast.makeText(getContext(), "Erro de conexão com o servidor: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}