package com.asgar.tanggapbencana.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;

import com.asgar.tanggapbencana.R;
import com.asgar.tanggapbencana.base.BaseActivity;
import com.asgar.tanggapbencana.databinding.ActivityTambahUpdateBinding;

public class TambahUpdate extends BaseActivity {

    private ActivityTambahUpdateBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_tambah_update);
        binding.setTambahUpdate(this);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle("Tambah Update");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.submit_update, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.submit_update){

        }
        return super.onOptionsItemSelected(item);
    }
}
