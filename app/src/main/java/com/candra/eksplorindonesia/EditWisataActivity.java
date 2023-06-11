package com.candra.eksplorindonesia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.candra.eksplorindonesia.API.APIRequestData;
import com.candra.eksplorindonesia.API.RetrofitServer;
import com.candra.eksplorindonesia.Model.ModelAllResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditWisataActivity extends AppCompatActivity {

    private ImageView ivBackToDetailWisata;
    private EditText etNamaWisata, etLokasiWisata, etMapsWisata, etDeskripsiWisata;

    private TextView tvIdEditWisata;

    private String tempIdWisata, tempNamaWisata, tempLokasiWisata, tempMapsWisata, tempDeskripsiWisata;

    private Button btnEditWisata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_wisata);

        Intent intent = getIntent();
        tempIdWisata = intent.getStringExtra("varIdWisata");
        tempNamaWisata = intent.getStringExtra("varNamaWisata");
        tempLokasiWisata = intent.getStringExtra("varLokasiWisata");
        tempMapsWisata = intent.getStringExtra("varMapsWisata");
        tempDeskripsiWisata = intent.getStringExtra("varDeskripsiWisata");

        etNamaWisata = findViewById(R.id.et_nama_wisata);
        etLokasiWisata = findViewById(R.id.et_lokasi_wisata);
        etMapsWisata = findViewById(R.id.et_maps_wisata);
        etDeskripsiWisata = findViewById(R.id.et_deskripsi_wisata);
        tvIdEditWisata = findViewById(R.id.tv_id_editwisata);

        tvIdEditWisata.setText(tempIdWisata);
        etNamaWisata.setText(tempNamaWisata);
        etLokasiWisata.setText(tempLokasiWisata);
        etMapsWisata.setText(tempMapsWisata);
        etDeskripsiWisata.setText(tempDeskripsiWisata);

        ivBackToDetailWisata = findViewById(R.id.iv_back_to_detail_wisata);
        ivBackToDetailWisata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EditWisataActivity.this, DetailWisataActivity.class));
//                onBackPressed();
                finish();
            }
        });

        btnEditWisata = findViewById(R.id.btn_edit_wisata);
        btnEditWisata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama, lokasi, maps, deskripsi;
                nama = etNamaWisata.getText().toString();
                lokasi = etLokasiWisata.getText().toString();
                maps = etMapsWisata.getText().toString();
                deskripsi = etDeskripsiWisata.getText().toString();
                if(nama.trim().equals(""))
                {
                    etNamaWisata.setError("Nama Wisata tidak boleh kosong !");
                    etNamaWisata.requestFocus();
                }
                else if(lokasi.trim().equals(""))
                {
                    etLokasiWisata.setError("Lokasi Wisata Tidak boleh Kosong");
                    etLokasiWisata.requestFocus();
                }
                else if(maps.trim().equals(""))
                {
                    etMapsWisata.setError("Maps Wisata Tidak boleh Kosong !");
                    etMapsWisata.requestFocus();
                }
                else if(deskripsi.trim().equals(""))
                {
                    etDeskripsiWisata.setError("Deskripsi Wisata Tidak boleh Kosong !");
                    etDeskripsiWisata.requestFocus();
                }
                else {
                    editData();
                }
            }
        });
    }

    private void editData()
    {
        String idWisata = tempIdWisata;
        APIRequestData ard = RetrofitServer.connectionRetrofit().create(APIRequestData.class);
        Call<ModelAllResponse> edit = ard.ardUpdateDataWisata(tempIdWisata, etNamaWisata.getText().toString(),etLokasiWisata.getText().toString(), etMapsWisata.getText().toString(), "edittext", etDeskripsiWisata.getText().toString());
        edit.enqueue(new Callback<ModelAllResponse>() {
            @Override
            public void onResponse(Call<ModelAllResponse> call, Response<ModelAllResponse> response) {
                String code = response.body().getKode();
                String message = response.body().getPesan();

                Toast.makeText(EditWisataActivity.this, "Data Wisata Berhasil di Update", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(EditWisataActivity.this, DetailWisataActivity.class);

                i.putExtra("xIdWisata", tempIdWisata);
                i.putExtra("xNamaWisata", etNamaWisata.getText().toString());
                i.putExtra("xLokasiWisata", etLokasiWisata.getText().toString());
                i.putExtra("xMapsWisata", etMapsWisata.getText().toString());
                i.putExtra("xDeskripsiWisata", etDeskripsiWisata.getText().toString());
                startActivity(i);
                finish();
            }

            @Override
            public void onFailure(Call<ModelAllResponse> call, Throwable t) {

                Toast.makeText(EditWisataActivity.this, "Data Gagal di Update : " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

}