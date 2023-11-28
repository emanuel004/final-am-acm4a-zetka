package com.davinci.pokedex.controller;

import android.os.AsyncTask;

import com.davinci.pokedex.model.Pokemon;
import com.davinci.pokedex.model.RegionList;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

class PokemonList {
    public List<Pokemon> pokemonList;
}

public class GetPokemon extends AsyncTask<String,Integer,String> {

    OkHttpClient client = new OkHttpClient();

    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
    @Override
    protected String doInBackground(String... strings) {
        String url= strings[0];
        String response = "";
        try {
            response = run(url);
            ObjectMapper objectMapper = new ObjectMapper();
            PokemonList respuesta = objectMapper.readValue(response, PokemonList.class);
            response = respuesta.pokemonList.get(0).getName();
            /*JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = (JSONArray) jsonObject.get("jsonArray");
            JSONObject item = (JSONObject) jsonArray.get(0);
            String dato = (String) item.get("name");*/
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return url;
    }

    protected void onPostExecute(String s){
        super.onPostExecute(s);
    }
}
