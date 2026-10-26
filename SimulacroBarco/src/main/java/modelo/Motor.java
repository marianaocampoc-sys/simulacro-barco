package modelo;

import excepciones.ConfiguracionException;

public class Motor {
    private int cilindraje;
    private int velocidadMaxima;

    public Motor(int cilindraje) throws ConfiguracionException {
        this.cilindraje = cilindraje;
        switch (cilindraje) {
            case 500:
                velocidadMaxima = 50;
                break;
            case 1000:
                velocidadMaxima = 80;
                break;
            case 1500:
                velocidadMaxima = 120;
                break;
            default:
                throw new ConfiguracionException("No se reconoce este motor: " + cilindraje);
        }
    }

    public int getCilindraje() {
        return cilindraje;
    }

    public int getVelocidadMaxima() {
        return velocidadMaxima;
    }
}