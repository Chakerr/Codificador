package controlador;

import mundo.Codificador;
import java.util.ArrayList;

public class Controlador {
    // Atributos

    // Relaciones 	
    private Codificador codificador;
    // private Transmisor transmisor; (No se usa en este ejemplo)

    public Controlador(ArrayList<String> ips, String direccionArchivo) throws InterruptedException {
        codificador = new Codificador(ips, direccionArchivo); // Pasamos la dirección del archivo al crear el Codificador
    }

    public Codificador getCodificador() {
        return codificador;
    }
}
