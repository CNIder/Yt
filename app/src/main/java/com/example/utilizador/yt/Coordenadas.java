package com.example.utilizador.yt;

/**
 * Created by Utilizador on 15/04/2018.
 */

public class Coordenadas {

    public Double lat;
    public Double Long;



    public Coordenadas(Double lat,Double Long){
        this.lat = lat;
        this.Long = Long;

    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLong() {
        return Long;
    }

    public void setLong(Double aLong) {
        Long = aLong;
    }

}

