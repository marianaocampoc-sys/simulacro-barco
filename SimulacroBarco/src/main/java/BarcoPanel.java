import javax.swing.*;
import java.awt.*;
public class BarcoPanel extends JPanel {
    private Barco barco;
    private Timer timer;

    public BarcoPanel(Barco barco){
        setPreferredSize(new Dimension(600,400));
        setBackground(Color.CYAN);
        this.barco = barco;
        timer = new Timer(20, e -> {
            barco.acelerar(getWidth());

        });

    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

    }
}
