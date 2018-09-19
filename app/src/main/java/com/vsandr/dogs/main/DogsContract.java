package com.vsandr.dogs.main;

import com.vsandr.dogs.model.Dogs;

import java.util.List;

public class DogsContract {

    interface View {
        void showDogs(List<Dogs> dogs);

        void connectivityStatus(boolean isActive);

        void columnNumber(int number);
    }

    interface Presenter{

        void checkInternetConnection();

        void getDogsData();

        void countColumns();
    }

}
