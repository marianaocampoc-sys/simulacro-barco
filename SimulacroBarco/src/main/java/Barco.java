import java.awt.*;
public class Barco {
    //atributos
    private final static int VELOCIDAD_MAXIMA= 20;
    private int x;
    private int y;
    private boolean navegando;
    private int velocidad;

    //constructor que en este caso inicializa x pero generalmente recibe x y y
    public Barco(){
        x=100;
        y=150;
        navegando=false;
        velocidad=5;
    }
    //metodos
    public void zarpar() throws BarcoException{
        if (navegando){
            throw new BarcoException("El barco ya esta navegando, no lo puedes zarpar otra vez");
        }
        navegando=true;

    }
    public void detener()throws BarcoException{
        if (!navegando){
            throw new BarcoException("El barco ya esta detenido, no se puede volver a detener");
        }
        navegando = false;
        velocidad=5;
    }
    public void acelerar()throws BarcoException{
        if(!navegando){
            throw new BarcoException("No se puede acelerar un barco detenido");
        }
        if(velocidad==VELOCIDAD_MAXIMA){
            throw new BarcoException("No se puede acelerar mas, estamos en nuestra maxima velocidad");
        }
        velocidad += 5;

    }
    public void mover(int ancho){
        if(!navegando){
           return;
        }
        x +=velocidad;
        if(x>ancho){
            x = 0;
        }
    }
    //dibujar que recibe graphics g pero no override
    protected void dibujar(Graphics g){
        //siendo muy honesta no he agarrado todavia la logica de dibujar
        //casco
        g.setColor(new Color(25, 19, 19));
        g.fillRect(x,y,100,50);
        //cabina
        g.setColor(new Color(250, 226, 226));
        g.fillRect(x+25,y-25, 50, 25);
        //chimenea
        g.setColor(new Color(147, 89, 2));
        g.fillRect(x+45, y - 47, 10, 25);

    }

    //getters

    public boolean isNavegando() {
        return navegando;
    }

    public int getVelocidad() {
        return velocidad;
    }
}