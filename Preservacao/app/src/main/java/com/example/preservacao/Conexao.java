package com.example.preservacao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Conexao {

    public static String endApi(String uri){
        BufferedReader bufferedReader = null;

        try{

            URL url = new URL(uri);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            StringBuilder stringBuilder = new StringBuilder();
            bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

            String texto;
            while ((texto= bufferedReader.readLine()) != null){

                stringBuilder.append(texto+"\n");

            }

            return stringBuilder.toString();

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            if(bufferedReader != null){
                try
                {
                    bufferedReader.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
