package com.company;

import java.util.Scanner;

public class Przelew extends Platnosc {
    private String kontoBankowe;
    private String tytulPrzelewu;

    @Override
    public boolean zaplac(Zamowienie zamowienie) {
        System.out.println("Wybrano metodę płatności przelew.");

        Scanner s = new Scanner(System.in);
        System.out.println("Kwota płatności: ");
        String inputedValue;
        do {
            inputedValue = s.nextLine();
        } while (!isFloat(inputedValue));
        kwotaPlatnosci = Float.parseFloat(inputedValue);

        idZamowienia = zamowienie.getIdentyfikatorZamowienia();

        System.out.println("Numer konta: ");
        inputedValue = s.nextLine();
        kontoBankowe = inputedValue;

        System.out.println("Tytuł przelewu: ");
        inputedValue = s.nextLine();
        tytulPrzelewu = inputedValue;

        if (zamowienie.wartoscZamowienia() == kwotaPlatnosci) {
            zamowienie.setStatusZamowienia(true);
            System.out.println("Zamównienie opłacone poprawnie.");
            return true;
        }
        System.out.println("Zamównienie NIE opłacone. Nie prawidłowa kwota.");
        return false;
    }

    public void wydrukPotwierdzenia() {
        System.out.printf("Potwierdzenia zapłaty.\n");
        System.out.printf("Kwota: %.2f\n", kwotaPlatnosci);
        System.out.printf("Powiązane z zamówieniem: %s\n", idZamowienia);
        System.out.printf("Konto bankowe: %s\n", kontoBankowe);
        System.out.printf("Tytuł przelewu: %s\n", tytulPrzelewu);
    }
}
