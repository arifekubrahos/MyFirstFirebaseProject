package com.example.aloko.myfirstfirebaseproject;

/**
 * Created by aloko on 18.06.2017.
 */

public class Model {
    private String height;
    private String weight;
    private String name;
    private String result;

    public Model(){
    }

    public Model(String height, String weight, String name, String result) {
        this.height = height;
        this.weight = weight;
        this.name = name;
        this.result = result;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
