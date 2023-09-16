package com.example.preservacao;

import android.os.AsyncTask;
import android.widget.TextView;

public class SearchAPI extends AsyncTask<String, String, String> {


    private TextView textView;

    public SearchAPI(){

    }
    public SearchAPI(TextView textView) {
        this.textView = textView;
    }

    @Override
    protected String doInBackground(String... strings) {
        String result = Conexao.endApi(strings[0]);
        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        textView.setText(s);
    }
}
