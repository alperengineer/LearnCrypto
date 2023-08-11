package com.aok.learncrypto.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aok.learncrypto.databinding.RowLayoutBinding;
import com.aok.learncrypto.model.CryptoModel;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>{

    private ArrayList<CryptoModel> cryptoModelArrayList;
    public RecyclerViewAdapter(ArrayList<CryptoModel> _cryptoModelArrayList) {
        this.cryptoModelArrayList = _cryptoModelArrayList;
    }

    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       RowLayoutBinding layoutBinding = RowLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
       return new RowHolder(layoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RowHolder holder, int position) {

        holder.rowLayoutBinding.textName.setText(cryptoModelArrayList.get(position).currency);
        holder.rowLayoutBinding.textPrice.setText(cryptoModelArrayList.get(position).price);


    }

    @Override
    public int getItemCount() {
        return cryptoModelArrayList.size();
    }

    public class RowHolder extends RecyclerView.ViewHolder {

        RowLayoutBinding rowLayoutBinding;
        public RowHolder(RowLayoutBinding binding) {
            super(binding.getRoot());
            this.rowLayoutBinding = binding;
        }
    }
}

