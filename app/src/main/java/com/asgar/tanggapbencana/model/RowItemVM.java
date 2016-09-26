package com.asgar.tanggapbencana.model;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;

import com.asgar.tanggapbencana.R;
import com.asgar.tanggapbencana.databinding.RowItemBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by irfanandarafifsatrio on 9/26/16.
 */

public class RowItemVM extends RecyclerView.ViewHolder {

    private RowItemBinding binding;
    public ObservableField<String> bLokasi = new ObservableField<>("");
    public ObservableField<String> bTanggal = new ObservableField<>("");
    public ObservableField<String> bDesKorban = new ObservableField<>("");
    public ObservableField<String> bDesKebutuhan = new ObservableField<>("");
    public ObservableField<String> bKeterangan = new ObservableField<>("");
    public ObservableField<String> bRelawan = new ObservableField<>("");

    public RowItemVM(RowItemBinding binding, ResponseListDao.DataBean data) {
        super(binding.getRoot());
        this.binding = binding;

        Date mDate = null;
        String mResultOutputDate = "";

        SimpleDateFormat mInputDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", java.util.Locale.getDefault());
        SimpleDateFormat mOutputDate = new SimpleDateFormat("dd MMM yyyy", java.util.Locale.getDefault());

        try {
            mDate = mInputDate.parse(data.getCreated_at());
            mResultOutputDate = mOutputDate.format(mDate);
            bTanggal.set(mResultOutputDate);
        } catch (ParseException e) {
            Log.e("ERROR DATE ", e.getMessage());
        }

        bLokasi.set(data.getNama_lokasi());
        bDesKorban.set("Deskripsi Korban : "+data.getDeskripsi_korban());
        bDesKebutuhan.set("Deskripsi Kebutuhan : "+data.getDeskripsi_kebutuhan());
        bKeterangan.set("Keterangan : "+data.getKeterangan());
        bRelawan.set("Relawan : "+data.getNama_relawan());
    }
}
