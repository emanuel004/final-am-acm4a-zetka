package com.davinci.pokedex.controller;

import android.os.AsyncTask;

import com.davinci.pokedex.model.Pokemon;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

class PokemonList {
    public List<Pokemon> pokemonList;
}

public class GetPokemon extends AsyncTask<String,Integer,List<Pokemon>> {

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
    protected List<Pokemon> doInBackground(String... strings) {
        String url = strings[0];
        String response = "";
        List<Pokemon> respuesta;
        try {
            response = run(url);
            ObjectMapper objectMapper = new ObjectMapper();
            respuesta = Arrays.asList(objectMapper.readValue(response, Pokemon[].class));

            //Pokemon info = respuesta[0];

            /*JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = (JSONArray) jsonObject.get("jsonArray");
            JSONObject item = (JSONObject) jsonArray.get(0);
            String dato = (String) item.get("name");*/
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return respuesta;
    }

    protected void onPostExecute(List<Pokemon> s){
        super.onPostExecute(s);
    }
}
