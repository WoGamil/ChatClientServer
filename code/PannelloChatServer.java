import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PannelloChatServer extends JFrame implements ActionListener {
    private ThreadGestioneServizoChat gestioneServizio;
    private JTextField textNuovo;
    
    public PannelloChatServer() {
        super();
        this.setBackground(new Color(50, 100, 255));
        // pannello superiore: lista messaggi
        JPanel panLista = new JPanel(new BorderLayout(20, 5));
        panLista.setBackground(new Color(50, 100, 255));
        List lista = new List();
        lista.setBackground(Color.lightGray);
        lista.setSize(100, 50);
        lista.setVisible(true);
        // scritte laterali
        JLabel chat1 = new JLabel("  Chat  ", JLabel.CENTER);
        chat1.setForeground(new Color(200, 100, 100));
        JLabel chat2 = new JLabel("  Chat  ", JLabel.CENTER);
        chat2.setForeground(new Color(200, 100, 100));
        // aggiungiamo gli oggetti sul pannello
        panLista.add(chat1, BorderLayout.CENTER);
        panLista.add(lista, BorderLayout.CENTER);
        panLista.add(chat2, BorderLayout.CENTER);
        // pannello inserimento nuovo messaggio
        JPanel nuovoMex = new JPanel(new BorderLayout(20, 5));
        nuovoMex.setBackground(new Color(50, 100, 255));

        JLabel labNuovo = new JLabel("Nuovo Messaggio -> ", JLabel.CENTER);
        nuovoMex.setBackground(new Color(50, 100, 255));

        textNuovo = new JTextField("");

        JButton buttonInvia = new JButton("Invia");
        buttonInvia.addActionListener(this);
        // aggiungiamo gli oggetti sul pannello
        nuovoMex.add(labNuovo, BorderLayout.WEST);
        nuovoMex.add(textNuovo, BorderLayout.CENTER);
        nuovoMex.add(buttonInvia, BorderLayout.EAST);

        this.setLayout(new BorderLayout(0, 5));
        add(panLista, BorderLayout.CENTER);
        add(nuovoMex, BorderLayout.SOUTH);

        connetti();
    }// Fine costruttore classe PannelloChat

    public void connetti() {
        // instanzio il Thread per le connessioni : numero massimo player = 10
        gestioneServizio = new ThreadGestioneServizoChat(10, null);
    }

    public void actionPerformed(ActionEvent e) {
        String bottone = e.getActionCommand();
        if(bottone.equals("Invia")) {
            gestioneServizio.spedisciMessaggio(textNuovo.getText());
            textNuovo.setText("");
        }
    }
}
