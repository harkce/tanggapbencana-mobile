package com.asgar.tanggapbencana.model;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.asgar.tanggapbencana.R;
import com.asgar.tanggapbencana.databinding.RowItemBinding;

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
        bLokasi.set(data.getNama_lokasi());
        bTanggal.set(data.getCreated_at());
        bDesKorban.set(data.getDeskripsi_korban());
        bDesKebutuhan.set(data.getDeskripsi_kebutuhan());
        bKeterangan.set(data.getKeterangan());
        bRelawan.set(data.getNama_relawan());
    }
}
