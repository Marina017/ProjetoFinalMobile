package com.example.projetofinal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Remedio> lista = new ArrayList<>();
        lista.add(new Remedio("Dipirona", "Analgésico", "Alivia dores e febre", android.R.drawable.ic_menu_help));
        lista.add(new Remedio("Amoxicilina", "Antibiótico", "Combate infecções bacterianas", android.R.drawable.ic_menu_help));
        lista.add(new Remedio("Vitamina C", "Suplemento", "Fortalece o sistema imunológico", android.R.drawable.ic_menu_help));
        lista.add(new Remedio("Omeprazol", "Antiácido", "Protege o estômago", android.R.drawable.ic_menu_help));

        RemedioAdapter adapter = new RemedioAdapter(lista, remedio ->
                Toast.makeText(getContext(), "Selecionado: " + remedio.getNome(), Toast.LENGTH_SHORT).show()
        );

        recyclerView.setAdapter(adapter);
        return view;
    }
}