package com.mobile.pratice.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.pratice.Model.Kategori;
import com.mobile.pratice.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class KategoriAdapter extends RecyclerView.Adapter<KategoriAdapter.KategoriViewRecHolder> {
    private Context context;
    private List<Kategori> kategoriList;

    public KategoriAdapter(Context context, List<Kategori> kategoriList) {
        this.context = context;
        this.kategoriList = kategoriList;
    }

    @NonNull
    @Override
    public KategoriViewRecHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.show_kategori, null);
        return new KategoriAdapter.KategoriViewRecHolder(view);
    }

    @NonNull

    @Override
    public void onBindViewHolder(@NonNull KategoriViewRecHolder holder, int position) {
        final Kategori kategori = kategoriList.get(position);
        holder.NamaKategori.setText(kategori.getNama_kategori());
    }


    @Override
    public int getItemCount() {
        return kategoriList.size();
    }

    class KategoriViewRecHolder extends RecyclerView.ViewHolder {
        TextView NamaKategori;
        public KategoriViewRecHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            NamaKategori = itemView.findViewById(R.id.nama_kategori);
        }
    }
}
