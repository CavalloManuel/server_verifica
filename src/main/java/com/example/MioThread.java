package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MioThread extends Thread {
    Socket s;
    int numeroDaIndovinare;

    public MioThread(Socket s, int numeroDaIndovinare) {
        this.s = s;
        this.numeroDaIndovinare = numeroDaIndovinare;
    }

    public void run() {
        try {
            int conta = 0;
            String stringRead = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            do {
                stringRead = in.readLine();
                System.out.println("Stringa ricevuta : " + stringRead);

                
                
                if (Integer.parseInt(stringRead) < numeroDaIndovinare) {
                    System.out.println("<");
                    conta ++;
                }



                if (Integer.parseInt(stringRead) > numeroDaIndovinare) {
                    System.out.println(">");
                    conta ++;
                }

                if (Integer.parseInt(stringRead) == numeroDaIndovinare) {
                    System.out.println("= in " + conta + "tentativi");
                    System.out.println("Nuova partita? (yes, no)");
                    if (stringRead.equals("no")) {
                        System.out.println("The client wants to close");
                        s.close();
                    }
                }

                

                

            out.writeBytes(stringRead + '\n');
            } while (true);
        } catch (IOException e) {
            System.out.println("ERROR");
            System.exit(1);
        }
    }
}