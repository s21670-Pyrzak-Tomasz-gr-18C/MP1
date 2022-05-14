package com.pyrzakt;


import java.io.Serializable;


public class Place implements Serializable {
    private String country; //atrybut wymagany, prosty
    private String cityName; //atrybut wymagany prosty


    // gettery i settery atrybutów obiektu

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        if (country == null || country.length() == 0) {
            throw new IllegalArgumentException("Podano błędną nazwę państwa");
        } else {
            this.country = country;
        }
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        if (cityName == null || cityName.length() == 0) {
            throw new IllegalArgumentException("Podano błędną nazwę miasta");
        } else {
            this.cityName = cityName;
        }
    }


    // konstruktor
    public Place(String country, String cityName) {
        setCityName(cityName);
        setCountry(country);
    }

    //Przesłonięta metoda toString


    @Override
    public String toString() {
        return "Place{" +
                "country='" + country + '\'' +
                ", cityName='" + cityName + '\'' +
                '}';
    }
}