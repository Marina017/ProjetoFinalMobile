package com.example.projetofinal;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private RemedioAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        configurarRecyclerView(view);
        carregarDados();
    }

    private void configurarRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    private void carregarDados() {
        List<Remedio> lista = new ArrayList<>();
        int ic = android.R.drawable.ic_menu_help;

        lista.add(new Remedio("Dipirona", "Analgésico", "Alivia dores e febre", ic,
                "20 comprimidos", "R$ 8,90",
                "Alergia à dipirona; problemas na medula óssea.",
                "Em caso de dor ou febre, a cada 6 horas."));

        lista.add(new Remedio("Amoxicilina", "Antibiótico", "Combate infecções bacterianas", ic,
                "21 cápsulas", "R$ 24,50",
                "Alergia a penicilinas.",
                "A cada 8 horas, conforme prescrição médica."));

        lista.add(new Remedio("Vitamina C", "Suplemento", "Fortalece o sistema imunológico", ic,
                "30 comprimidos", "R$ 15,00",
                "Cálculos renais; excesso de ferro no sangue.",
                "1 comprimido ao dia, de preferência pela manhã."));

        lista.add(new Remedio("Omeprazol", "Antiácido", "Protege o estômago", ic,
                "28 cápsulas", "R$ 12,30",
                "Alergia ao omeprazol.",
                "1 cápsula em jejum, 30 min antes do café."));

        lista.add(new Remedio("Paracetamol", "Analgésico", "Indicado para alívio de dores leves e febre", ic,
                "20 comprimidos", "R$ 6,50",
                "Doença grave do fígado.",
                "A cada 6 a 8 horas, em caso de dor ou febre."));

        lista.add(new Remedio("Ibuprofeno", "Anti-inflamatório", "Reduz inflamações, dores musculares e febre", ic,
                "20 comprimidos", "R$ 11,90",
                "Úlcera; problemas renais; último trimestre da gravidez.",
                "A cada 8 horas, preferencialmente após as refeições."));

        lista.add(new Remedio("Losartana", "Anti-hipertensivo", "Ajuda no controle da pressão arterial", ic,
                "30 comprimidos", "R$ 9,80",
                "Gravidez; alergia à losartana.",
                "1 comprimido ao dia, sempre no mesmo horário."));

        lista.add(new Remedio("Metformina", "Antidiabético", "Auxilia no controle do açúcar no sangue", ic,
                "30 comprimidos", "R$ 10,40",
                "Problemas renais graves.",
                "Junto às refeições, conforme prescrição."));

        lista.add(new Remedio("Simvastatina", "Hipolipemiante", "Utilizado para a redução do colesterol ruim", ic,
                "30 comprimidos", "R$ 13,70",
                "Doença ativa do fígado; gravidez.",
                "1 comprimido à noite."));

        lista.add(new Remedio("Loratadina", "Anti-histamínico", "Alivia sintomas de alergias e rinite", ic,
                "12 comprimidos", "R$ 7,20",
                "Alergia à loratadina.",
                "1 comprimido ao dia."));

        lista.add(new Remedio("Fluoxetina", "Antidepressivo", "Auxilia no tratamento de ansiedade e depressão", ic,
                "30 cápsulas", "R$ 18,00",
                "Uso com outros antidepressivos (IMAO).",
                "Pela manhã, conforme orientação médica."));

        lista.add(new Remedio("Buscopan", "Antiespasmódico", "Alivia cólicas estomacais e abdominais", ic,
                "20 comprimidos", "R$ 14,60",
                "Glaucoma; obstrução intestinal.",
                "Até 3 vezes ao dia, em caso de cólica."));

        lista.add(new Remedio("Salbutamol", "Broncodilatador", "Facilita a respiração em crises de asma", ic,
                "Spray 200 doses", "R$ 22,90",
                "Problemas cardíacos graves.",
                "Em crises, 1 a 2 jatos conforme prescrição."));

        lista.add(new Remedio("Clonazepam", "Ansiolítico", "Auxilia no controle de distúrbios do sono e ansiedade", ic,
                "30 comprimidos", "R$ 16,30",
                "Glaucoma; problemas respiratórios graves.",
                "À noite, conforme prescrição médica."));

        adapter = new RemedioAdapter(lista, remedio ->
                new android.os.Handler(android.os.Looper.getMainLooper()).postDelayed(
                        () -> mostrarDialogDetalhe(remedio), 80
                )
        );

        recyclerView.setAdapter(adapter);
    }

    private void mostrarDialogDetalhe(Remedio remedio) {
        View dialogView = LayoutInflater.from(requireContext())
                .inflate(R.layout.detalhe_remedio, null);

        TextView nome = dialogView.findViewById(R.id.dialog_nome);
        TextView categoria = dialogView.findViewById(R.id.dialog_categoria);
        TextView descricao = dialogView.findViewById(R.id.dialog_descricao);
        TextView quantidade = dialogView.findViewById(R.id.dialog_quantidade);
        TextView preco = dialogView.findViewById(R.id.dialog_preco);
        TextView quandoTomar = dialogView.findViewById(R.id.dialog_quando_tomar);
        TextView contraindicacoes = dialogView.findViewById(R.id.dialog_contraindicacoes);
        ImageView icone = dialogView.findViewById(R.id.dialog_icone);
        MaterialButton btnFechar = dialogView.findViewById(R.id.dialog_btn_fechar);

        nome.setText(remedio.getNome());
        categoria.setText(remedio.getCategoria());
        descricao.setText(remedio.getDescricao());
        quantidade.setText(remedio.getQuantidade());
        preco.setText(remedio.getPreco());
        quandoTomar.setText(remedio.getQuandoTomar());
        contraindicacoes.setText(remedio.getContraindicacoes());
        icone.setImageResource(remedio.getImagemResId());

        AlertDialog dialog = new AlertDialog.Builder(requireContext())
                .setView(dialogView)
                .create();

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        }

        btnFechar.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }
}