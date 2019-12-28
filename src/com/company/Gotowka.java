package com.company;

import java.util.Scanner;

public class Gotowka extends Platnosc {

    @Override
    public boolean zaplac(Zamowienie zamowienie) {
        System.out.println("Wybrano metodę płatności gotówka.");

        Scanner s = new Scanner(System.in);
        System.out.println("Kwota płatności: ");
        String inputedValue;
        do {
            inputedValue = s.nextLine();
        } while (!isFloat(inputedValue));

        kwotaPlatnosci = Float.parseFloat(inputedValue);

        idZamowienia = zamowienie.getIdentyfikatorZamowienia();

        if (zamowienie.wartoscZamowienia() == kwotaPlatnosci) {
            zamowienie.setStatusZamowienia(true);
            System.out.println("Zamównienie opłacone poprawnie.");
            return true;
        }
        System.out.println("Zamównienie NIE opłacone. Nie prawidłowa kwota.");
        return false;
    }
}
