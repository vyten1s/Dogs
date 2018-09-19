package com.vsandr.dogs.details;

public interface DogDetailContract {

    interface View {

        void showDogs(String url);

        void showError();
    }

    interface Presenter{

        void loadDogImage(String url);
    }
}
