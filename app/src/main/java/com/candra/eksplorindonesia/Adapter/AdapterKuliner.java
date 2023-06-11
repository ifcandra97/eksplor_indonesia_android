package com.candra.eksplorindonesia.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.candra.eksplorindonesia.DetailKulinerActivity;
import com.candra.eksplorindonesia.DetailWisataActivity;
import com.candra.eksplorindonesia.Model.ModelKuliner;
import com.candra.eksplorindonesia.R;

import java.util.List;

public class AdapterKuliner extends RecyclerView.Adapter<AdapterKuliner.VHKuliner> {

    private Context context;
    private List<ModelKuliner> listKuliner;

    public AdapterKuliner(Context context, List<ModelKuliner> listKuliner) {
        this.context = context;
        this.listKuliner = listKuliner;
    }

    @NonNull
    @Override
    public AdapterKuliner.VHKuliner onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_kuliner, parent, false);
        return new VHKuliner(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterKuliner.VHKuliner holder, int position) {
        ModelKuliner mk = listKuliner.get(position);
        holder.tvIdKuliner.setText(mk.getIdKuliner());
        holder.tvNamaKuliner.setText(mk.getNamaKuliner());
        holder.tvAsalKuliner.setText(mk.getAsalKuliner());
        holder.tvDeskripsiKuliner.setText(mk.getDeskripsiKuliner());
    }

    @Override
    public int getItemCount() {
        if(listKuliner==null) return 0;
        return listKuliner.size();
    }

    public class VHKuliner extends RecyclerView.ViewHolder
    {
        TextView tvIdKuliner, tvNamaKuliner, tvAsalKuliner, tvDeskripsiKuliner;
        Button btnDetailKuliner;
        public VHKuliner(@NonNull View itemView) {
            super(itemView);

            tvIdKuliner = itemView.findViewById(R.id.tv_id_kuliner);
            tvNamaKuliner = itemView.findViewById(R.id.tv_nama_kuliner);
            tvAsalKuliner = itemView.findViewById(R.id.tv_asal_kuliner);
            tvDeskripsiKuliner = itemView.findViewById(R.id.tv_deskripsi_kuliner);

            btnDetailKuliner = itemView.findViewById(R.id.btn_detail_kuliner);
            btnDetailKuliner.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DetailKulinerActivity.class);
                    intent.putExtra("xIdKuliner", tvIdKuliner.getText().toString());
                    intent.putExtra("xNamaKuliner", tvNamaKuliner.getText().toString());
                    intent.putExtra("xAsalKuliner", tvAsalKuliner.getText().toString());
                    intent.putExtra("xDeskripsiKuliner", tvDeskripsiKuliner.getText().toString());

                    context.startActivity(intent);
                }
            });

        }
    }
}
