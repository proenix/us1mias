package com.company;

public abstract class Platnosc {
    protected float kwotaPlatnosci;
    protected String idZamowienia;

    public abstract boolean zaplac(Zamowienie zamowienie);

    public void wydrukPotwierdzenia() {
        System.out.printf("Potwierdzenia zapłaty.\n");
        System.out.printf("Kwota: %.2f\n", kwotaPlatnosci);
        System.out.printf("Powiązane z zamówieniem: %s\n", idZamowienia);
    }

    public String getTypPlatnosci() {
        return this.getClass().getSimpleName();
    }

    protected boolean isFloat(String s) {
        try {
            Float.parseFloat(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }

    protected boolean isInteger(String s) {
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

    protected boolean isByte(String s) {
        try {
            Byte.parseByte(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }
}
