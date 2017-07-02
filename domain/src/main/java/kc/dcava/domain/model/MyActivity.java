package kc.dcava.domain.model;

import java.io.Serializable;

/**
 * Created by davidcavajimenez on 26/6/17.
 */

public class MyActivity implements Serializable{

    private long   _id;
    private String name;
    private String description_es;
    private String description_en;
    private String address;
    private String front_image;
    private String logo;
    private Float latitude;
    private Float longitude;


    public MyActivity(){
        super();
    }



    public MyActivity(long id, String name, String description_es, String description_en, String address, String front_image, String logo, Float latitude, Float longitude) {
        this._id = id;
        this.name = name;
        this.description_es = description_es;
        this.description_en = description_en;
        this.address = address;
        this.front_image = front_image;
        this.logo = logo;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public long getId() {
        return _id;
    }

    public MyActivity setId(long _id) {
        this._id = _id;
        return this;
    }

    public String getName() {
        return name;
    }

    public MyActivity setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription_es() {
        return description_es;
    }

    public MyActivity setDescription_es(String description_es) {
        this.description_es = description_es;
        return this;
    }

    public String getDescription_en() {
        return description_en;
    }

    public MyActivity setDescription_en(String description_en) {
        this.description_en = description_en;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public MyActivity setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getFront_image() {
        return front_image;
    }

    public MyActivity setFront_image(String front_image) {
        this.front_image = front_image;
        return this;
    }

    public String getLogo() {
        return logo;
    }

    public MyActivity setLogo(String logo) {
        this.logo = logo;
        return this;
    }

    public Float getLatitude() {
        return latitude;
    }

    public MyActivity setLatitude(Float latitude) {
        this.latitude = latitude;
        return this;
    }

    public Float getLongitude() {
        return longitude;
    }

    public MyActivity setLongitude(Float longitude) {
        this.longitude = longitude;
        return this;
    }
}
