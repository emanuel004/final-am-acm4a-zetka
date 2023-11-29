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

public class PokemonGet extends AsyncTask<String,Integer, Pokemon> {

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
    protected Pokemon doInBackground(String... strings) {
        String url = strings[0];
        String response = "";
        Pokemon respuesta;
        try {
            response = run(url);
            ObjectMapper objectMapper = new ObjectMapper();
            respuesta = objectMapper.readValue(response, Pokemon.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return respuesta;
    }

    protected void onPostExecute(Pokemon s){
        super.onPostExecute(s);
    }
}
