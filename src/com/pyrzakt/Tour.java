package com.pyrzakt;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Tour implements Serializable {

    private LocalDate departureDate;  // atrybut wymagany ,data wyjazdu na wycieczkę
    private LocalDate arrivalDate;    // data powrotu z wycieczki
    private Place placeOfDeparture;
    private Place destinationPlace;   // atrybut złożony
    private boolean isPromoted;       // atrybu tprosty
    private BigDecimal airportax;     // atrybut opcjonalny
    private List<String> imgLinks = new ArrayList<String>();    // atrybut powtarzalny

    private static List<Tour> allTours; // ekstensja klasy

    private static String nameOfTravelAgency; // nazwa biura podróży - atrybut klasowy

    // getter i setter atrybutu klasowego
    public static List<String> getNameOfTravelAgency() {
        ArrayList<String> agencyName = new ArrayList<>();
        agencyName.add(nameOfTravelAgency);
        return java.util.Collections.unmodifiableList(agencyName);
    }

    public static void setNameOfTravelAgency(String nameOfTravelAgency) {
        if (nameOfTravelAgency.equals(null) || nameOfTravelAgency.isEmpty()) {
            throw new IllegalArgumentException("Podano nieprawidłową nazwę agencji");
        } else {
            Tour.nameOfTravelAgency = nameOfTravelAgency;
        }
    }

    // gettery i settery atrybutów obiektu
    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        if (departureDate == null) {
            throw new IllegalArgumentException("Podano niewłaściwą datę wyjazdu");
        } else {
            this.departureDate = departureDate;
        }
    }


    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        if (arrivalDate == null || arrivalDate.isBefore(departureDate)) {
            throw new IllegalArgumentException("Podano niewłaściwą datę powrotu");
        } else {
            this.arrivalDate = arrivalDate;
        }
    }


    //atrybut złożony
    public Place getPlaceOfDeparture() {
        return placeOfDeparture;
    }

    public void setPlaceOfDeparture(Place placeOfDeparture) {
        if (placeOfDeparture.equals(null)) {
            throw new IllegalArgumentException("Podano nieprawidłowe miejsce wyjazdu");
        }
        this.placeOfDeparture = placeOfDeparture;
    }


    public Place getDestinationPlace() {
        return destinationPlace;
    }

    public void setDestinationPlace(Place destinationPlace) {
        if (placeOfDeparture.equals(null)) {
            throw new IllegalArgumentException("Podano nieprawidłowe miejsce wyjazdu");
        }
        this.destinationPlace = destinationPlace;
    }


    //atrybut prosty


    public boolean isPromoted() {
        return isPromoted;
    }

    public void setPromoted(boolean promoted) {
        isPromoted = promoted;
    }

    //getter atrybutu pochodnego
    public long getTimeOfVisit() {
        if (departureDate == null || arrivalDate == null) {
            throw new IllegalArgumentException("Data wyjazdu i powrotu nie mogą być null");
        } else if (arrivalDate.isBefore(departureDate)) {
            throw new IllegalArgumentException("Data powrotu nie może poprzedzać daty wyjazdu");
        } else if (departureDate.getYear() == arrivalDate.getYear() && departureDate.getMonth() == arrivalDate.getMonth() && departureDate.getDayOfMonth() == arrivalDate.getDayOfMonth()) {
            return 1;
        } else {
            long timeOfVisit = ChronoUnit.DAYS.between(departureDate, arrivalDate);
            return timeOfVisit;
        }
    }

    //getter atrybutu powtarzalnego
    public List<String> getImgLinks() {
        List<String> inmutableImgLinks = null;
        try{
            inmutableImgLinks = Collections.unmodifiableList(imgLinks);
        }catch(UnsupportedOperationException e){
            System.out.println("Exception thrown : " + e);
        }
        return imgLinks;
    }

    //obsługa atrybutu powtarzalnego
    public void addNewImageLink(String link){
        if (link.equals(null)||link.isEmpty()){
            throw new IllegalArgumentException("Podano nieprawidłowy link");
        }else{
            imgLinks.add(link);
        }
    }

    public void removeImgLink(String link) throws Exception {
        if (imgLinks.size()<=1){
            throw new Exception("Musi istnięć przynajmniej jeden link, nie można usunąć ostatniego zdjęcia");
        }else{
            if(link.equals(null) || link.isEmpty()){
                throw new IllegalArgumentException("Podano nieprawidłowy link");
            }else{
                for (String imglink: imgLinks) {
                    if(imglink.equals(link)){
                        imgLinks.remove(imglink);
                    }
                }
            }
        }
    }

    //getter i setter atrybutu opcjonalnego
    public BigDecimal getAirportax() {
        return airportax;
    }

    public void setAirportax(BigDecimal airportax) {
        this.airportax = airportax;
    }

    //getter ekstensji
    public static List<Tour> getAllTours() {
        List<Tour> immutableToursList = null;
        try {
            immutableToursList = Collections
                    .unmodifiableList(allTours);
        } catch (UnsupportedOperationException e) {
            System.out.println("Exception thrown : " + e);
        }
        return immutableToursList;
    }

    // ekstensja-obsługa
    private static void addTour(Tour tour) {
        if (allTours == null) {
            allTours = new ArrayList<Tour>();
        }
        if (tour == null) {
            throw new IllegalArgumentException("Tour nie może być n ullem");
        } else {
            allTours.add(tour);
        }
    }

    public static void removeTour(Tour tour) {
        if (allTours != null) {
            allTours.remove(tour);
        }
    }

    public static void showTours(boolean isPromoted) {
        if (isPromoted == true) {
            System.out.println("Promowane wycieczki: ");
            for (Tour tour : allTours) {
                if (tour.isPromoted == isPromoted)
                    System.out.println(tour.toString());
            }
        } else {
            System.out.println("Wszystkie dostępne wycieczki: ");
            for (Tour tour : allTours) {
                System.out.println(tour.toString());
            }
        }
    }

    // trwałość ekstensji
    public static void saveTours(ObjectOutputStream stream) throws IOException {
        stream.writeObject(allTours);
    }

    public static void readSavedTours(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        allTours = (ArrayList<Tour>) stream.readObject();
    }


    // Przeciążone metody/Konstruktory
    public Tour(LocalDate departureDate, LocalDate arrivalDate, Place placeOfDeparture, Place destinationPlace, boolean isPromoted, BigDecimal airportax, String imgLink) {
        setDepartureDate(departureDate);
        setArrivalDate(arrivalDate);
        setPlaceOfDeparture(placeOfDeparture);
        setDestinationPlace(destinationPlace);
        setPromoted(isPromoted);
        setAirportax(airportax);
        imgLinks.add(imgLink);
        addTour(this);
    }

    public Tour(LocalDate departureDate, LocalDate arrivalDate, Place placeOfDeparture, Place destinationPlace, boolean isPromoted, String imgLink) {
        setDepartureDate(departureDate);
        setArrivalDate(arrivalDate);
        setPlaceOfDeparture(placeOfDeparture);
        setDestinationPlace(destinationPlace);
        setPromoted(isPromoted);
        setAirportax(null);
        imgLinks.add(imgLink);
        addTour(this);
    }

    //Przesłonięta metoda toString
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Wyjazd z: " + getPlaceOfDeparture() + " w dniu: " + getDepartureDate());
        builder.append(" Cel podróży: " + getDestinationPlace());
        builder.append(" Czas pobytu (ilość dni): " + getTimeOfVisit());
        builder.append(" Data powrotu: " + getArrivalDate());
        return builder.toString();
    }


}