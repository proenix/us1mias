package com.company;

import java.util.Scanner;

public class PozycjaZamowienia {
    private String nazwaTowaru;
    private float cenaJednostkowa;
    private int stawkaVAT;
    private float ilosc;

    public double obliczBrutto() {
        return cenaJednostkowa*ilosc + obliczPodatek();
    }

    public double obliczPodatek() {
        return cenaJednostkowa*stawkaVAT/100*ilosc;
    }

    private boolean checkValidity(String test, String regex) {
        boolean b = test.matches(regex);
        if (!b) {
            System.out.println("Wartość nieprawidłowa.");
        }
        return b;
    }
    
    private void askNazwaTowaru(Scanner s) {
        System.out.println("Nazwa towaru: ");
        String inputedValue;
        do {
            inputedValue = s.nextLine();
        } while (!checkValidity(inputedValue, "^[_A-Za-z0-9-\\+]+"));
        nazwaTowaru = inputedValue;
    }

    private void askCenaJednostkowa(Scanner s) {
        System.out.println("Cena jednostkowa: ");
        String inputedValue;
        do {
            inputedValue = s.nextLine();
        } while (!checkValidity(inputedValue, "^([+-]?\\d*\\.?\\d*)$"));
        cenaJednostkowa = Float.parseFloat(inputedValue);
    }

    private void askStawkaVAT(Scanner s) {
        System.out.println("Stawka VAT: (0, 5, 8, 23)");
        String inputedValue;
        do {
            inputedValue = s.nextLine();
        } while (!(inputedValue.equals("0") ||
                inputedValue.equals("5") ||
                inputedValue.equals("8") ||
                inputedValue.equals("23")
                ));
        stawkaVAT = Integer.parseInt(inputedValue);
    }

    private void askIlosc(Scanner s) {
        System.out.println("Ilość: ");
        String inputedValue;
        do {
            inputedValue = s.nextLine();
        } while (!checkValidity(inputedValue, "^([+-]?\\d*\\.?\\d*)$"));
        ilosc = Float.parseFloat(inputedValue);
    }

    public void askNowaPozycja() {
        Scanner s = new Scanner(System.in);
        askNazwaTowaru(s);
        askCenaJednostkowa(s);
        askIlosc(s);
        askStawkaVAT(s);
    }

    public void printPozycja(int i) {
        System.out.printf("[%d] %s \n%.2fPLN/szt | %.2fszt\n", i, nazwaTowaru, cenaJednostkowa, ilosc);
        System.out.printf("Brutto: %.2fPLN | VAT(%d): %.2f\n", obliczBrutto(), stawkaVAT, obliczPodatek());
    }
}
