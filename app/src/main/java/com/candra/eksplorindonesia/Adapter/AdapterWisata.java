package com.candra.eksplorindonesia.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.candra.eksplorindonesia.DetailWisataActivity;
import com.candra.eksplorindonesia.Model.ModelWisata;
import com.candra.eksplorindonesia.R;

import java.util.List;

public class AdapterWisata extends RecyclerView.Adapter<AdapterWisata.VHWisata> {

    private Context context;
    private List<ModelWisata> listWisata;


    public AdapterWisata(Context context, List<ModelWisata> listWisata) {
        this.context = context;
        this.listWisata = listWisata;
    }

    @NonNull
    @Override
    public VHWisata onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_wisata, parent, false);
        return new VHWisata(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VHWisata holder, int position) {
        ModelWisata mw = listWisata.get(position);

        holder.tvIdWisata.setText(mw.getIdWisata());
        holder.tvNamaWisata.setText(mw.getNamaWisata());
        holder.tvLokasiWisata.setText(mw.getLokasiWisata());
        holder.tvMapsWisata.setText(mw.getMapsWisata());
        holder.tvDeskripsiWisata.setText(mw.getDeskripsiWisata());

    }

    @Override
    public int getItemCount() {
        if(listWisata==null) return 0;
        return listWisata.size();
    }

    public class VHWisata extends RecyclerView.ViewHolder {

        TextView tvIdWisata, tvNamaWisata, tvLokasiWisata, tvMapsWisata, tvDeskripsiWisata;

        Button btnDetailWisata;
        public VHWisata(@NonNull View itemView) {
            super(itemView);

            tvIdWisata = itemView.findViewById(R.id.tv_id_wisata);
            tvNamaWisata = itemView.findViewById(R.id.tv_nama_wisata);
            tvLokasiWisata = itemView.findViewById(R.id.tv_lokasi_wisata);
            tvMapsWisata = itemView.findViewById(R.id.tv_maps_wisata);
            tvDeskripsiWisata = itemView.findViewById(R.id.tv_deskripsi_wisata);

            btnDetailWisata = itemView.findViewById(R.id.btn_detail_wisata);

//            btnDetailWisata.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(context, DetailWisataActivity.class);
//                    intent.putExtra("vIdWisata", tvIdWisata.getText().toString());
//                    intent.putExtra("vNamaWisata", tvNamaWisata.getText().toString());
//                    intent.putExtra("vLokasiWisata", tvLokasiWisata.getText().toString());
//                    intent.putExtra("vMapsWisata", tvMapsWisata.getText().toString());
//                    intent.putExtra("vDeskripsiWisata", tvDeskripsiWisata.getText().toString());
//                    context.startActivity(intent);
//                }
//            });
        }
    }
}
