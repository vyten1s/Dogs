package com.vsandr.dogs.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Dogs {

    @SerializedName("urls")
    @Expose
    private List dogs = new ArrayList<>();

    public List getDogs() {
        return dogs;
    }

    public void setDogs(List dogs) {
        this.dogs = dogs;
    }
}
