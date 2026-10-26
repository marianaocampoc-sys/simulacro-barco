package vista;

import modelo.Barco;

import javax.swing.*;
import java.awt.*;

public class BarcoPanel extends JPanel {
    private Barco barco;
    private Timer timer;

    public BarcoPanel(Barco barco) {
        this.barco = barco;
        setPreferredSize(new Dimension(600, 400));
        setBackground(new Color(18, 57, 96));
        timer = new Timer(20, e -> {
            this.barco.mover(getWidth());
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(14, 46, 128));
        g.fillRect(0, 200, getWidth(), getHeight());
        barco.dibujar(g);
    }
}