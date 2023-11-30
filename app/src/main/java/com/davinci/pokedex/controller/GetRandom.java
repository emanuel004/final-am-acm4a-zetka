package com.davinci.pokedex.controller;

import android.os.AsyncTask;

import com.davinci.pokedex.model.Pokemon;
import com.davinci.pokedex.model.Random;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class GetRandom extends AsyncTask<String,Integer, List<Pokemon>> {

    public static final MediaType JSON = MediaType.get("application/json");

    OkHttpClient client = new OkHttpClient();

    String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
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
            response = post(url,"{\"limit\":\"10\"}");
            ObjectMapper objectMapper = new ObjectMapper();
            respuesta = Arrays.asList(objectMapper.readValue(response, Pokemon[].class));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return respuesta;
    }

    protected void onPostExecute(List<Pokemon> s){
        super.onPostExecute(s);
    }
}
