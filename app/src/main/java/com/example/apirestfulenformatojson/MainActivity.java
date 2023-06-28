package com.example.apirestfulenformatojson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import WebService.Asynchtask;
import WebService.WebService;
//Autor: José Zambrano

public class MainActivity extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void btmostrar(View view){
        String url="https://jsonplaceholder.typicode.com/users";
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService(url,datos, MainActivity.this, MainActivity.this);
        ws.execute("GET");
    }

    @Override
    public void processFinish(String result) throws JSONException {
        TextView txtResp=(TextView) findViewById(R.id.twResult);
        JSONArray JSONlista = new JSONArray(result);
        String Users="";
        for(int i=0; i< JSONlista.length();i++){
            JSONObject usuario = JSONlista.getJSONObject(i);
            Users = Users + "\n **************************************" + "\n -- ID: " + usuario.getString("id")
                    + "\n -- NOMBRES: "  + usuario.getString("name") + "\n -- USUARIO: "
                    + usuario.getString("username") +
                     "\n -- EMAIL: " + usuario.getString("email") + "\n -- TELÉFONO: "
                    + usuario.getString("phone");
        }
        txtResp.setText(Users);
    }
}