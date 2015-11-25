/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xiat_server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Joan Galiana MagÃ¡n
 */
public class Server {

    ServerSocket servSoc;
    Socket cliSock;
    PrintWriter fuera;
    Scanner dentro;

    private final static int puerto = 5050;

    public Server() throws IOException {
        conecta();
        lee();
    }

    private String teclao(Scanner telcao) {
        return telcao.nextLine();
    }

    private void lee() {
        do {
            System.out.println("Recibido: " + dentro.nextLine()
                    + "\t \t de :" + cliSock.getInetAddress());// Muesta recibido, con direccion del 
        } while (cliSock.isConnected());
    }

    private void conecta() throws IOException {
        servSoc = new ServerSocket(puerto);//Crea Socket de servidor a la escucha
        cliSock = servSoc.accept();//Establece conexion con el cliente

        fuera = new PrintWriter(cliSock.getOutputStream());//Canal para enviar
        dentro = new Scanner(cliSock.getInputStream());//Canal para recibir      
        servSoc.close();
    }

}
