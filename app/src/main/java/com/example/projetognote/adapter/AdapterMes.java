package com.example.projetognote.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetognote.R;
import com.example.projetognote.model.Registro;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class AdapterMes extends RecyclerView.Adapter<AdapterMes.MyViewHolder> {

    private List<Registro> listaRegistrosMes;
    private OnRegistroListenerMes onRegistroListenerMes;

    private Registro registro;
    DateTimeFormatter hora = DateTimeFormatter.ofPattern("HH:mm:ss");

    public AdapterMes(List<Registro> listaRegistrosMes, AdapterMes.OnRegistroListenerMes onRegistroListenerMes) {
        this.listaRegistrosMes = listaRegistrosMes;
        this.onRegistroListenerMes = onRegistroListenerMes;
    }

    @NonNull
    @Override
    public AdapterMes.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_mes, parent, false);
        return new MyViewHolder(itemLista, onRegistroListenerMes);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMes.MyViewHolder holder, int position) {
        //recupera os dados
        registro = listaRegistrosMes.get(position);
        if (registro != null) {
            holder.dia.setText(registro.getDataRegistro().toString());
            holder.hora.setText(registro.getHoraRegistro().format(hora));
            holder.glicose.setText(String.valueOf(registro.getRegistroGlicose()));
            holder.insulinaFixa.setText(String.valueOf(registro.getInsulinaRefeicao()));
            holder.insulinaCorrecao.setText(String.valueOf(registro.getInsulinaCorrecao()));

            if (registro.getEtiqueta().equals("2")) {
                holder.etiqueta.setImageResource(R.drawable.muito_boa);
            } else if (registro.getEtiqueta().equals("1")) {
                holder.etiqueta.setImageResource(R.drawable.hipo);
            } else if (registro.getEtiqueta().equals("3")) {
                holder.etiqueta.setImageResource(R.drawable.hiper);
            } else {
                holder.etiqueta.setImageResource(R.drawable.boa);
            }

        }

    }

    @Override
    public int getItemCount() {
        if (listaRegistrosMes == null) {
            System.out.println("não foram encontrados registros.");
        } else {
            System.out.println("retorna lista");
            return this.listaRegistrosMes.size();
        }
        return 0;
    }

    public interface OnRegistroListenerMes {
        void onRegistroClick(int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView etiqueta;
        TextView dia, hora, glicose, insulinaFixa, insulinaCorrecao;
        OnRegistroListenerMes onRegistroListenerMes;

        public MyViewHolder(View itemView, OnRegistroListenerMes onRegistroListenerMes) {
            super(itemView);

            dia = itemView.findViewById(R.id.tv_dia_mes);
            hora = itemView.findViewById(R.id.tv_hora_mes);
            glicose = itemView.findViewById(R.id.tv_glicose_mes);
            insulinaFixa = itemView.findViewById(R.id.tv_insulina_refeicao_mes);
            insulinaCorrecao = itemView.findViewById(R.id.tv_insulina_correcao_mes);
            etiqueta = itemView.findViewById(R.id.iv_etiqueta_mes);

            this.onRegistroListenerMes = onRegistroListenerMes;
            this.itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onRegistroListenerMes.onRegistroClick(getAdapterPosition());
        }
    }

}
