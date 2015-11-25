/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xiat_server;

import java.io.PrintWriter;
import java.util.Map;

/**
 *
 * @author Joan Galiana Magán
 */
public class Recibidor {

    public Recibidor(Map<Integer, PrintWriter> mapPrintClientes) {
        clientes.equals(mapPrintClientes);
    }

    private Map<Integer, PrintWriter> clientes;

    public void recibido(String recibido, int numCliente) {
        for (Integer cliente : clientes.keySet()) {
            if (cliente != numCliente) {
                PrintWriter printer = clientes.get(cliente);
                if (printer != null) {
                    printer.println(recibido);
                    printer.flush();
                }
            }
        }

    }

}
