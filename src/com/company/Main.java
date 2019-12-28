package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static List<Zamowienie> zamowieniaList = new ArrayList<Zamowienie>();
    public static List<Platnosc> platnosciList = new ArrayList<Platnosc>();

    public static void main(String[] args) {
        mainMenu();
    }

    public static void clearScreen() {
        //System.out.print("\033[H\033[2J");
        //System.out.flush();
        System.out.println("___________________________");
    }

    public static void mainMenu() {
        clearScreen();
        Scanner s = new Scanner(System.in);
        System.out.println("by Piotr Trzepacz 2019");
        System.out.println("---");
        System.out.printf("1. Lista Zamówień\n" +
                "2. Dodaj Zamówienie\n" +
                "3. Lista płatności\n");
        String inputtedValue;
        do {
            inputtedValue = s.nextLine();
        } while (!(inputtedValue.equals("1") ||
                inputtedValue.equals("2") ||
                inputtedValue.equals("3")
        ));
        switch (inputtedValue) {
            case "1":
                listZamowienia();
                break;
            case "2":
                addZamowienie();
                break;
            case "3":
                listPlatnosci();
                break;
            default:
                break;
        }
    }

    private static void listPlatnosci() {
        clearScreen();
        System.out.println("Lista płatności: ");
        int i = 1;
        for (Platnosc data: platnosciList) {
            System.out.printf("[%d] %.2f  %s\n", i, data.kwotaPlatnosci, data.getTypPlatnosci());
            i++;
        }
        System.out.println("Koniec listy płatności.");
        System.out.println("Wpisz numer porządkowy płatności aby go wybrać lub EXIT aby wyjść do menu.");
        Scanner s = new Scanner(System.in);
        String inputtedValue;
        do {
            inputtedValue = s.nextLine();
        } while (!(inputtedValue.equals("EXIT") ||
                isInteger(inputtedValue)
        ));

        if (inputtedValue.equals("EXIT")) {
            mainMenu();
        } else {
            try {
                Platnosc selectedPlatnosc = platnosciList.get(Integer.parseInt(inputtedValue)-1);
                showPlatnosc(selectedPlatnosc);
            } catch (IndexOutOfBoundsException e) {
                mainMenu();
            }
        }
    }

    private static void showPlatnosc(Platnosc platnosc) {
        clearScreen();
        Scanner s = new Scanner(System.in);
        System.out.printf("Wybrano płatność dot. %s\n", platnosc.idZamowienia);
        System.out.printf("1. Wydruk potwierdzenia\n" +
                "2. Wyjdź do menu\n");
        String inputtedValue;
        do {
            inputtedValue = s.nextLine();
        } while (!(inputtedValue.equals("1") ||
                inputtedValue.equals("2")
        ));
        switch (inputtedValue) {
            case "1":
                platnosc.wydrukPotwierdzenia();
                break;
            case "2":
                mainMenu();
                break;
            default:
                break;
        }
        showPlatnosc(platnosc);
    }

    private static void addZamowienie() {
        Zamowienie nZamowienie = new Zamowienie();
        nZamowienie.askNoweZamowienie();
        zamowieniaList.add(nZamowienie);
        addPozycjeZamowienia(nZamowienie);
        mainMenu();
    }

    private static void addPozycjeZamowienia(Zamowienie zamowienie) {
        Scanner s = new Scanner(System.in);
        zamowienie.dodajPozycjeZamowienia();
        System.out.println("Dodać pozycje do zamówienia? [TAK/NIE]");
        String inputedValue;
        do {
            inputedValue = s.nextLine();
        } while (!(inputedValue.equals("TAK") ||
                inputedValue.equals("NIE")
        ));

        if (inputedValue.equals("TAK")) {
            zamowienie.dodajPozycjeZamowienia();
            addPozycjeZamowienia(zamowienie);
        }
    }

    private static void listZamowienia() {
        clearScreen();
        System.out.println("Lista zamówień: ");
        int i = 1;
        for (Zamowienie data: zamowieniaList) {
            System.out.println(i + " " + data.getIdentyfikatorZamowienia());
            i++;
        }
        System.out.println("Koniec listy zamówień.");
        System.out.println("Wpisz numer porządkowy zamówienia aby go wybrać lub EXIT aby wyjść do menu.");
        Scanner s = new Scanner(System.in);
        String inputtedValue;
        do {
            inputtedValue = s.nextLine();
        } while (!(inputtedValue.equals("EXIT") ||
                isInteger(inputtedValue)
        ));

        if (inputtedValue.equals("EXIT")) {
            mainMenu();
        } else {
            try {
                Zamowienie selectedZam = zamowieniaList.get(Integer.parseInt(inputtedValue)-1);
                showZamowienie(selectedZam);
            } catch (IndexOutOfBoundsException e) {
                mainMenu();
            }
        }
    }

    private static void showZamowienie(Zamowienie zamowienie) {
        clearScreen();
        Scanner s = new Scanner(System.in);
        System.out.printf("Wybrano zamówienie %s\n", zamowienie.getIdentyfikatorZamowienia());
        System.out.printf("1. Pokaż szczegóły zamówienia\n" +
                "2. Opłać zamówienie\n" +
                "3. Wydrukuj potwierdzenie\n" +
                "4. Wyjdź do menu\n");
        String inputtedValue;
        do {
            inputtedValue = s.nextLine();
        } while (!(inputtedValue.equals("1") ||
                inputtedValue.equals("2") ||
                inputtedValue.equals("3") ||
                inputtedValue.equals("4")
        ));
        switch (inputtedValue) {
            case "1":
                zamowienie.printZamowienie();
                break;
            case "2":
                oplacZamowienie(zamowienie);
                break;
            case "3":
                zamowienie.drukujPotwierdzenie();
                break;
            case "4":
                mainMenu();
                break;
            default:
                break;
        }
        showZamowienie(zamowienie);
    }

    private static void oplacZamowienie(Zamowienie zamowienie) {
        clearScreen();
        System.out.println("Wybierz zamówienie do opłacenia: ");
        System.out.printf("Wybierz metodę płatności:\n" +
                "1.Gotówka\n" +
                "2.Kredyt\n" +
                "3.Przelew\n" +
                "[Wpisz 1, 2, 3]\n");
        Scanner s = new Scanner(System.in);
        String inputtedValue;
        do {
            inputtedValue = s.nextLine();
        } while (!(inputtedValue.equals("1") ||
                inputtedValue.equals("2") ||
                inputtedValue.equals("3")
        ));
        if (inputtedValue.equals("1")) {
            Gotowka nPlatnosc = new Gotowka();
            if (nPlatnosc.zaplac(zamowienie)) {
                platnosciList.add(nPlatnosc);
            }
        } else if (inputtedValue.equals("2")) {
            Kredyt nPlatnosc = new Kredyt();
            if (nPlatnosc.zaplac(zamowienie)) {
                platnosciList.add(nPlatnosc);
            }
        } else {
            Przelew nPlatnosc = new Przelew();
            if (nPlatnosc.zaplac(zamowienie)) {
                platnosciList.add(nPlatnosc);
            }
        }
        mainMenu();
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }
}
