import java.awt.*;
public class Barco {
    private  final int VELOCIDAD_MAXIMA = 20;
    private boolean encendido;
    private int velocidad;
    private int x;
    private int y;

    public Barco(){
        this.velocidad=5;
        this.encendido=false;
        x=50;
        y=275;

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
        this.velocidad= 5;
    }
    public int acelerar() throws BarcoException{
        if(!encendido){
            throw new BarcoException("No se puede acelerar un barco detenido");
        }
        if(velocidad==VELOCIDAD_MAXIMA){
            throw new BarcoException("Ya no puede acelerar mas, estamos en la velocidad maxima");
        }
        velocidad +=5;
        return velocidad;
    }

    public void dibujarBarco(Graphics g){
        //casco
        g.setColor(new Color(28, 25, 25));
        g.fillRect(x,y,120, 30);
        //cabina
        g.setColor(new Color(227, 213, 213));
        g.fillRect(x+30,y-25,50,25);
        //chimenea
        g.setColor(new Color(152, 139, 66));
        g.fillRect(x+50, y-45, 10,20);
    }

    public boolean isEncendido() {
        return encendido;
    }

    public int getVelocidad() {
        return velocidad;
    }
}
