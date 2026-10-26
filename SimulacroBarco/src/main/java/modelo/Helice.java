package modelo;

import excepciones.ConfiguracionException;

public class Helice {
    private String tipo;
    private int limite;

    public Helice(String tipo) throws ConfiguracionException {
        this.tipo = tipo;
        switch (tipo) {
            case "Bronce":
                limite = 60;
                break;
            case "Acero":
                limite = 45;
                break;
            case "Plastico":
                limite = 30;
                break;
            default:
                throw new ConfiguracionException("Tipo de hélice desconocido: " + tipo);
        }
    }

    public String getTipo() {
        return tipo;
    }

    public int getLimite() {
        return limite;
    }
}