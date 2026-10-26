package modelo;

import excepciones.ConfiguracionException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Astillero {

    public static Barco construir(String ruta) throws ConfiguracionException {
        List<String> lineas;
        try {
            lineas = Files.readAllLines(Path.of(ruta));
        } catch (IOException ex) {
            throw new ConfiguracionException("No se pudo leer el archivo " + ruta);
        }

        String tipoHelice = null;
        int cilindraje = 0;

        for (String linea : lineas) {
            String[] partes = linea.trim().split("\\s+");
            if (partes.length < 2) {
                continue;
            }
            switch (partes[0]) {
                case "helice":
                    tipoHelice = partes[1];
                    break;
                case "motor":
                    try {
                        cilindraje = Integer.parseInt(partes[1]);
                    } catch (NumberFormatException ex) {
                        throw new ConfiguracionException("El motor debe ser un número: " + partes[1]);
                    }
                    break;
            }
        }

        if (tipoHelice == null || cilindraje == 0) {
            throw new ConfiguracionException("Al archivo le falta la hélice o el motor");
        }
        return new Barco(new Helice(tipoHelice), new Motor(cilindraje));
    }

    public static Barco porDefecto() {
        try {
            return new Barco(new Helice("Acero"), new Motor(1000));
        } catch (ConfiguracionException ex) {
            return null;
        }
    }
}