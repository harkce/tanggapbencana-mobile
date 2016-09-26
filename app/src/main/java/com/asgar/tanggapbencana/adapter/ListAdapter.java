package com.asgar.tanggapbencana.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.asgar.tanggapbencana.R;
import com.asgar.tanggapbencana.databinding.RowFooterBinding;
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
        if (viewType == VIEW_TYPE_LOADING){
            RowFooterBinding binding = DataBindingUtil.inflate(LayoutInflater.
                    from(parent.getContext()), R.layout.row_footer, parent, false);
            return new FooterBindingHolder(binding);
        }else{
            RowItemBinding binding = DataBindingUtil.inflate(LayoutInflater.
                    from(parent.getContext()), R.layout.row_item, parent, false);
            return new RowBindingHolder(binding);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isLoadMoreAvailable && position == mList.size()) {
            return VIEW_TYPE_LOADING;
        } else {
            return VIEW_TYPE_ITEM;
        }
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        RowFooterVM vm = new RowFooterVM();
        if (holder instanceof FooterBindingHolder) {
            //footer
            ((FooterBindingHolder) holder).getBinding().setVm(vm);
            vm.isVisible.set(true);
        } else {
            //item
            ((RowBindingHolder)holder).getBinding().setVm(new RowItemVM((
                    (RowBindingHolder)holder).getBinding(), mList.get(position)));
            vm.isVisible.set(false);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size() + (isLoadMoreAvailable ? 1 : 0);
    }

    public static class BindingHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;

        public BindingHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public ViewDataBinding getBinding() {
            return this.binding;
        }
    }

    public static class FooterBindingHolder extends BindingHolder {

        private RowFooterBinding binding;

        public FooterBindingHolder(RowFooterBinding binding) {
            super(binding);
            this.binding = binding;
        }

        public RowFooterBinding getBinding() {
            return this.binding;
        }
    }

    public static class RowBindingHolder extends BindingHolder {

        private RowItemBinding binding;

        public RowBindingHolder(RowItemBinding binding) {
            super(binding);
            this.binding = binding;
        }

        public RowItemBinding getBinding() {
            return this.binding;
        }
    }
}
