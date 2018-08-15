/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intercambio;

/**
 *
 * @author Dark
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Intercambio {

    /**
     * @param args the command line arguments
     */
    static ArrayList<Integer> S;

    static int npruebas;

    /**
     * Metodo para contar monedas itilizando algoritmo voraz
     *
     */
    public static ArrayList<Integer> monedas(ArrayList<Integer> C, int M) {
        int total = 0;
        ArrayList<Integer> S = new ArrayList<>();

        while (total < M && !C.isEmpty()) {
            int x = C.get(0);
            if ((total + x) <= M) {
                S.add(x);
                total = total + x;
            } else {
                C.remove(0);
            }
        }

        if (total < M) {
            S.clear();
        }
        return S;
    }

    /**
     * Cargando informacion de archivo
     *
     */
    public static int cargaDatos(String archivo) throws FileNotFoundException, IOException {

        ArrayList<Integer> moneditas = new ArrayList<Integer>();

        int test = 0;
        int monto;
        int cantidadm = 0;

        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        String cadena = "";

        cadena = b.readLine();
        npruebas = Integer.parseInt(cadena);

        while (((cadena = b.readLine()) != null) && test < npruebas) {
            String[] parts = cadena.split(" ");
            // System.out.println(parts[1]);

            monto = Integer.parseInt(parts[0]);
            cantidadm = Integer.parseInt(parts[1]);
            for (int i = 0; i < cantidadm; i++) {
                int a = Integer.parseInt(parts[i + 2]);
                moneditas.add(a);
            }
            Comparator<Integer> comparador = Collections.reverseOrder();
            Collections.sort(moneditas, comparador);
            S = monedas(moneditas, monto);
            System.out.println("Cantidad de monedas para prueba #" + (test + 1) + ":");
            System.out.println(S.size());
            test++;
        }
        b.close();

        return 0;

    }

    /**
     * 
     *
     */
    public static void main(String[] args) throws IOException {
        cargaDatos("input.txt");
    }
}
