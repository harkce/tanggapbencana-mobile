package com.asgar.tanggapbencana.activity;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
    private Drawable icon;
    private ProgressDialog dialog;

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
            boolean isFill;
            if (!binding.tuNamaLokasi.getText().toString().equals("")){
                isFill = true;
            }else {
                isFill = false;
            }

            if (isFill){
                dialog = new ProgressDialog(this);
                dialog.setCancelable(false);
                dialog.setMessage("Mohon tunggu sebentar...");
                dialog.show();

                DataRelawan dataRelawan = PrefRelawan.getRelawan(this);

                Log.i("Update", binding.tuNamaLokasi.getText().toString()+" "+
                        binding.tuDeskripsiKorban.getText().toString()+" "+
                        binding.tuDeskripsiKebutuhan.getText().toString()+" "+
                        binding.tuKeterangan.getText().toString()+" "+
                        dataRelawan.getRelawan().getNama()+" "+
                        dataRelawan.getRelawan().getKontak());

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(ApiInterface.url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                ApiInterface post = retrofit.create(ApiInterface.class);
                Call<ResponseBody> call = post.uploadData(binding.tuNamaLokasi.getText().toString(),
                        null,
                        null,
                        binding.tuDeskripsiKorban.getText().toString(),
                        binding.tuDeskripsiKebutuhan.getText().toString(),
                        binding.tuKeterangan.getText().toString(),
                        dataRelawan.getRelawan().getNama(),
                        dataRelawan.getRelawan().getKontak());
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        dialog.dismiss();
                        Toast.makeText(TambahUpdate.this,"Berhasil Menambahkan",Toast.LENGTH_LONG).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        dialog.dismiss();
                        Toast.makeText(TambahUpdate.this,"Gagal Menambahkan",Toast.LENGTH_LONG).show();
                    }
                });
            }else{
                icon = ContextCompat.getDrawable(this, R.drawable.ic_error_red_500_18dp);
                icon.setBounds(new Rect(0, 0, icon.getIntrinsicWidth(), icon.getIntrinsicHeight()));
                binding.tuNamaLokasi.setCompoundDrawables(null,null,icon,null);
                binding.tuNamaLokasi.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        binding.tuNamaLokasi.setCompoundDrawables(null,null,null,null);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if (binding.tuNamaLokasi.getText().toString().equals("")){
                            binding.tuNamaLokasi.setCompoundDrawables(null,null,icon,null);
                        }
                    }
                });
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
