package com.example.androidjsonwithgson.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Auxiliar {

    public String conectar(String url) {
        String response = null;

        try {
            URL endereco = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) endereco.openConnection();
            connection.setRequestMethod("GET");
            InputStream stream = connection.getInputStream();
            response = streamToString(stream);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;

    }//method

    private String streamToString(InputStream stream) {
        StringBuilder builder = new StringBuilder();
        String linha;
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));


        try {
            while (!((linha = reader.readLine()) != null)) {
                builder.append(linha).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return builder.toString();
    }//mehtod

}//class
