package com.example.nikkoekasaputra.rentalmobil;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class DetailPenyewa extends AppCompatActivity {

    String sNama, sAlamat, sHP, sMerk, sHarga;
    int iLama, iPromo, iTotal;
    double  dTotal;

    protected Cursor cursor;
    DataHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_penyewa);

        dbHelper = new DataHelper(this);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("select * from penyewa, mobil, sewa where penyewa.nama = sewa.nama AND mobil.merk = sewa.merk AND penyewa.nama = '"+getIntent().getStringExtra("nama")+"'", null);
        cursor.moveToFirst();
        if(cursor.getCount()>0){
            cursor.moveToPosition(0);
            sNama = cursor.getString(0).toString();
            sAlamat = cursor.getString(1).toString();
            sHP = cursor.getString(2).toString();
            sMerk = cursor.getString(3).toString();
            sHarga = cursor.getString(4).toString();
            iPromo = cursor.getInt(7);
            iLama = cursor.getInt(8);
            dTotal = cursor.getDouble(9);
        }

        TextView tvNama = (TextView) findViewById(R.id.HNama);
        TextView tvAlamat = (TextView) findViewById(R.id.HAlamat);
        TextView tvHP = (TextView) findViewById(R.id.HTelp);

        TextView tvMerk = (TextView) findViewById(R.id.HMerk);
        TextView tvHarga = (TextView) findViewById(R.id.HHarga);

        TextView tvLama = (TextView) findViewById(R.id.HLamaSewa);
        TextView tvPromo = (TextView) findViewById(R.id.HPromo);
        TextView tvTotal = (TextView) findViewById(R.id.HTotal);

        tvNama.setText("     "+sNama);
        tvAlamat.setText("     "+sAlamat);
        tvHP.setText("     "+sHP);

        tvMerk.setText("     "+sMerk);
        tvHarga.setText("     Rp. "+sHarga);

        tvLama.setText("     "+iLama+" hari");
        tvPromo.setText("     "+iPromo+"%");
        iTotal = (int) dTotal;
        tvTotal.setText("     Rp. "+iTotal);

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