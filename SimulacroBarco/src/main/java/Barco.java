public class Barco {
    private  final int VELOCIDAD_MAXIMA = 20;
    private boolean encendido;
    private int velocidad;

    public Barco(){
        this.velocidad=5;
        this.encendido=false;

    }
    public void zarpar() throws BarcoException {
        if(encendido){
            throw new BarcoException("El barco ya esta navegando, dude");
        }
        encendido=true;

    }
    public void detener() throws BarcoException{
        if(!encendido){
            throw new BarcoException("El barco esta detenido, como vas a detener un barco detenido?");
        }
        encendido=false;
        velocidad= 5;
    }
    public void acelerar() throws BarcoException{
        if(!encendido){
            throw new BarcoException("No se puede acelerar un barco detenido");
        }
        if(velocidad==VELOCIDAD_MAXIMA){
            throw new BarcoException("Ya no puede acelerar mas, estamos en la velocidad maxima");
        }
        velocidad +=5;
    }

    public boolean isEncendido() {
        return encendido;
    }

    public int getVelocidad() {
        return velocidad;
    }
}
