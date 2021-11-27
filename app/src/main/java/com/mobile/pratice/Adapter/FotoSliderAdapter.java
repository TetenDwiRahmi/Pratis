package com.mobile.pratice.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.pratice.Model.FotoSlider;
import com.mobile.pratice.R;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class FotoSliderAdapter extends RecyclerView.Adapter<FotoSliderAdapter.FotoSliderViewRecHolder> {
    private Context context;
    private List<FotoSlider> fotoList;
    private int selectedItemPosition = -1;

    public FotoSliderAdapter(Context context, List<FotoSlider> fotoList) {
        this.context = context;
        this.fotoList = fotoList;
    }

    @NonNull
    @Override
    public FotoSliderViewRecHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.show_foto, null);
        return new FotoSliderAdapter.FotoSliderViewRecHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @NonNull

    @Override
    public void onBindViewHolder(@NonNull FotoSliderViewRecHolder holder, int position) {
        final FotoSlider fotoSlider = fotoList.get(position);
        Picasso.get().load(fotoSlider.getGambar()).into(holder.foto);
        holder.kategori.setText(fotoSlider.getKategori());
        Locale localeId = new Locale("in", "ID");
        final NumberFormat rupiah = NumberFormat.getCurrencyInstance(localeId);
        holder.harga.setText(rupiah.format(fotoSlider.getHarga()));
        holder.like.setText("" + fotoSlider.getLike());

        holder.cardFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedItemPosition = position;
                notifyDataSetChanged();
            }
        });

        if (selectedItemPosition == position) {
            holder.cardKategori.setCardBackgroundColor(Color.parseColor("#8FD9A8"));
        } else {
            holder.cardKategori.setCardBackgroundColor(Color.parseColor("#ffffff"));
        }
    }


    @Override
    public int getItemCount() {
        return fotoList.size();
    }

    class FotoSliderViewRecHolder extends RecyclerView.ViewHolder {
        ImageView foto;
        TextView kategori, harga, like;
        FrameLayout cardFoto;
        CardView cardKategori;

        public FotoSliderViewRecHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            foto = itemView.findViewById(R.id.foto);
            kategori = itemView.findViewById(R.id.kategori);
            harga = itemView.findViewById(R.id.harga);
            like = itemView.findViewById(R.id.like);
            cardFoto = itemView.findViewById(R.id.cardFoto);
            cardKategori = itemView.findViewById(R.id.cardKategori);
        }
    }
}
