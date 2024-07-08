package mundo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Codificador {

    private ArrayList<String> lista;
    private HashMap<Integer, String> diccionario;
    int contador;

    /* Constructor */
    public Codificador(ArrayList<String> ips, String direccionArchivo) throws InterruptedException {
        lista = new ArrayList();
        diccionario = new HashMap();
        for (int i = 0; i < 256; i++) {
            diccionario.put(i, (char) i + "");
        }
        contador = 256;
        leerTxt(direccionArchivo); // Pasamos la dirección del archivo a leerTxt
        codificar(ips);
    }
    
    public void leerTxt(String direccion) {
        // String nombreArchivo = "C:\\Users\\mario\\Documents\\LZW.txt"; // Ya no es necesario
        String salida = "";
        //ArrayList<String> lineas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(direccion))) { // Usamos la dirección proporcionada
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.length() > 0) {
                    lista.add(linea + "\n");
                } else {
                    lista.add("\n");
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        while (!lista.isEmpty()) {
            salida += lista.remove(0);
        }
        String aux = salida.substring(0, salida.length() - 1);
        String texto[] = aux.split("");
        for (int i = 0; i < texto.length; i++) {
            lista.add(texto[i]);
        }
        lista.add("fin");
    }

    public void codificar(ArrayList<String> ip) throws InterruptedException {
        Transmisor transmisor = new Transmisor();
        String PE, SE, PS;
        int index = 0;
        PE = lista.get(index);
        SE = lista.get(++index);
        while (!PE.equals("fin")) {
            PS = PE + SE;
            if (!diccionario.containsValue(PS)) {
                if (!PS.contains("fin")) {
                    diccionario.put(contador, PS);
                    contador++;
                }

                for (int i = 0; i < ip.size(); i++) {
                    System.out.print(valorLLave(PE) + " ");
                    transmisor.socket(valorLLave(PE) + "", ip.get(i));
                }
                Thread.sleep(500);
                PE = lista.get(index);
                if (PE.equals("fin")) {
                    break;
                }
                SE = lista.get(++index);
            } else {
                PE = PS;
                SE = lista.get(++index);
            }
        }
    }

    private int valorLLave(String valor) {
        for (Map.Entry<Integer, String> entry : diccionario.entrySet()) {
            if (entry.getValue().equals(valor)) {
                return entry.getKey();
            }
        }
        return 0;
    }
}
