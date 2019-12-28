package com.company;

public class Drukarka implements INotatka {

    @Override
    public void drukujPotwierdzenie() {
        System.out.println("Potwierdzenie wydrukowane via Drukarka");
    }
}
