package com.asgar.tanggapbencana.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.location.Location;
import android.location.LocationListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.asgar.tanggapbencana.R;
import com.asgar.tanggapbencana.base.BaseActivity;
import com.asgar.tanggapbencana.databinding.ActivityMenuBinding;
import com.asgar.tanggapbencana.model.DataRelawan;
import com.asgar.tanggapbencana.sharedPrefs.PrefRelawan;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;

public class Menu extends BaseActivity{

    private ActivityMenuBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_menu);
        binding.setMenu(this);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle("Menu");
    }


    public void onClickFeed(View view){
        if (view.getId() == R.id.btn_tmb_feed){
            startActivity( new Intent(this,TambahFeed.class));
        }
    }

    public void onClickUpdate(View view){
        if (view.getId() == R.id.btn_tmb_update){
            startActivity(new Intent(this, TambahUpdate.class));
        }
    }
}
