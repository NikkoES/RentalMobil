package com.example.nikkoekasaputra.rentalmobil;

import android.os.Bundle;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class SewaMobil extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText nama, alamat, no_hp, lama;
    RadioGroup promo;
    RadioButton weekday, weekend;
    Button selesai;

    String sNama, sAlamat, sNo, sMerk, sLama;
    double dPromo;
    int iLama, iPromo, iHarga;
    double dTotal;

    private Spinner spinner;
    DataHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sewa);

        dbHelper = new DataHelper(this);

        spinner = (Spinner) findViewById(R.id.spinner);

        selesai = (Button) findViewById(R.id.selesaiHitung);

        nama = (EditText) findViewById(R.id.eTNama);
        alamat = (EditText) findViewById(R.id.eTAlamat);
        no_hp = (EditText) findViewById(R.id.eTHP);
        promo = (RadioGroup) findViewById(R.id.promoGroup);
        weekday = (RadioButton) findViewById(R.id.rbWeekDay);
        weekend = (RadioButton) findViewById(R.id.rbWeekEnd);
        lama = (EditText) findViewById(R.id.eTLamaSewa);

        spinner.setOnItemSelectedListener(this);

        loadSpinnerData();

        selesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sNama = nama.getText().toString();
                sAlamat = alamat.getText().toString();
                sNo = no_hp.getText().toString();
                sLama = lama.getText().toString();

                if(weekday.isChecked()){
                    dPromo = 0.1;
                }
                else if(weekend.isChecked()){
                    dPromo = 0.25;
                }

                if(sMerk.equals("Avanza")){
                    iHarga = 400000;
                }
                else if(sMerk.equals("APV")){
                    iHarga = 400000;
                }
                else if(sMerk.equals("Innova")){
                    iHarga = 500000;
                }
                else if(sMerk.equals("Pregio")){
                    iHarga = 550000;
                }
                else if(sMerk.equals("Elf")){
                    iHarga = 700000;
                }

                iLama = Integer.parseInt(sLama);
                iPromo = (int) (dPromo * 100);
                dTotal = (iHarga * iLama) - (iHarga * iLama * dPromo);


                SQLiteDatabase dbH = dbHelper.getWritableDatabase();
                dbH.execSQL("INSERT INTO penyewa (nama, alamat, no_hp) VALUES ('" +
                        sNama + "','" +
                        sAlamat + "','" +
                        sNo + "');");
                dbH.execSQL("INSERT INTO sewa (merk, nama, promo, lama, total) VALUES ('" +
                        sMerk + "','" +
                        sNama + "','" +
                        iPromo + "','" +
                        iLama+ "','" +
                        dTotal+ "');");
                Penyewa.m.RefreshList();
                finish();

            }
        });

        ImageButton back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });

    }

    private void loadSpinnerData(){
        DataHelper db = new DataHelper(getAppl  icationContext());
        List<String> categories = db.getAllCategories();

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        sMerk = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}