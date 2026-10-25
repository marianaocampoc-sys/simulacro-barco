import javax.swing.*;
import java.awt.*;
public class BarcoPanel extends JPanel {
    private Barco barco;
    private Timer timer;

    public BarcoPanel(Barco barco){
        setPreferredSize(new Dimension(600,400));
        setBackground(new Color(50, 52, 81));
        this.barco = barco;
        timer = new Timer(20, e -> {
            barco.mover(getWidth());
            repaint();

        });
        timer.start();
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        barco.dibujarBarco(g);
        //mar
        g.setColor(new Color(1, 64, 255));
        g.fillRect(0,300, getWidth(), getHeight());

    }
}
