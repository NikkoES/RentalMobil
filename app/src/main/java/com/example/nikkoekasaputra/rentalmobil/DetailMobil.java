package com.example.nikkoekasaputra.rentalmobil;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailMobil extends AppCompatActivity {


    String sMerk, sHarga, sGambar;

    protected Cursor cursor;
    DataHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_mobil);

        Bundle terima = getIntent().getExtras();

        dbHelper = new DataHelper(this);
        Intent intent = getIntent();

        String merk = terima.getString("merk");

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("select * from mobil where merk = '"+merk+"'", null);
        cursor.moveToFirst();
        if(cursor.getCount()>0){
            sMerk = cursor.getString(0).toString();
            sHarga = cursor.getString(1).toString();
        }

        if(sMerk.equals("Avanza")){
            sGambar = "avanza";
        }
        else if(sMerk.equals("APV")){
            sGambar = "apv";
        }
        else if(sMerk.equals("Innova")){
            sGambar = "innova";
        }
        else if(sMerk.equals("Pregio")){
            sGambar = "pregio";
        }
        else if(sMerk.equals("Elf")){
            sGambar = "elf";
        }

        ImageView ivGambar = (ImageView) findViewById(R.id.ivMobil);
        TextView tvMerk = (TextView) findViewById(R.id.JMobil);
        TextView tvHarga = (TextView) findViewById(R.id.JHarga);

        tvMerk.setText(sMerk);
        ivGambar.setImageResource(getResources().getIdentifier(sGambar,"drawable",getPackageName()));
        tvHarga.setText("Rp. "+sHarga);

        ImageButton back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });
    }
}
