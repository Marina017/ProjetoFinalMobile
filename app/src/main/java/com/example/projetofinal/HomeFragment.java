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

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private RemedioAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // 1. O onCreateView deve focar apenas em inflar (conectar) o arquivo XML
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 2. onViewCreated é o lugar correto para configurar as Views e a lógica
        configurarRecyclerView(view);
        carregarDados();
    }

    private void configurarRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);

        // Usamos requireContext() por ser mais seguro contra NullPointerException
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    private void carregarDados() {
        List<Remedio> lista = new ArrayList<>();

        // Os 4 iniciais
        lista.add(new Remedio("Dipirona", "Analgésico", "Alivia dores e febre", android.R.drawable.ic_menu_help));
        lista.add(new Remedio("Amoxicilina", "Antibiótico", "Combate infecções bacterianas", android.R.drawable.ic_menu_help));
        lista.add(new Remedio("Vitamina C", "Suplemento", "Fortalece o sistema imunológico", android.R.drawable.ic_menu_help));
        lista.add(new Remedio("Omeprazol", "Antiácido", "Protege o estômago", android.R.drawable.ic_menu_help));

        // +10 novos remédios para testar a rolagem da tela
        lista.add(new Remedio("Paracetamol", "Analgésico", "Indicado para alívio de dores leves e febre", android.R.drawable.ic_menu_help));
        lista.add(new Remedio("Ibuprofeno", "Anti-inflamatório", "Reduz inflamações, dores musculares e febre", android.R.drawable.ic_menu_help));
        lista.add(new Remedio("Losartana", "Anti-hipertensivo", "Ajuda no controle da pressão arterial", android.R.drawable.ic_menu_help));
        lista.add(new Remedio("Metformina", "Antidiabético", "Auxilia no controle do açúcar no sangue", android.R.drawable.ic_menu_help));
        lista.add(new Remedio("Simvastatina", "Hipolipemiante", "Utilizado para a redução do colesterol ruim", android.R.drawable.ic_menu_help));
        lista.add(new Remedio("Loratadina", "Anti-histamínico", "Alivia sintomas de alergias e rinite", android.R.drawable.ic_menu_help));
        lista.add(new Remedio("Fluoxetina", "Antidepressivo", "Auxilia no tratamento de ansiedade e depressão", android.R.drawable.ic_menu_help));
        lista.add(new Remedio("Buscopan", "Antiespasmódico", "Alivia cólicas estomacais e abdominais", android.R.drawable.ic_menu_help));
        lista.add(new Remedio("Salbutamol", "Broncodilatador", "Facilita a respiração em crises de asma", android.R.drawable.ic_menu_help));
        lista.add(new Remedio("Clonazepam", "Ansiolítico", "Auxilia no controle de distúrbios do sono e ansiedade", android.R.drawable.ic_menu_help));

        adapter = new RemedioAdapter(lista, remedio ->
                Toast.makeText(requireContext(), "Selecionado: " + remedio.getNome(), Toast.LENGTH_SHORT).show()
        );

        recyclerView.setAdapter(adapter);
    }
}