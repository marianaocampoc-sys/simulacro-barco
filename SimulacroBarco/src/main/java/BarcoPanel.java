import javax.swing.*;
import java.awt.*;
public class BarcoPanel extends JPanel {
    //atributos
    private Barco barco;
    private Timer timer;

    //constructor que recibe barco
    public BarcoPanel(Barco barco){
        this.barco=barco;
        //este me da error porque no reconoce el setPreferedSize
        setPreferredSize(new Dimension(600,400));
        setBackground(new Color(18, 57, 96));
        timer = new Timer(20,e -> {
            barco.mover(getWidth());
            repaint();
        });
        timer.start();
    }
    //timer
    @Override
    protected void paintComponent (Graphics g){
        super.paintComponent(g);
        //mar
        g.setColor(new Color(14, 46, 128));
        g.fillRect(0,200,getWidth(),getHeight());
        //barco
        barco.dibujar(g);
    }
    //override con el dibujo del panel y dentro de este el barquito
}