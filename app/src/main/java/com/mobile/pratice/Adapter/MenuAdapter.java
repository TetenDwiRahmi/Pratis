package com.mobile.pratice.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.pratice.Model.Kategori;
import com.mobile.pratice.Model.Menu;
import com.mobile.pratice.R;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.KategoriViewRecHolder> {
    private Context context;
    private List<Menu> menuList;

    public MenuAdapter(Context context, List<Menu> menuList) {
        this.context = context;
        this.menuList = menuList;
    }

    @NonNull
    @Override
    public KategoriViewRecHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.show_menu, null);
        return new MenuAdapter.KategoriViewRecHolder(view);
    }

    @NonNull

    @Override
    public void onBindViewHolder(@NonNull KategoriViewRecHolder holder, int position) {
        final Menu menu = menuList.get(position);
        holder.menu.setText(menu.getMenu());
    }


    @Override
    public int getItemCount() {
        return menuList.size();
    }

    class KategoriViewRecHolder extends RecyclerView.ViewHolder {
        TextView menu;
        ImageView image_menu;
        public KategoriViewRecHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            menu = itemView.findViewById(R.id.menu);
            image_menu = itemView.findViewById(R.id.image_menu);
        }
    }
}
