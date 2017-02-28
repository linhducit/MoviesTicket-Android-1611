package com.pt.movieticket.model;

import com.pt.movieticket.base.model.BaseModel;

/**
 * Created by TitTit on 29/11/2016.
 */

public class Cinemas {
    private String image;
    private String cinemas;


    public Cinemas(String cinemas) {
        this.cinemas = cinemas;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCinemas() {
        return cinemas;
    }

    public void setCinemas(String cinemas) {
        this.cinemas = cinemas;
    }
}
