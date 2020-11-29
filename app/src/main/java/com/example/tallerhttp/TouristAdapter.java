package com.example.tallerhttp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class TouristAdapter extends ArrayAdapter<Tourist> {

    private ArrayList<Tourist> tourists, touristsCopy;

    public TouristAdapter(@NonNull Context context, @NonNull ArrayList<Tourist> tourists) {
        super(context, 0, tourists);
        this.tourists = tourists;
        this.touristsCopy = new ArrayList<>();
        this.touristsCopy.addAll(tourists);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        Tourist tourist = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_tourist, parent, false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView type = (TextView) convertView.findViewById(R.id.type);
        TextView municipality = (TextView) convertView.findViewById(R.id.municipality);
        TextView description = (TextView) convertView.findViewById(R.id.description);
        TextView address = (TextView) convertView.findViewById(R.id.address);
        TextView phone = (TextView) convertView.findViewById(R.id.phone);

        name.setText(tourist.getNombresito());
        type.setText(tourist.getTipo());
        municipality.setText(tourist.getNombremunicipio());
        description.setText(tourist.getDescripcion());
        address.setText(tourist.getDireccion());
        phone.setText(tourist.getTelefono());

        return convertView;
    }

    public void filter(String text) {
        text = text.toLowerCase();
        tourists.clear();

        if (text.length() == 0) {
            tourists.addAll(touristsCopy);
        } else {
            for (Tourist m : touristsCopy) {
                if (m.getNombremunicipio().toLowerCase().contains(text)) {
                    tourists.add(m);
                }
            }
        }
        notifyDataSetChanged();
    }
}
