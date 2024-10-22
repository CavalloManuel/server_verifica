package com.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;


public class Main {
     
    public static void main(String[] args) throws IOException {
        System.out.println("Server Avviato! Indovina il numero.");
        Random random = new Random();
        int numeroDaIndovinare = random.nextInt(100);
        ServerSocket ss = new ServerSocket(3000);
        do{
            Socket s = ss.accept();
            System.out.println("un client si è collegato");
            MioThread t = new MioThread(s, numeroDaIndovinare);

            t.start();
        }while(true);
        
    }
}