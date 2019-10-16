package com.app.dilan.rumahsakitbogor;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;


public class DetailActivity extends AppCompatActivity {
    MainActivity check;
    private Context context;
    final String STATE_TITLE = "state_string";
    final String STATE_LIST = "state_list";
    final String STATE_MODE = "state_mode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

//        TextView tJudul = findViewById(R.id.tvJudul);
        TextView tAlamat = findViewById(R.id.tvAlamat);
        ImageView iRs = findViewById(R.id.item_photo);
        Button bLok = findViewById(R.id.bLokasi);
        Button bTelp = findViewById(R.id.bTlp);

        final Intent intent = getIntent();
        String title = intent.getStringExtra("nama");
        getSupportActionBar().setTitle(title);

        //tJudul.setText(intent.getStringExtra("nama"));
        tAlamat.setText(intent.getStringExtra("alamat"));

        Picasso.get().load(intent.getStringExtra("foto")).into(iRs);

        final String lokashi = intent.getStringExtra("lokasi");
        final String telephon = intent.getStringExtra("telepon");

        bLok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(lokashi));
                startActivity(i);
            }
        });

        bTelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + telephon));
                if (ContextCompat.checkSelfPermission(DetailActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    check.checkPerm();
                }
                else{
                    startActivity(intent);
                }
            }
        });
    }
}