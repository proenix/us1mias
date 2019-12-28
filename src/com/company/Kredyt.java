package com.company;

import java.util.Scanner;

public class Kredyt extends Platnosc {
    private byte liczbaRat;
    private String bank;
    private float oprocentowanieRoczne;

    @Override
    public boolean zaplac(Zamowienie zamowienie) {
        System.out.println("Wybrano metodę płatności kredyt.");

        Scanner s = new Scanner(System.in);
        System.out.println("Kwota płatności: ");
        String inputedValue;
        do {
            inputedValue = s.nextLine();
        } while (!isFloat(inputedValue));
        kwotaPlatnosci = Float.parseFloat(inputedValue);

        idZamowienia = zamowienie.getIdentyfikatorZamowienia();

        System.out.println("Liczba rat: ");
        do {
            inputedValue = s.nextLine();
        } while (!isByte(inputedValue));
        liczbaRat = Byte.parseByte(inputedValue);

        System.out.println("Oprocentowanie Roczne: ");
        do {
            inputedValue = s.nextLine();
        } while (!isFloat(inputedValue));
        oprocentowanieRoczne = Float.parseFloat(inputedValue);

        System.out.println("Bank: ");
        inputedValue = s.nextLine();
        bank = inputedValue;

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
        System.out.printf("Liczba rat: %d\n", liczbaRat);
        System.out.printf("Oprocentowanie roczne: %.2f\n", oprocentowanieRoczne);
        System.out.printf("Bank: %s\n", bank);
    }
}
