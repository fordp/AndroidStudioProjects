package com.example.gmuras.ancientbritain;

/**
 * Created by Fordp on 26/02/2017.
 */

public class MainDataDef {
    int image;
    String name;
    String info;

    public MainDataDef(int image, String name, String info) {
        this.image = image;
        this.name = name;
        this.info = info;
    }

    public int getImage() { return image; }

    public String getName() { return name; }

    public String getInfo() { return info; }
}
