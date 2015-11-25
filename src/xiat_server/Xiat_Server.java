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
public class Xiat_Server {

    private final static int puerto = 5050;

    private String teclao(Scanner telcao) {
        return telcao.nextLine();
    }

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        ServerSocket servSoc = new ServerSocket(puerto); //Crea Socket de servidor
        System.err.println("asd");
        Socket cliSock = servSoc.accept();//Establece conexion con el cliente
        System.err.println("Conectado");//Muestra por el canal de error al conectar con cliente

        PrintWriter fuera = new PrintWriter(cliSock.getOutputStream());//Canal para enviar
        Scanner dentro = new Scanner(cliSock.getInputStream());//Canal para recibir

        do {
            System.out.println("Recibido: " + dentro.nextLine()
                    + "\t \t de :" + cliSock.getInetAddress());// Muesta recibido, con direccion del 
        } while (cliSock.isConnected());//mientras que exista conexion

    }

}
