package com.example.tallerhttp;

import android.os.Parcel;
import android.os.Parcelable;

public class Tourist implements Parcelable {
    private String nombresito;
    private String tipo;
    private String descripcion;
    private String nombremunicipio;
    private String direccion;
    private String telefono;

    public String getNombresito() {
        return nombresito;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getNombremunicipio() {
        return nombremunicipio;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public Tourist(String nombresito, String tipo, String descripcion, String nombremunicipio, String direccion, String telefono) {
        this.nombresito = nombresito;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.nombremunicipio = nombremunicipio;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    protected Tourist(Parcel in) {
        nombresito = in.readString();
        tipo = in.readString();
        descripcion = in.readString();
        nombremunicipio = in.readString();
        direccion = in.readString();
        telefono = in.readString();
    }

    public static final Creator<Tourist> CREATOR = new Creator<Tourist>() {
        @Override
        public Tourist createFromParcel(Parcel in) {
            return new Tourist(in);
        }

        @Override
        public Tourist[] newArray(int size) {
            return new Tourist[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombresito);
        dest.writeString(tipo);
        dest.writeString(descripcion);
        dest.writeString(nombremunicipio);
        dest.writeString(direccion);
        dest.writeString(telefono);
    }
}
