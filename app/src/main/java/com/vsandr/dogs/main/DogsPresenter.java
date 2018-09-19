package com.vsandr.dogs.main;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;
import android.util.Log;

import com.google.gson.Gson;
import com.vsandr.dogs.model.Dogs;

import java.io.IOException;
import java.io.InputStream;

public class DogsPresenter implements DogsContract.Presenter {

    private Context context;
    private DogsContract.View view;

    public DogsPresenter(Context context, DogsContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void checkInternetConnection() {
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        assert cm != null;
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        view.connectivityStatus(isConnected);
    }

    @Override
    public void getDogsData() {
        String jsonString = getAssetsJSON(MainActivity.JSON_FILE);
        Log.d(MainActivity.TAG, "Json: " + jsonString);
        Gson gson = new Gson();
        Dogs baseWatch = gson.fromJson(jsonString, Dogs.class);
        view.showDogs(baseWatch.getDogs());
    }

    @Override
    public void countColumns() {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        view.columnNumber((int) (dpWidth / 180));
    }

    /* Get File in Assets Folder */
    private String getAssetsJSON(String fileName) {
        String json = null;
        try {
            InputStream inputStream = context.getAssets().open(fileName);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

}
