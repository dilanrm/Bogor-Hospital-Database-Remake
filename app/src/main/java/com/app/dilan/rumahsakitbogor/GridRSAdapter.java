package com.app.dilan.rumahsakitbogor;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class GridRSAdapter extends RecyclerView.Adapter<GridRSAdapter.GridViewHolder> {
    private Context context;
    private ArrayList<RS> listRS;

    public ArrayList<RS> getListRS() {
        return listRS;
    }

    public void setListRS(ArrayList<RS> listRS) {
        this.listRS = listRS;
    }

    public GridRSAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public GridRSAdapter.GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_rs, parent, false);
        return new GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GridRSAdapter.GridViewHolder holder, int position) {
        Glide.with(context)
                .load(getListRS().get(position).getFoto())
                .apply(new RequestOptions().override(350, 550))
                .into(holder.imgPhoto);
    }

    @Override
    public int getItemCount() {
        return getListRS().size();
    }

    public class GridViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;

        GridViewHolder(View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
        }
    }
}
