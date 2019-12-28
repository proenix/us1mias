package com.company;

import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Email implements INotatka {

    @Override
    public void drukujPotwierdzenie() {
        String email = inputEmail();

        System.out.printf("Potwierdzenie wysłane na %s\n", email);
    }

    public boolean checkEmailvalidity(String emailaddress){
        String email_regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        boolean b = emailaddress.matches(email_regex);
        return b;
    }

    public String inputEmail(){
        Scanner s = new Scanner(System.in);
        System.out.println("Wpisz adres email: ");
        String emailaddress = s.nextLine();
        boolean a = checkEmailvalidity(emailaddress);
        if(!a){
            System.out.println("Adres nieprawidłowy.");
            return inputEmail();
        }
        return emailaddress;
    }
}
