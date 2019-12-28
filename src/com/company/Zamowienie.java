package com.company;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Zamowienie{
    private SimpleDateFormat dataRealizacji;
    private boolean statusZamowienia;
    private String identyfikatorZamowienia;
    private List<PozycjaZamowienia> listaPozycjiZamowienia;
    private boolean potwierdzenieElektorniczne;

    public Zamowienie() {
        listaPozycjiZamowienia = new ArrayList<PozycjaZamowienia>();
    }

    public double wartoscZamowienia() {
        double wZamowienia = 0;
        for(PozycjaZamowienia data: listaPozycjiZamowienia) {
            wZamowienia += data.obliczBrutto();
        }
        return wZamowienia;
    }

    public double wartoscPodatku() {
        double wPodatek = 0;
        for(PozycjaZamowienia data: listaPozycjiZamowienia) {
            wPodatek += data.obliczPodatek();
        }
        return wPodatek;
    }

    public boolean oplacZamowienie(Platnosc platnosc) {
        return platnosc.zaplac(this);
    }

    public void drukujPotwierdzenie() {
        if (potwierdzenieElektorniczne == true) {
            Email p = new Email();
            p.drukujPotwierdzenie();
        } else {
            Drukarka p = new Drukarka();
            p.drukujPotwierdzenie();
        }
    }

    public void dodajPozycjeZamowienia()  {
        PozycjaZamowienia nPozycjaZamowienia = new PozycjaZamowienia();
        nPozycjaZamowienia.askNowaPozycja();
        listaPozycjiZamowienia.add(nPozycjaZamowienia);
    }

    public void printPozycjeZamowienia() {
        int i = 1;
        for(PozycjaZamowienia data: listaPozycjiZamowienia) {
            data.printPozycja(i);
            i++;
        }
    }

    public void setIdentyfikatorZamowienia(String identyfikatorZamowienia) {
        this.identyfikatorZamowienia = identyfikatorZamowienia;
    }

    public String getIdentyfikatorZamowienia() {
        return identyfikatorZamowienia;
    }

    public void setStatusZamowienia(boolean statusZamowienia) {
        this.statusZamowienia = statusZamowienia;
    }

    public void setPotwierdzenieElektorniczne(boolean potwierdzenieElektorniczne) {
        this.potwierdzenieElektorniczne = potwierdzenieElektorniczne;
    }

    public void printZamowienie() {
        System.out.printf("Zamowienie: %s\n", identyfikatorZamowienia);
        System.out.println();
        System.out.printf("Opłacone: %s\n", (statusZamowienia?"TAK":"NIE"));

        System.out.printf("Pozycje zamówienia: \n");
        printPozycjeZamowienia();

        System.out.println();
        System.out.printf("Wartość zamówienia: %.2f\n", wartoscZamowienia());
        System.out.printf("Wartość podatku: %.2f\n", wartoscPodatku());
    }

    public void askNoweZamowienie() {
        Scanner s = new Scanner(System.in);
        askIdentyfikatorZamowienia(s);
        askPotwierdzenieElektroniczne(s);
    }

    private boolean checkValidity(String test, String regex) {
        boolean b = test.matches(regex);
        if (!b) {
            System.out.println("Wartość nieprawidłowa.");
        }
        return b;
    }

    private void askIdentyfikatorZamowienia(Scanner s) {
        System.out.println("Identyfikator zamowienia: ");
        String inputedValue;
        do {
            inputedValue = s.nextLine();
        } while (!checkValidity(inputedValue, "^[_A-Za-z0-9-\\+]+"));
        identyfikatorZamowienia = inputedValue;
    }
    private void askPotwierdzenieElektroniczne(Scanner s) {
        System.out.println("Potwierdzenie elektroniczne? [TAK/NIE]");
        String inputedValue;
        do {
            inputedValue = s.nextLine();
        } while (!(inputedValue.equals("TAK") ||
                inputedValue.equals("NIE")
        ));

        potwierdzenieElektorniczne = inputedValue.equals("TAK");
    }
}
