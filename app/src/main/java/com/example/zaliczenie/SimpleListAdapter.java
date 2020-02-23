package com.example.zaliczenie;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SimpleListAdapter extends RecyclerView.Adapter<SimpleListAdapter.SimpleViewHolder>{

    List<Result> mItems;

    SimpleListAdapter(List<Result> items){
        mItems = items;
    }

    @NonNull
    @Override
    public SimpleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new SimpleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleViewHolder holder, int position) {
        holder.operations.setText(mItems.get(position).getOperations());
        holder.result.setText(mItems.get(position).getResult());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class SimpleViewHolder extends RecyclerView.ViewHolder{

        final TextView operations;
        final TextView result;

        public SimpleViewHolder(@NonNull View itemView) {
            super(itemView);

            operations = itemView.findViewById(R.id.operations);
            result = itemView.findViewById(R.id.result);
        }
    }

    public void updateHistory(List<Result> newlist) {
        mItems = newlist;
        this.notifyDataSetChanged();
    }
}
