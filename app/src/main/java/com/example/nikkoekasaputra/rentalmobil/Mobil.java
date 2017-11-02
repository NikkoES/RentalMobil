package com.example.nikkoekasaputra.rentalmobil;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

public class Mobil extends AppCompatActivity{
    String[] daftar;
    ListView ListView1;
    Menu menu;
    protected Cursor cursor;
    DataHelper dbcenter;
    public static Mobil m;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobil);

        m = this;
        dbcenter = new DataHelper(this);
        RefreshList();

    }

    public void RefreshList(){
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM mobil", null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for(int i=0;i<cursor.getCount();i++){
            cursor.moveToPosition(i);
            daftar[i] = cursor.getString(0).toString();
        }
        ListView1 = (ListView) findViewById(R.id.listView1);
        ListView1.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar));
        ListView1.setSelected(true);
        ListView1.setOnItemClickListener(new OnItemClickListener(){

            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3){
                final String selection = daftar[arg2];
                Intent i = new Intent(Mobil.this, DetailMobil.class);
                i.putExtra("merk", selection);
                startActivity(i);
            }
        });
        ((ArrayAdapter) ListView1.getAdapter()).notifyDataSetInvalidated();

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