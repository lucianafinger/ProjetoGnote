package com.example.projetognote.adapter;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetognote.R;
import com.example.projetognote.model.Registro;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class AdapterRegistros extends RecyclerView.Adapter<AdapterRegistros.MyViewHolder> {

    private List<Registro> listaRegistros;
    private OnRegistroListener registroListener;

    private Registro registro;
    DateTimeFormatter hora = DateTimeFormatter.ofPattern("HH:mm:ss");
    private Bundle bundleDadosRegistro;


    public AdapterRegistros(List<Registro> listaRegistros, OnRegistroListener onRegistroListener) {
        this.listaRegistros = listaRegistros;
        this.registroListener = onRegistroListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_lista, parent, false);
        return new MyViewHolder(itemLista, registroListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //recupera os dados
        registro = listaRegistros.get(position);
        if (registro != null) {
            holder.hora.setText(registro.getHoraRegistro().format(hora));
            holder.glicose.setText(String.valueOf(registro.getRegistroGlicose()));
            holder.insulinaFixa.setText(String.valueOf(registro.getInsulinaRefeicao()));
            holder.insulinaCorrecao.setText(String.valueOf(registro.getInsulinaCorrecao()));

            //clique
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Registro selecionado = listaRegistros.get(position);
//
//                    bundleDadosRegistro = new Bundle();
//                    bundleDadosRegistro.putSerializable("registro", selecionado);
//                    Navigation.findNavController(v).navigate(R.id.home_to_editar_registro, bundleDadosRegistro);
//                }
//            });
        }

    }

    // quantidade de itens que vai retornar do ViewHolder
    @Override
    public int getItemCount() {
        if (listaRegistros == null) {
            System.out.println("não foram encontrados registros.");
        } else {
            System.out.println("retorna lista");
            return this.listaRegistros.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView etiqueta;
        TextView hora, glicose, insulinaFixa, insulinaCorrecao;
        OnRegistroListener onRegistroListener;

        public MyViewHolder(@NonNull View itemView, OnRegistroListener onRegistroListener) {
            super(itemView);

            hora = itemView.findViewById(R.id.tv_hora);
            glicose = itemView.findViewById(R.id.tv_glicose);
            insulinaFixa = itemView.findViewById(R.id.tv_insulina_refeicao);
            insulinaCorrecao = itemView.findViewById(R.id.tv_insulina_correcao);
            etiqueta = itemView.findViewById(R.id.iv_etiqueta);

            this.onRegistroListener = onRegistroListener;
            itemView.setOnClickListener(this);
            if (registro != null) {
//                 confiigurar imagens etiqueta
//                 1 hipo; 2 bom; 3 hiper
                if (registro.getEtiqueta().equals(String.valueOf(2))) {
                    // bom
                    etiqueta.setImageResource(R.drawable.muito_boa);
                } else if (registro.getEtiqueta().equals(String.valueOf(1))) {
                    // hipo
                    etiqueta.setImageResource(R.drawable.hipo);
                } else if (registro.getEtiqueta().equals(String.valueOf(3))) {
                    // hiper
                    etiqueta.setImageResource(R.drawable.hiper);
                } else {
                    // normal - imagem padrão
                    etiqueta.setImageResource(R.drawable.boa_normal);
                }
            }


        }

        @Override
        public void onClick(View v) {
            onRegistroListener.onRegistroClick(getAdapterPosition());
        }
    }

    public interface OnRegistroListener {
        void onRegistroClick(int position);
    }

}
