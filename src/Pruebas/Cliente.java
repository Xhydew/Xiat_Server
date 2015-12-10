/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pruebas;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joan Galiana Magán
 */
public class Cliente {

    PrintWriter fuera;
    Scanner dentro;
    Socket cSocket;
    String nick;
    Thread escuchaHilo;
    Envia envia;
    int id;

    /**
     *
     * @param nick
     * @param cSocket
     * @param envia
     * @param id
     */
    public Cliente(String nick, Socket cSocket, Envia envia, int id) {
        this.nick = nick;
        this.id = id;
        this.cSocket = cSocket;
        this.envia = envia;
        try {
            dentro = new Scanner(cSocket.getInputStream());
            fuera = new PrintWriter(cSocket.getOutputStream());

        } catch (IOException ex) {
            System.err.println("Error en la creacion de Stream");
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

        Escucha escucha = new Escucha(dentro, escuchaHilo, this);
        escuchaHilo=new Thread(escucha);
        escuchaHilo.start();
    }

    private void kick() { //futuro
        System.out.println("Kickeando al usario " + nick + " en " + cSocket.getInetAddress());

        try {
            cSocket.close();
        } catch (IOException ex) {
            System.out.println("Error cerrando Conexion");
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        //quiero matarlooooo pero no seeee :'(
        fuera = null;
        dentro = null;
        cSocket = null;
        nick = null;
        escuchaHilo.interrupt();
        escuchaHilo = null;
    }

    /**
     *
     * @param msg
     */
    public void envia(String msg) {
        fuera.println(msg);
        fuera.flush();
    }

}
