package com.example.shoddiq.binarandoridassessmenttest.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shoddiq.binarandoridassessmenttest.R;
import com.example.shoddiq.binarandoridassessmenttest.model.Stuff;
import com.example.shoddiq.binarandoridassessmenttest.utils.Constants;
import com.example.shoddiq.binarandoridassessmenttest.view.DetailsActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyHolder> implements Filterable {

    Context mContext;
    List<Stuff> stuffList;
    List<Stuff> mFilteredList;
    Bundle bundle;

    public MainAdapter(Context mContext, List<Stuff> stuffList) {
        this.mContext = mContext;
        this.stuffList = stuffList;
        this.bundle = new Bundle();
        this.mFilteredList = stuffList;
        Collections.reverse(this.mFilteredList);
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(mContext).inflate(R.layout.content_main_holder, parent, false);
        return new MyHolder(mView);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
        holder.tvName.setText(mFilteredList.get(position).getName());
        holder.tvDate.setText(mFilteredList.get(position).getDate());
        holder.tvSupplier.setText(mFilteredList.get(position).getSupplier());
        holder.tvIcon.setText(mFilteredList.get(position).getName().substring(0, 1));
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putString(Constants.BUNDLE_NAME, mFilteredList.get(position).getName());
                bundle.putString(Constants.BUNDLE_SUPPLIER, mFilteredList.get(position).getSupplier());
                bundle.putString(Constants.BUNDLE_DATE, mFilteredList.get(position).getDate());
                bundle.putInt(Constants.BUNDLE_TOTAL, mFilteredList.get(position).getTotal());
                bundle.putString(Constants.BUNDLE_OTHER, mFilteredList.get(position).getOther());
                Intent intent = new Intent(mContext, DetailsActivity.class);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if (charString.isEmpty()) {
                    mFilteredList = stuffList;
                    Collections.reverse(mFilteredList);
                } else {
                    List<Stuff> filteredList = new ArrayList<>();
                    for (Stuff stuff : mFilteredList) {
                        if (stuff.getName().contains(charString)
                                || stuff.getSupplier().contains(charString)) {
                            filteredList.add(stuff);
                        }
                    }
                    mFilteredList = filteredList;
                }

                FilterResults results = new FilterResults();
                results.values = mFilteredList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mFilteredList = (List<Stuff>) results.values;
                Collections.reverse(mFilteredList);
                notifyDataSetChanged();
            }
        };
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
        @BindView(R.id.content_holder_linear_layout)
        LinearLayout linearLayout;

        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
