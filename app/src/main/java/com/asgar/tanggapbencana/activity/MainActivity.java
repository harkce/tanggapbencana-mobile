package com.asgar.tanggapbencana.activity;

import android.content.Intent;
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

import com.asgar.tanggapbencana.R;
import com.asgar.tanggapbencana.base.BaseActivity;
import com.asgar.tanggapbencana.databinding.ActivityMainBinding;
import com.asgar.tanggapbencana.model.DataRelawan;
import com.asgar.tanggapbencana.model.Relawan;
import com.asgar.tanggapbencana.sharedPrefs.PrefRelawan;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;
    private Drawable icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.setRegis(this);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle("Login");

    }

    public void submit(View view){
        if (view.getId() == R.id.btn_submit){
            boolean isFill;
            if (!binding.namaRelawan.getText().toString().equals("") && !binding.kontakRelawan.getText().toString().equals("")){
                isFill = true;
            }else {
                isFill = false;
            }

            if (isFill){
                Relawan relawan = new Relawan(binding.namaRelawan.getText().toString(),binding.kontakRelawan.getText().toString());
                DataRelawan  dataRelawan = PrefRelawan.getRelawan(this);
                if (dataRelawan == null){
                    dataRelawan = new DataRelawan();
                    dataRelawan.setRelawan(relawan);
                    PrefRelawan.setRelawan(dataRelawan,this);
                }
                startActivity(new Intent(this,Menu.class));
                finish();
            }else {

                icon = ContextCompat.getDrawable(this, R.drawable.ic_error_red_500_18dp);
                icon.setBounds(new Rect(0, 0, icon.getIntrinsicWidth(), icon.getIntrinsicHeight()));

                if (binding.namaRelawan.getText().toString().equals("")){
                    binding.namaRelawan.setCompoundDrawables(null,null,icon,null);
                    binding.namaRelawan.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            binding.namaRelawan.setCompoundDrawables(null,null,null,null);
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            if (binding.namaRelawan.getText().toString().equals("")){
                                binding.namaRelawan.setCompoundDrawables(null,null, icon, null);
                            }
                        }
                    });
                }

                if (binding.kontakRelawan.getText().toString().equals("")){
                    binding.kontakRelawan.setCompoundDrawables(null,null,icon,null);
                    binding.kontakRelawan.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            binding.kontakRelawan.setCompoundDrawables(null,null,null,null);
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            if (binding.kontakRelawan.getText().toString().equals("")){
                                binding.kontakRelawan.setCompoundDrawables(null,null,icon,null);
                            }
                        }
                    });
                }
            }
        }
    }
}
