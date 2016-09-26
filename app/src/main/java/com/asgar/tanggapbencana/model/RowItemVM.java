package com.asgar.tanggapbencana.model;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.asgar.tanggapbencana.R;
import com.asgar.tanggapbencana.databinding.RowItemBinding;

/**
 * Created by irfanandarafifsatrio on 9/26/16.
 */

public class RowItemVM extends RecyclerView.ViewHolder {

    private RowItemBinding binding;

    public RowItemVM(RowItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}
