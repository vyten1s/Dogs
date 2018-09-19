package com.vsandr.dogs.main;

import com.vsandr.dogs.model.Dogs;

public class DogsContract {

    interface View {
        void showDogs(Dogs dogs);
    }
}
