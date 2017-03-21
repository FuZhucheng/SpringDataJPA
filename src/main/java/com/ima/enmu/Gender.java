package com.ima.enmu;

/**
 * Created by 符柱成 on 17-3-7.
 */
public enum Gender {
    male("男"),
    female("女");

    private String name;
    private Gender(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
