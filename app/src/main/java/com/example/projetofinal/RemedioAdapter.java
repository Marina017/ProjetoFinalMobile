package com.example.projetofinal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class RemedioAdapter extends RecyclerView.Adapter<RemedioAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Remedio remedio);
    }

    private List<Remedio> lista;
    private OnItemClickListener listener;

    public RemedioAdapter(List<Remedio> lista, OnItemClickListener listener) {
        this.lista = lista;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_remedio, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Remedio remedio = lista.get(position);
        holder.txtNome.setText(remedio.getNome());
        holder.txtCategoria.setText(remedio.getCategoria());
        holder.txtDescricao.setText(remedio.getDescricao());
        holder.imgRemedio.setImageResource(remedio.getImagem());
        holder.itemView.setOnClickListener(v -> listener.onItemClick(remedio));
    }

    @Override
    public int getItemCount() { return lista.size(); }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgRemedio;
        TextView txtNome, txtCategoria, txtDescricao;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgRemedio = itemView.findViewById(R.id.imgRemedio);
            txtNome = itemView.findViewById(R.id.txtNome);
            txtCategoria = itemView.findViewById(R.id.txtCategoria);
            txtDescricao = itemView.findViewById(R.id.txtDescricao);
        }
    }
}