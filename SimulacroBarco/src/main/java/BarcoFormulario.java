import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BarcoFormulario {
    //creados automaticamente
    private JPanel panelPrincipal;
    private JTextField txtNombreCap;
    private JTextField txtEstado;
    private JLabel lblCapitan;
    private JLabel lblEstado;
    private JButton zarparButton;
    private JButton detenerButton;
    private JButton acelerarButton;
    private JPanel panelDibujo;
    private JLabel lblVelocidad;
    private JTextField txtVelocidad;
    //los que yo creo
    private Barco barco;
    private BarcoPanel lienzo;

    public BarcoFormulario(){
        barco = new Barco();
        lienzo = new BarcoPanel(barco);
        panelDibujo.setLayout(new BorderLayout());
        panelDibujo.add(lienzo);
        lblEstado.setText("SIN ESTADO");
        zarparButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    barco.zarpar();
                    lblEstado.setText("ESTADO: ENCENDIDO");
                }
                catch (BarcoException ex){
                    JOptionPane.showMessageDialog(panelPrincipal, ex.getMessage());
                }
            }
        });
        detenerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    barco.detener();
                    lblEstado.setText("ESTADO: APAGADO");
                }
                catch (BarcoException ex){
                    JOptionPane.showMessageDialog(panelPrincipal, ex.getMessage());
                }
            }
        });
        acelerarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    barco.acelerar();
                    lblEstado.setText("ESTADO ACELERANDO" );
                }
                catch (BarcoException ex){
                    JOptionPane.showMessageDialog(panelPrincipal, ex.getMessage());
                }

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("SIMULADOR BARCO");
        frame.setContentPane(new BarcoFormulario().panelPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
