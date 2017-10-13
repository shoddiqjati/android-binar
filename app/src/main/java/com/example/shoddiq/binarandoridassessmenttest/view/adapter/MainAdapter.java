package com.example.shoddiq.binarandoridassessmenttest.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shoddiq.binarandoridassessmenttest.R;
import com.example.shoddiq.binarandoridassessmenttest.model.Stuff;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyHolder> {

    Context mContext;
    List<Stuff> stuffList;

    public MainAdapter(Context mContext, List<Stuff> stuffList) {
        this.mContext = mContext;
        this.stuffList = stuffList;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(mContext).inflate(R.layout.content_main_holder, parent, false);
        return new MyHolder(mView);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.tvName.setText(stuffList.get(position).getName());
        holder.tvDate.setText(stuffList.get(position).getDate());
        holder.tvSupplier.setText(stuffList.get(position).getSupplier());
        holder.tvIcon.setText(stuffList.get(position).getName().substring(0, 1));
    }

    @Override
    public int getItemCount() {
        return stuffList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.content_name_text_view)
        TextView tvName;
        @BindView(R.id.content_date_text_view)
        TextView tvDate;
        @BindView(R.id.content_supplier_text_view)
        TextView tvSupplier;
        @BindView(R.id.content_icon_text_view)
        TextView tvIcon;

        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
