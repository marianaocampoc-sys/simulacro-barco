package modelo;

import excepciones.*;

import java.awt.*;

public class Barco {
    private static final int VELOCIDAD_HUNDIMIENTO = 40;
    private static final int FRENO_BRUSCO = 20;
    private static final int FRENADO_SIN_RUMBO = 1;

    private int x;
    private int y;
    private boolean navegando;
    private int velocidad;
    private boolean rumboPerdido;
    private Helice helice;
    private Motor motor;

    public Barco(Helice helice, Motor motor) {
        this.helice = helice;
        this.motor = motor;
        x = 100;
        y = 150;
        navegando = false;
        velocidad = 0;
        rumboPerdido = false;
    }

    public void zarpar() throws BarcoException {
        if (navegando) {
            throw new EstadoInvalidoException("El motor ya está encendido, no se puede encender otra vez");
        }
        navegando = true;
    }

    public void apagar() throws BarcoException {
        if (!navegando) {
            throw new EstadoInvalidoException("El motor ya está apagado");
        }
        if (velocidad > VELOCIDAD_HUNDIMIENTO) {
            String mensaje = "Apagaste el motor a " + velocidad + " nudos: ¡el barco se hundió!";
            hundir();
            throw new HundimientoException(mensaje);
        }
        navegando = false;
        velocidad = 0;
        rumboPerdido = false;
    }

    public void acelerar(int intensidad) throws BarcoException {
        if (!navegando) {
            throw new EstadoInvalidoException("No se puede acelerar con el motor apagado");
        }
        if (rumboPerdido) {
            throw new EstadoInvalidoException("El barco perdió el rumbo y no responde hasta detenerse");
        }
        velocidad += intensidad;
        if (velocidad > motor.getVelocidadMaxima()) {
            String mensaje = "Llegaste a " + velocidad + " nudos y el motor solo aguanta "
                    + motor.getVelocidadMaxima() + ": ¡el barco se hundió!";
            hundir();
            throw new HundimientoException(mensaje);
        }
    }

    public void desacelerar(int intensidad) throws BarcoException {
        if (!navegando) {
            throw new EstadoInvalidoException("No se puede desacelerar con el motor apagado");
        }
        if (rumboPerdido) {
            throw new EstadoInvalidoException("El barco perdió el rumbo y no responde hasta detenerse");
        }
        if (velocidad == 0) {
            throw new EstadoInvalidoException("El barco está detenido: frenar no surte efecto");
        }
        if (intensidad > velocidad) {
            rumboPerdido = true;
            throw new RumboPerdidoException("Frenaste " + intensidad + " nudos yendo a " + velocidad
                    + ": ¡el barco perdió el rumbo!");
        }
        if (intensidad > FRENO_BRUSCO && velocidad > helice.getLimite()) {
            rumboPerdido = true;
            throw new RumboPerdidoException("Frenazo brusco a " + velocidad + " nudos y la hélice solo aguanta "
                    + helice.getLimite() + ": ¡el barco perdió el rumbo!");
        }
        velocidad -= intensidad;
    }

    public void echarAncla() throws BarcoException {
        desacelerar(velocidad);
    }

    public void mover(int ancho) {
        if (!navegando) {
            return;
        }
        if (rumboPerdido) {
            velocidad -= FRENADO_SIN_RUMBO;
            if (velocidad <= 0) {
                velocidad = 0;
                rumboPerdido = false;
            }
        }
        x += velocidad;
        if (x > ancho) {
            x = 0;
        }
    }

    private void hundir() {
        navegando = false;
        velocidad = 0;
        rumboPerdido = false;
    }

    public void dibujar(Graphics g) {
        if (rumboPerdido) {
            g.setColor(Color.ORANGE);
        } else {
            g.setColor(new Color(25, 19, 19));
        }
        g.fillRect(x, y, 100, 50);
        g.setColor(new Color(250, 226, 226));
        g.fillRect(x + 25, y - 25, 50, 25);
        g.setColor(new Color(147, 89, 2));
        g.fillRect(x + 45, y - 47, 10, 25);
    }

    public boolean isNavegando() {
        return navegando;
    }

    public boolean isRumboPerdido() {
        return rumboPerdido;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public Helice getHelice() {
        return helice;
    }

    public Motor getMotor() {
        return motor;
    }
}