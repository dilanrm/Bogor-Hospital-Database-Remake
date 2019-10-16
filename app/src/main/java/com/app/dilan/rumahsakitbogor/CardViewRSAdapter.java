package com.app.dilan.rumahsakitbogor;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;


public class CardViewRSAdapter extends RecyclerView.Adapter<CardViewRSAdapter.CardViewViewHolder> {
    private Context context;
    private ArrayList<RS> listRS;
    MainActivity check;

    public ArrayList<RS> getListRS() {
        return listRS;
    }

    public void setListRS(ArrayList<RS> listRS) {
        this.listRS = listRS;
    }

    public CardViewRSAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cardview_rs, viewGroup, false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewViewHolder cardViewViewHolder, int i) {
        RS rs = getListRS().get(i);
        Glide.with(context)
                .load(rs.getFoto())
                .apply(new RequestOptions().override(350, 550))
                .into(cardViewViewHolder.imgPhoto);
        cardViewViewHolder.tvName.setText(rs.getNama());
        cardViewViewHolder.tvRemarks.setText(rs.getAlamat());
        cardViewViewHolder.btnFavorite.setOnClickListener(new CustomOnItemClickListener(i, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Toast.makeText(context, "Lokasi " + getListRS().get(position).getNama(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(
                        android.content.Intent.ACTION_VIEW,
                        Uri.parse(getListRS().get(position).getLokasi()));
                context.startActivity(i);
            }
        }));
        cardViewViewHolder.btnShare.setOnClickListener(new CustomOnItemClickListener(i, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Toast.makeText(context, "Hubungi " + getListRS().get(position).getNama(), Toast.LENGTH_SHORT).show();

                if (ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    check.checkPerm();
                }
                else{
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + getListRS().get(position).getTelepon()));
                    context.startActivity(intent);
                }
            }
        }));
        cardViewViewHolder.imgPhoto.setOnClickListener(new CustomOnItemClickListener(i, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent i = new Intent(context, DetailActivity.class);

                i.putExtra("nama", getListRS().get(position).getNama());
                i.putExtra("alamat", getListRS().get(position).getAlamat());
                i.putExtra("lokasi", getListRS().get(position).getLokasi());
                i.putExtra("telepon", getListRS().get(position).getTelepon());
                i.putExtra("foto", getListRS().get(position).getFoto());
                context.startActivity(i);
            }
        }));
    }

    @Override
    public int getItemCount() {
        return getListRS().size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName, tvRemarks;
        Button btnFavorite, btnShare;
        CardViewViewHolder(View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvRemarks = itemView.findViewById(R.id.tv_item_remarks);
            btnFavorite = itemView.findViewById(R.id.btn_set_favorite);
            btnShare = itemView.findViewById(R.id.btn_set_share);
        }
    }
}