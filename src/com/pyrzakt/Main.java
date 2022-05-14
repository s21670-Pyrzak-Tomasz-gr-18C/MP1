package com.pyrzakt;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        // utworzenie przykładowych miejsc wyjazdu
        Place miejsceWyjazdu1 = new Place("Polska", "Warszawa");
        Place miejsceWyjazdu2 = new Place("Polska", "Radom");

        // utworzenie przykładowych miejsc docelowych
        Place miejsceDocelowe1 = new Place("Egipt", "Hurghada");
        Place miejsceDocelowe2 = new Place("Dominikana", "Puerto Plata");


        // utworzenie przykładowych wycieczek
        Tour tour1 = new Tour(LocalDate.of(2022, 04, 15), LocalDate.of(2022, 05, 12), miejsceWyjazdu1, miejsceDocelowe1, true, BigDecimal.valueOf(100), "C:\\zdj1");
        Tour tour2 = new Tour(LocalDate.of(2022, 05, 12), LocalDate.of(2022, 05, 12), miejsceWyjazdu2, miejsceDocelowe2, false, "zdjecie2");


        // metoda klasowa wyświetlająca wszystkie lub tylko promowane wycieczki (true - tylko promowane)
        System.out.println("Ekstensja klasy Tour - metoda klasowa:");
        System.out.println();
        Tour.showTours(true);
        System.out.println();
        Tour.showTours(false);
        System.out.println();

        // atrybut klasowy
        Tour.setNameOfTravelAgency("Biuro Podróży Tomka P.");
        System.out.println("Nazwa biura podróży: " + Tour.getNameOfTravelAgency());
        System.out.println();

        // atrybut prosty
        System.out.println("Atrybut posty (czy wycieczka jest promowana):  " + tour1.isPromoted());
        System.out.println();

        // atrybut pochodny
        System.out.println("Atrybut pochodny (ilość dni pobytu): " + tour2.getTimeOfVisit());
        System.out.println();

        // atrybut złożony
        System.out.println("Atrybut złożony (cel podróży): " + tour1.getDestinationPlace().toString());
        System.out.println();

        // atrybut opcjonalny
        System.out.println("Atrybyt opcjonalny:");
        System.out.println("Wysokość dodatkowych opłat lotniskowych wycieczka1 : " + tour1.getAirportax());
        System.out.println("Wysokość dodatkowych opłat lotniskowych wycieczka2 : " + tour2.getAirportax());
        System.out.println();

        // atrybut powtarzalny
        System.out.println("Atrybut powtarzalny:");
        System.out.println("linki do zdjęć związanych z daną wycieczką");
        System.out.println();


        // trwałość ekstensji
        //zapis do pliku
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File("Backup.txt")))) {
            Tour.saveTours(out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File("Backup.txt")))) {
            Tour.readSavedTours(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        for (Tour tour:Tour.getAllTours()) {
            System.out.println(tour.toString());
        }

    }
}
