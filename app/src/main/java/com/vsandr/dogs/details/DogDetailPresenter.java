package com.vsandr.dogs.details;

import android.content.Context;

public class DogDetailPresenter implements DogDetailContract.Presenter {

    private Context context;
    private DogDetailContract.View view;

    public DogDetailPresenter(Context context, DogDetailContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void loadDogImage(String url) {
        if(url.isEmpty()){
            view.showError();
        }else{
            view.showDogs(url);
        }
    }
}
