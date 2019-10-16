package com.app.dilan.rumahsakitbogor;

import android.os.Parcel;
import android.os.Parcelable;

public class RS implements Parcelable {
    private String nama, alamat, telepon, lokasi, foto;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nama);
        dest.writeString(this.alamat);
        dest.writeString(this.foto);
    }

    public RS(){

    }

    protected RS(Parcel in) {
        this.nama = in.readString();
        this.alamat = in.readString();
        this.telepon = in.readString();
        this.lokasi = in.readString();
        this.foto = in.readString();
    }

    public static final Creator<RS> CREATOR = new Creator<RS>() {
        @Override
        public RS createFromParcel(Parcel in) {
            return new RS(in);
        }

        @Override
        public RS[] newArray(int size) {
            return new RS[size];
        }
    };
}