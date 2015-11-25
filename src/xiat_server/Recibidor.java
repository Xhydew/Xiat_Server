/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xiat_server;

import java.io.PrintWriter;

/**
 *
 * @author Joan Galiana Magán
 */
public class Recibidor {

    PrintWriter clientes[];

    public void recibido(String recibido, int numCliente) {
        int i = 0;
        for (PrintWriter printer : clientes) {
            if (i != numCliente) {
                printer.println(recibido);
                printer.flush();
            }
            i++;
        }

    }

}
