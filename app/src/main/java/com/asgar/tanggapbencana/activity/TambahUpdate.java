package com.asgar.tanggapbencana.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.Toast;

import com.asgar.tanggapbencana.R;
import com.asgar.tanggapbencana.api.ApiInterface;
import com.asgar.tanggapbencana.base.BaseActivity;
import com.asgar.tanggapbencana.databinding.ActivityTambahUpdateBinding;
import com.asgar.tanggapbencana.model.DataRelawan;
import com.asgar.tanggapbencana.sharedPrefs.PrefRelawan;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

            DataRelawan dataRelawan = PrefRelawan.getRelawan(this);

            Log.i("Update", binding.tuNamaLokasi.getText().toString()+" "+
                    binding.tuDeskripsiKorban.getText().toString()+" "+
                    binding.tuDeskripsiKebutuhan.getText().toString()+" "+
                    binding.tuKeterangan.getText().toString()+" "+
                    dataRelawan.getRelawan().getNama()+" "+
                    dataRelawan.getRelawan().getKontak());

            /*Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://tanggapbencana.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            ApiInterface post = retrofit.create(ApiInterface.class);
            Call<ResponseBody> call = post.tmbFeed(binding.tuNamaLokasi.getText().toString(),
                    binding.tuDeskripsiKorban.getText().toString(),
                    binding.tuDeskripsiKebutuhan.getText().toString(),
                    binding.tuKeterangan.getText().toString(),
                    dataRelawan.getRelawan().getNama(),
                    dataRelawan.getRelawan().getKontak());
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    Toast.makeText(TambahFeed.this,"Berhasil Menambahkan",Toast.LENGTH_LONG).show();
                    finish();
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(TambahFeed.this,"Gagal Menambahkan",Toast.LENGTH_LONG).show();
                }
            });*/
        }
        return super.onOptionsItemSelected(item);
    }
}
