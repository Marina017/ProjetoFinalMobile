package com.example.projetofinal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.card.MaterialCardView;

public class DetalhesFragment extends Fragment {

    public DetalhesFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detalhes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView nome = view.findViewById(R.id.text_detalhe_nome);
        TextView descricao = view.findViewById(R.id.text_descricao);
        ImageView icone = view.findViewById(R.id.image_detalhe_icone);
        MaterialCardView cardDetalhes = view.findViewById(R.id.card_detalhes);
        MaterialCardView cardIcone = view.findViewById(R.id.card_icone);

        Remedio remedio = null;
        if (getArguments() != null) {
            remedio = getArguments().getParcelable("remedio");
        }

        if (remedio != null) {
            nome.setText(remedio.getNome());
            descricao.setText(remedio.getCategoria() + " — " + remedio.getDescricao());
            icone.setImageResource(remedio.getImagemResId());
        }

        cardIcone.setOnClickListener(v ->
                cardDetalhes.setVisibility(
                        cardDetalhes.getVisibility() == View.GONE ? View.VISIBLE : View.GONE
                )
        );
    }
}