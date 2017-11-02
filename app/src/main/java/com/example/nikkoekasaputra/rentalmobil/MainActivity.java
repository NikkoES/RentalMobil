package com.example.nikkoekasaputra.rentalmobil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button informasi = (Button) findViewById(R.id.button1);
        Button sewa = (Button) findViewById(R.id.button2);
        Button about = (Button) findViewById(R.id.button3);

        informasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Mobil.class);
                startActivity(i);
            }
        });

        sewa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(MainActivity.this, Penyewa.class);
                startActivity(p);
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this, About.class);
                startActivity(a);
            }
        });
    }
}
