public class Barco {
    private  final int VELOCIDAD_MAXIMA = 20;
    private boolean encendido;
    private int velocidad;

    public Barco(){
        this.velocidad=5;
        this.encendido=false;

    }
    public boolean zarpar() throws BarcoException {
        if(encendido){
            throw new BarcoException("El barco ya esta navegando, dude");
        }
        encendido=true;
        return true;
    }
    public boolean detener() throws BarcoException{
        if(!encendido){
            throw new BarcoException("El barco esta detenido, como vas a detener un barco detenido?");
        }
        encendido=false;
        return true;
    }
    public void acelerar(int ancho) throws BarcoException{
        if(!encendido){
            throw new BarcoException("No se puede acelerar un barco detenido");
        }
        velocidad +=5;
    }

    public boolean isEncendido() {
        return encendido;
    }
}
