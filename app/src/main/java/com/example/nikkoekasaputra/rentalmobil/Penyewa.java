package com.example.nikkoekasaputra.rentalmobil;

import android.content.DialogInterface;
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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.app.AlertDialog;

public class Penyewa extends AppCompatActivity{
    String[] daftar;
    int[] id;
    ListView ListView1;
    Menu menu;
    protected Cursor cursor;
    DataHelper dbcenter;
    public static Penyewa m;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penyewa);

        Button tambah = (Button) findViewById(R.id.tambahPenyewa);

        tambah.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent p = new Intent(Penyewa.this, SewaMobil.class);
                startActivity(p);
            }
        });

        m = this;
        dbcenter = new DataHelper(this);
        RefreshList();

        ImageButton back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });

    }

    public void RefreshList(){
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM penyewa", null);
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
                final CharSequence[] dialogitem = {"Lihat Data", "Hapus Data"};
                AlertDialog.Builder builder = new AlertDialog.Builder(Penyewa.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int item){
                        switch(item){
                            case 0 : {
                                Intent i = new Intent(Penyewa.this, DetailPenyewa.class);
                                i.putExtra("nama", selection);
                                startActivity(i);
                                break;
                            }
                            case 1 : {
                                SQLiteDatabase db = dbcenter.getWritableDatabase();
                                db.execSQL("DELETE FROM penyewa where nama = '"+selection+"'");
                                db.execSQL("DELETE FROM sewa where nama = '"+selection+"'");
                                RefreshList();
                                break;
                            }
                        }
                    }
                });
                builder.create().show();
            }
        });
        ((ArrayAdapter) ListView1.getAdapter()).notifyDataSetInvalidated();

    }
}
