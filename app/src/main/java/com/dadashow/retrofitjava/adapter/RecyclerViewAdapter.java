package com.dadashow.retrofitjava.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dadashow.retrofitjava.R;
import com.dadashow.retrofitjava.model.CyrptoModel;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RowHolder> {
    ArrayList<CyrptoModel> modelArrayList;
    String [] colors={"#a3ff00","#ff00aa","#b4a7d6","#a4c2f4","#8ee5ee","#cd950c","#f5f5f5","#f47932"};
    public  RecyclerViewAdapter(ArrayList<CyrptoModel> modelArrayList){
        this.modelArrayList=modelArrayList;
    }
    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.recycle_row,parent,false);
        return new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RowHolder holder, int position) {
        holder.bind(modelArrayList.get(position),colors,position);
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class RowHolder extends RecyclerView.ViewHolder {
        TextView textName;
        TextView textPrice;
        public RowHolder(@NonNull View itemView) {
            super(itemView);
        }
        public  void bind(CyrptoModel model,String [] colors,int position){
            itemView.setBackgroundColor(Color.parseColor(colors[position%8]));
            textName=itemView.findViewById(R.id.textView);
            textPrice=itemView.findViewById(R.id.textView2);
            textName.setText(model.currency);
            textPrice.setText(model.price);

        }
    }
}
