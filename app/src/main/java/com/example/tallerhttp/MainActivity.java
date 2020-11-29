package com.example.tallerhttp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    String url = "https://www.datos.gov.co/resource/jj37-fvz6.json";
    ArrayList<Tourist> touristsList = new ArrayList<Tourist>();
    ListView listado;
    EditText search;
    TouristAdapter touristAdapter;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listado = findViewById(R.id.listado);
        progressBar = findViewById(R.id.progressBar);
        search = findViewById(R.id.filter_municipality);
        requestDatos();
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = search.getText().toString();
                touristAdapter.filter(text);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void requestDatos(){
        RequestQueue cola = Volley.newRequestQueue(this);
        progressBar.setVisibility(View.VISIBLE);
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        parserJson(response);
                        progressBar.setVisibility(View.GONE);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(),"Error en la conexion", Toast.LENGTH_LONG).show();
            }
        })
        {
            @Override

            public Map getHeaders() throws AuthFailureError {
                HashMap headers = new HashMap();
                // headers.put("Content-Type", "application/json");
                headers.put("X-Auth-Token", "0ACdcJJKCFXsEVM6TD7mqwZcJ");
                return headers;
            }
        };
        cola.add(jsonObjectRequest);
    }


    public void parserJson(JSONArray response){
        try {
            touristsList = new ArrayList<Tourist>();
            for (int i = 0 ; i<response.length(); i++) {
                JSONObject tour = response.getJSONObject(i);
                String nombresitio = tour.getString("nombresitio");
                String tipo = tour.getString("tipo");
                String descripcion = tour.getString("descripcion");
                String nombremunicipio = tour.getString("nombremunicipio");
                String direccion = tour.getString("direccion");
                String telefono = tour.getString("telefono");
                Tourist tourist = new Tourist(nombresitio,tipo,descripcion,nombremunicipio,direccion,telefono);
                touristsList.add(tourist);
            }
            touristAdapter = new TouristAdapter(this, touristsList);
            listado.setAdapter(touristAdapter);
            touristAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

}