/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xiat_server;

import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author Joan Galiana Magán
 */
public class Cliente implements Runnable {

    Scanner dentro;
    PrintWriter fuera;
    Thread hilo;
    String str;
    static int numCli = 0;
    int num;

    /**
     *
     * @param dentro
     * @param fuera
     */
    public Cliente(Scanner dentro, PrintWriter fuera) {
        this.dentro = dentro;
        this.fuera = fuera;
        numCli++;
        num = numCli;
    }

    private void envia() {
        fuera.println(str);
        fuera.flush();
        System.out.println("Cliente " + num + " : \t" + str);
        str = null;
    }

    private void lee() {
        str = dentro.nextLine();
    }

    @Override
    public void run() {
        while (true) {
            lee();
            envia();
        }
    }

}
