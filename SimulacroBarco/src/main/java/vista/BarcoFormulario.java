package vista;

import excepciones.BarcoException;
import excepciones.ConfiguracionException;
import modelo.Astillero;
import modelo.Barco;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BarcoFormulario {
    private JPanel panelPrincipal;
    private JPanel panelDibujo;
    private JLabel lblCapitan;
    private JLabel lblEstado;
    private JLabel lblVelocidad;
    private JLabel lblRumbo;
    private JLabel lblHelice;
    private JLabel lblMotor;
    private JLabel lblIntensidad;
    private JTextField txtNombreCap;
    private JTextField txtEstado;
    private JTextField txtVelocidad;
    private JTextField txtRumbo;
    private JTextField txtHelice;
    private JTextField txtMotor;
    private JTextField txtIntensidad;
    private JButton encenderButton;
    private JButton apagarButton;
    private JButton acelerarButton;
    private JButton desacelerarButton;
    private JButton anclarButton;

    private Barco barco;
    private BarcoPanel lienzo;

    public BarcoFormulario() {
        try {
            barco = Astillero.construir("barco.txt");
        } catch (ConfiguracionException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage() + "\nSe usarán valores por defecto.");
            barco = Astillero.porDefecto();
        }

        lienzo = new BarcoPanel(barco);
        panelDibujo.setLayout(new BorderLayout());
        panelDibujo.add(lienzo);

        if (txtHelice != null) {
            txtHelice.setText(barco.getHelice().getTipo() + " (límite " + barco.getHelice().getLimite() + ")");
        }
        if (txtMotor != null) {
            txtMotor.setText(barco.getMotor().getCilindraje() + " cc (máximo " + barco.getMotor().getVelocidadMaxima() + ")");
        }

        actualizarEtiquetas();
        new Timer(100, e -> actualizarEtiquetas()).start();

        encenderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    barco.zarpar();
                } catch (BarcoException ex) {
                    JOptionPane.showMessageDialog(panelPrincipal, ex.getMessage());
                }
            }
        });

        apagarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    barco.apagar();
                } catch (BarcoException ex) {
                    JOptionPane.showMessageDialog(panelPrincipal, ex.getMessage());
                }
            }
        });

        acelerarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int intensidad = Integer.parseInt(txtIntensidad.getText().trim());
                    barco.acelerar(intensidad);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(panelPrincipal, "La intensidad debe ser un número entero");
                } catch (BarcoException ex) {
                    JOptionPane.showMessageDialog(panelPrincipal, ex.getMessage());
                }
            }
        });

        desacelerarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int intensidad = Integer.parseInt(txtIntensidad.getText().trim());
                    barco.desacelerar(intensidad);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(panelPrincipal, "La intensidad debe ser un número entero");
                } catch (BarcoException ex) {
                    JOptionPane.showMessageDialog(panelPrincipal, ex.getMessage());
                }
            }
        });

        anclarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    barco.echarAncla();
                } catch (BarcoException ex) {
                    JOptionPane.showMessageDialog(panelPrincipal, ex.getMessage());
                }
            }
        });
    }

    private void actualizarEtiquetas() {
        if (barco.isNavegando()) {
            txtEstado.setText("ENCENDIDO");
        } else {
            txtEstado.setText("APAGADO");
        }
        txtVelocidad.setText(barco.getVelocidad() + " nudos");
        if (txtRumbo != null) {
            if (barco.isRumboPerdido()) {
                txtRumbo.setText("PERDIDO");
            } else {
                txtRumbo.setText("CONTROLADO");
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("BARCO SIMULADOR");
        frame.setContentPane(new BarcoFormulario().panelPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}