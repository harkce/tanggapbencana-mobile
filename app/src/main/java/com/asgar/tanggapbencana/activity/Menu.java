package com.asgar.tanggapbencana.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.location.Location;
import android.location.LocationListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.asgar.tanggapbencana.R;
import com.asgar.tanggapbencana.adapter.ListAdapter;
import com.asgar.tanggapbencana.base.BaseActivity;
import com.asgar.tanggapbencana.databinding.ActivityMenuBinding;
import com.asgar.tanggapbencana.model.DataRelawan;
import com.asgar.tanggapbencana.model.ResponseListDao;
import com.asgar.tanggapbencana.sharedPrefs.PrefRelawan;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;

import java.util.ArrayList;
import java.util.List;

public class Menu extends BaseActivity{

    private ActivityMenuBinding binding;
    public ObservableField<Boolean> isFabOpen = new ObservableField<>(false);
    private Animation fab_open, fab_close, rotate_forward, rotate_backward;
    public ListAdapter bAdapter;
    public LinearLayoutManager bLayoutManager;
    private List<ResponseListDao.DataBean> mListDao = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_menu);
        binding.setVm(this);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle("Menu");

        fab_open = AnimationUtils.loadAnimation(this, R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(this,R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(this,R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(this,R.anim.rotate_backward);

        bAdapter = new ListAdapter(mListDao);
        bLayoutManager = new LinearLayoutManager(this);

        initDummy();
    }

    private void initDummy() {
        mListDao.add(new ResponseListDao.DataBean());
        mListDao.add(new ResponseListDao.DataBean());
        mListDao.add(new ResponseListDao.DataBean());
        bAdapter.notifyDataSetChanged();
    }


    public void onClickFeed(View view){
            startActivity(new Intent(this, TambahFeed.class));
    }

    public void onClickUpdate(View view){
            startActivity(new Intent(this, TambahUpdate.class));
    }

    public void onClickFabMenu(View view){
        animateFAB();
    }

    public void animateFAB(){
        if(isFabOpen.get()){
            binding.fabMenu.startAnimation(rotate_backward);
            binding.fabUpdate.startAnimation(fab_close);
            binding.fabFeed.startAnimation(fab_close);
            isFabOpen.set(false);
        } else {
            binding.fabMenu.startAnimation(rotate_forward);
            binding.fabUpdate.startAnimation(fab_open);
            binding.fabFeed.startAnimation(fab_open);
            isFabOpen.set(true);
        }
    }
}
