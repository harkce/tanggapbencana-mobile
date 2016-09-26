package com.asgar.tanggapbencana.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.asgar.tanggapbencana.model.DataRelawan;
import com.asgar.tanggapbencana.sharedPrefs.PrefRelawan;

public class Start extends AppCompatActivity {

    private boolean isLogin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        DataRelawan dataRelawan = PrefRelawan.getRelawan(this);
        Intent intent;
        if (dataRelawan == null){
            intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }
        if (dataRelawan != null){
            intent = new Intent(this,Menu.class);
            startActivity(intent);
        }
        finish();
    }
}
