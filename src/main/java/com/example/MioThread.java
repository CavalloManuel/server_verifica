package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
            int var=0;
            String stringRead = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            do {
                conta ++;
                stringRead = in.readLine();

                var = Integer.parseInt(stringRead);
                if(var>numeroDaIndovinare && var<=100 ){
                    out.writeBytes(">" + '\n');
                }

                if(var<numeroDaIndovinare && var>0){
                    out.writeBytes("<" + '\n');
                }
                
                if(var == numeroDaIndovinare){
                    out.writeBytes("=" + '\n');
                    out.writeBytes(Integer.toString(conta) + '\n');
                    break;
                }
                
                if(var <= 0 || var > 100 ){
                    out.writeBytes("numero non compreso " + '\n');
                }
                

            } while (true);

            

        } catch (IOException e) {
            System.out.println("ERROR");
            System.exit(1);
        }
    }
}