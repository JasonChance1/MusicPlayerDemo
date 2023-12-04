package com.example.musicplayerdemo;

import java.io.Serializable;

/**
 * @author yanglei
 * @version 1.0.0
 * @description:
 * @date :2023/12/4
 */
public class Song implements Serializable {
    public Song(){

    }

    public Song(String name, int song){
        this.name = name;
        this.song = song;
    }

    private int song;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
