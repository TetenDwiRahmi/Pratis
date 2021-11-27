package com.mobile.pratice.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.pratice.Activity.DetailCleanActivity;
import com.mobile.pratice.Model.Kategori;
import com.mobile.pratice.Model.PasClean;
import com.mobile.pratice.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

public class PasCleanAdapter extends RecyclerView.Adapter<PasCleanAdapter.KategoriViewRecHolder> {
    private Context context;
    private List<PasClean> pasCleans;
    private int selectedItemPosition = -1;

    public PasCleanAdapter(Context context, List<PasClean> pasCleans) {
        this.context = context;
        this.pasCleans = pasCleans;
    }

    @NonNull
    @Override
    public KategoriViewRecHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.show_layanan, null);
        return new PasCleanAdapter.KategoriViewRecHolder(view);
    }

    @SuppressLint({"SetTextI18n", "ResourceAsColor"})
    @NonNull

    @Override
    public void onBindViewHolder(@NonNull KategoriViewRecHolder holder, int position) {
        final PasClean pasClean = pasCleans.get(position);
        holder.NamaLayanan.setText(pasClean.getLayanan());
        holder.Alamat.setText(pasClean.getAlamat());
        holder.Jarak.setText(pasClean.getJarak() + " KM");
        Picasso.get().load(pasClean.getFoto_layanan()).into(holder.Foto_layanan);

        holder.layout_pasClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedItemPosition = position;
                notifyDataSetChanged();
                Intent i = new Intent(context, DetailCleanActivity.class);
                context.startActivity(i);
            }
        });

        if (selectedItemPosition == position) {
            holder.layout_pasClean.setBackgroundColor(Color.parseColor("#8FD9A8"));
            holder.Alamat.setTextColor(R.color.colorWhite);
            holder.Jarak.setTextColor(R.color.colorWhite);
        } else {
            holder.layout_pasClean.setBackgroundColor(Color.parseColor("#ffffff"));
        }
    }


    @Override
    public int getItemCount() {
        return pasCleans.size();
    }

    class KategoriViewRecHolder extends RecyclerView.ViewHolder {
        TextView NamaLayanan, Alamat, Jarak;
        ImageView Foto_layanan;
        LinearLayout layout_pasClean;
        public KategoriViewRecHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            NamaLayanan = itemView.findViewById(R.id.nama_layanan);
            Alamat = itemView.findViewById(R.id.alamat);
            Jarak = itemView.findViewById(R.id.jarak);
            Foto_layanan = itemView.findViewById(R.id.foto_layanan);
            layout_pasClean = itemView.findViewById(R.id.layout_pasClean);
        }
    }
}
