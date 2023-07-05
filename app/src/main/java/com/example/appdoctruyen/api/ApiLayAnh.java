package com.example.appdoctruyen.api;

import android.os.AsyncTask;

import com.example.appdoctruyen.interfaces.LayAnhVe;
import com.example.appdoctruyen.interfaces.LayTruyenVe;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

public class ApiLayAnh extends AsyncTask<Void,Void,Void> {
    String data;
    LayAnhVe layAnhVe;
    String idChap;

    public ApiLayAnh(LayAnhVe layAnhVe,String idChap) {
        this.layAnhVe = layAnhVe;
        this.idChap=idChap;
        this.layAnhVe.batDau();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://fptk15prm.000webhostapp.com/layAnh.php?idChap="+idChap)
                .build();
        data = null;
        try {
            Response response =client.newCall(request).execute();
            ResponseBody body=response.body();
            data = body.string();
        }catch (IOException e){
            data=null;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        if (data==null){
            this.layAnhVe.biLoi();
        }else {
            this.layAnhVe.ketThuc(data);
        }
    }
}
