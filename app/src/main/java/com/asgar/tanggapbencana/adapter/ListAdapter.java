package com.asgar.tanggapbencana.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.asgar.tanggapbencana.R;
import com.asgar.tanggapbencana.databinding.RowItemBinding;
import com.asgar.tanggapbencana.model.ResponseListDao;
import com.asgar.tanggapbencana.model.RowItemVM;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by irfanandarafifsatrio on 9/26/16.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.BindingHolder> {

    public final static int VIEW_TYPE_ITEM = 0;
    public final static int VIEW_TYPE_LOADING = 1;
    List<ResponseListDao.DataBean> mList = new ArrayList<>();
    public boolean isLoadMoreAvailable =false;

    public ListAdapter(List<ResponseListDao.DataBean> mList) {
        this.mList = mList;
    }

    @Override
    public ListAdapter.BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BindingHolder(RowItemBinding.inflate(LayoutInflater.from(parent.getContext())));
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        holder.getBinding().setVm(new RowItemVM(holder.getBinding()));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class BindingHolder extends RecyclerView.ViewHolder {
        private RowItemBinding binding;

        public BindingHolder(RowItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public RowItemBinding getBinding() {
            return this.binding;
        }
    }
}
