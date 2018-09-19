package com.vsandr.dogs.main;

import com.vsandr.dogs.model.Dogs;

import java.util.List;

public class DogsContract {

    interface View {
        void showDogs(List<Dogs> dogs);

        void connectivityStatus(boolean isActive);
    }

    interface Presenter{

        void checkInternetConnection();

        void getDogsData();
    }
}
