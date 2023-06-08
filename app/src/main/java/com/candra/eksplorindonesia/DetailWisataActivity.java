package com.candra.eksplorindonesia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class DetailWisataActivity extends AppCompatActivity {

    ImageView ivBackToWisata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_wisata);

        ivBackToWisata = findViewById(R.id.iv_back_to_wisata);
        ivBackToWisata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Back ke sebelumnya
                onBackPressed();
            }
        });
    }
}