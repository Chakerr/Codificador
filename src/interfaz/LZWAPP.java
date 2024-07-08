package interfaz;

import controlador.Controlador;
import java.util.ArrayList;

public class LZWAPP {

    private Controlador ctrl;

    public LZWAPP(Controlador ctrl) throws InterruptedException {
        this.ctrl = ctrl;
    }

    public static void main(String[] args) throws InterruptedException {
        ArrayList<String> ips = new ArrayList<>();
        // Agrega las direcciones IP predeterminadas aquí
        ips.add("192.168.1.3");

        String direccionArchivo = "C:\\Users\\mario\\Documents\\LZW.txt"; // Cambia la dirección según sea necesario
        new LZWAPP(new Controlador(ips, direccionArchivo));
    }
}
