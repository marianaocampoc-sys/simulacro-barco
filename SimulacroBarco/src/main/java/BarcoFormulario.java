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
    //atributos que creo yo
    private Barco barco;
    private BarcoPanel lienzo;
    //constructor
    public BarcoFormulario(){
        barco = new Barco();
        lienzo = new BarcoPanel(barco);
        panelDibujo.setLayout(new BorderLayout());
        panelDibujo.add(lienzo);
        txtEstado.setText("SIN ESTADO");
        txtNombreCap.setText("Capitan patas negras");
        txtVelocidad.setText(String.valueOf(barco.getVelocidad()));
        zarparButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    barco.zarpar();
                    txtEstado.setText("NAVEGANDO");
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
                    txtEstado.setText("DETENIDO");
                    txtVelocidad.setText(String.valueOf(barco.getVelocidad()));
                }
                catch (BarcoException ex){
                    JOptionPane.showMessageDialog(panelPrincipal, ex.getMessage());
                }
            }
        });
        acelerarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    barco.acelerar();
                    txtVelocidad.setText(String.valueOf(barco.getVelocidad()));
                }
                catch (BarcoException ex){
                    JOptionPane.showMessageDialog(panelPrincipal, ex.getMessage());
                }
            }
        });
    }
    //botones
    //form main


    public static void main(String[] args) {
        JFrame frame = new JFrame("BARCO SIMULADOR");
        frame.setContentPane(new BarcoFormulario().panelPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
