import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PannelloChatClient extends JPanel implements ActionListener {

    private ThreadChatClient gestioneServizio;
    private JTextField textNuovo;
    private List lista;
    private String ipServer = "localhost";
    private int porta = 6789;

    /**
     * Costruttore della classe PannelloChatClient
     */
    public PannelloChatClient() {
        super();
        this.setBackground(new Color(50, 100, 255));
        
        // Pannello superiore: lista messaggi
        JPanel panLista = new JPanel(new BorderLayout(20, 5));
        panLista.setBackground(new Color(255, 204, 51));
        List lista = new List();
        lista.setBackground(Color.lightGray);
        lista.setSize(100, 50);
        lista.setVisible(true);
        
        // Etichette laterali
        JLabel chat1 = new JLabel("  Chat  ", JLabel.CENTER);
        chat1.setForeground(new Color(200, 100, 100));
        JLabel chat2 = new JLabel("  Chat  ", JLabel.CENTER);
        chat2.setForeground(new Color(200, 100, 100));
        
        // Aggiunge gli oggetti al pannello
        panLista.add(chat1, BorderLayout.WEST);
        panLista.add(lista, BorderLayout.CENTER);
        panLista.add(chat2, BorderLayout.EAST);
        
        // Pannello inserimento nuovo messaggio
        JPanel nuovoMex = new JPanel(new BorderLayout(20, 5));
        nuovoMex.setBackground(new Color(50, 100, 255));

        JLabel labNuovo = new JLabel("Nuovo Messaggio -> ",JLabel.CENTER);
	    labNuovo.setForeground(new Color(255,255,0));

        textNuovo = new JTextField("");
        
        JButton buttonInvia = new JButton("Invia");
        buttonInvia.addActionListener(this);
        
        // Aggiunge gli oggetti al pannello
        nuovoMex.add(labNuovo,BorderLayout.WEST);
        nuovoMex.add(textNuovo, BorderLayout.CENTER);
        nuovoMex.add(buttonInvia, BorderLayout.EAST);

        this.setLayout(new BorderLayout(0, 5));
        add(panLista, BorderLayout.CENTER);
        add(nuovoMex, BorderLayout.SOUTH);

        connettiAlServer();
    }

    /**
     * Metodo per connettersi al servizio di chat
     */
    public void connettiAlServer() {
        gestioneServizio = new ThreadChatClient(lista, ipServer, porta);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String bottone = e.getActionCommand(); // Ottiene il comando dell'azione
        if(bottone.equals("Invia")) { // Se l'azione è "Invia"
            gestioneServizio.spedisciMessaggioChat(textNuovo.getText()); // Invia il messaggio al server
            textNuovo.setText(""); // Cancella il testo nel campo di testo
        }
    }
}
