package com.candra.eksplorindonesia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.candra.eksplorindonesia.API.APIRequestData;
import com.candra.eksplorindonesia.API.RetrofitServer;
import com.candra.eksplorindonesia.Model.ModelAllResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddWisataActivity extends AppCompatActivity {

    private ImageView ivBackToWisata, ivAddFotoWisata;

    private EditText etAddNamaWisata, etAddLokasiWisata, etAddMapsWisata, etDeskripsiWisata;

    private Button btnTambahWisata;

    String namaWisata, lokasiWisata, mapsWisata, deskripsiWisata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_wisata);

        ivBackToWisata = findViewById(R.id.iv_back_to_wisata2);
        ivBackToWisata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        ivAddFotoWisata = findViewById(R.id.iv_add_foto_wisata);

        etAddNamaWisata = findViewById(R.id.et_add_nama_wisata);
        etAddLokasiWisata = findViewById(R.id.et_add_lokasi_wisata);
        etAddMapsWisata = findViewById(R.id.et_add_maps_wisata);
        etDeskripsiWisata = findViewById(R.id.et_add_deskripsi_wisata);

        btnTambahWisata = findViewById(R.id.btn_add_wisata);
        btnTambahWisata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                namaWisata = etAddNamaWisata.getText().toString();
                lokasiWisata = etAddLokasiWisata.getText().toString();
                deskripsiWisata = etDeskripsiWisata.getText().toString();
                mapsWisata = etAddMapsWisata.getText().toString();

                if(namaWisata.trim().equals(""))
                {
                    etAddNamaWisata.setError("Nama Wisata tidak boleh kosong !");
                    etAddNamaWisata.requestFocus();
                }
                else if(mapsWisata.trim().equals(""))
                {
                    etAddMapsWisata.setError("Maps Wisata tidak boleh kosong !");
                    etAddMapsWisata.requestFocus();
                }
                else if(lokasiWisata.trim().equals(""))
                {
                    etAddLokasiWisata.setError("Lokasi Wisata tidak boleh kosong !");
                    etAddLokasiWisata.requestFocus();
                }
                else if(deskripsiWisata.trim().equals(""))
                {
                    etDeskripsiWisata.setError("Deskripsi Wisata tidak boleh kosong !");
                    etDeskripsiWisata.requestFocus();
                }
                else
                {
                    AddWisata();
                }
            }
        });




    }

    private void AddWisata()
    {
        APIRequestData ard = RetrofitServer.connectionRetrofit().create(APIRequestData.class);
        Call<ModelAllResponse> prosesAdd = ard.ardCreateDataWisata(namaWisata, lokasiWisata, mapsWisata,"foto", deskripsiWisata);

        prosesAdd.enqueue(new Callback<ModelAllResponse>() {
            @Override
            public void onResponse(Call<ModelAllResponse> call, Response<ModelAllResponse> response) {
                String kode = response.body().getKode();
                String pesan  = response.body().getPesan();

                Toast.makeText(AddWisataActivity.this, "Data Wisata Berhasil disimpan !", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AddWisataActivity.this, DetailWisataActivity.class));
                finish();
            }

            @Override
            public void onFailure(Call<ModelAllResponse> call, Throwable t) {

            }
        });

    }
}