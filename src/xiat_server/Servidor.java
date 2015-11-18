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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joan Galiana MagÃ¡n
 */
public class Servidor {

    private final static int puerto = 5050;

    private ServerSocket servSoc;
    private Socket cliSock[] = new Socket[2];
    private PrintWriter fuera[] = new PrintWriter[2];
    private Scanner dentro[] = new Scanner[2];
    private Thread hiloCli[] = new Thread[2];
    private Cliente cliente[] = new Cliente[2];

    public Servidor() {
        try {
            servSoc = new ServerSocket(puerto); //Crea Socket de servidor

            conecta(0);
            conecta(1);

            objetos();

            startCom();

        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void conecta(int i) throws IOException {
        cliSock[i] = servSoc.accept();//Establece conexion con el cliente

        dentro[i] = new Scanner(cliSock[i].getInputStream());//Canal para recibir
        fuera[i] = new PrintWriter(cliSock[i].getOutputStream());//Canal para enviar   

//<editor-fold defaultstate="collapsed" desc="FeedBackConexion">
        if (i == 0) {
            fuera[0].println("Esperando a segundo cliente...");
            fuera[0].flush();
        } else {
            fuera[0].println("Ha aparecido un segundo cliente, conectando...");
            fuera[0].flush();

            fuera[1].println("Ya hay un cliente esperando, conectando...");
            fuera[1].flush();
        }
//</editor-fold>

    }

    private void objetos() {

        cliente[0] = new Cliente(dentro[0], fuera[1]);
        cliente[1] = new Cliente(dentro[1], fuera[0]);

        hiloCli[0] = new Thread(cliente[0]);
        hiloCli[1] = new Thread(cliente[1]);
    }

    private void startCom() {
        hiloCli[0].start();
        hiloCli[1].start();
    }

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
//<editor-fold defaultstate="collapsed" desc="Main">
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        ServerSocket servSoc = new ServerSocket(puerto); //Crea Socket de servidor

        Socket cliSock = servSoc.accept();//Establece conexion con el cliente
        System.err.println("Conectado");//Muestra por el canal de error al conectar con cliente

        PrintWriter fuera = new PrintWriter(cliSock.getOutputStream());//Canal para enviar
        Scanner dentro = new Scanner(cliSock.getInputStream());//Canal para recibir

        do {
            System.out.println("Recibido: " + dentro.nextLine()
                    + "\t \t de :" + cliSock.getInetAddress());// Muesta recibido, con direccion del
        } while (cliSock.isConnected());//mientras que exista conexion

    }
//</editor-fold>

}
