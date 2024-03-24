import java.awt.*; // Importa le classi per la gestione delle interfacce grafiche
import java.awt.event.*; // Importa le classi per la gestione degli eventi
import javax.swing.*; // Importa le classi per la creazione di finestre grafiche

public class PannelloChatServer extends JFrame implements ActionListener {
    private ThreadGestioneServizoChat gestioneServizio; // Gestore del servizio di chat
    private JTextField textNuovo; // Campo di testo per l'inserimento di nuovi messaggi

    /**
     * Costruttore della classe PannelloChatServer
     */
    public PannelloChatServer() {
        super();
        this.setBackground(new Color(50, 100, 255)); // Imposta il colore di sfondo della finestra
        // Pannello superiore: lista messaggi
        JPanel panLista = new JPanel(new BorderLayout(20, 5));
        panLista.setBackground(new Color(50, 100, 255)); // Imposta il colore di sfondo del pannello
        List lista = new List(); // Lista per visualizzare i messaggi della chat
        lista.setBackground(Color.lightGray); // Imposta il colore di sfondo della lista
        lista.setSize(100, 50);
        lista.setVisible(true);
        // Etichette laterali
        JLabel chat1 = new JLabel("  Chat  ", JLabel.CENTER);
        chat1.setForeground(new Color(200, 100, 100)); // Imposta il colore del testo dell'etichetta
        JLabel chat2 = new JLabel("  Chat  ", JLabel.CENTER);
        chat2.setForeground(new Color(200, 100, 100)); // Imposta il colore del testo dell'etichetta
        // Aggiunge gli oggetti al pannello
        panLista.add(chat1, BorderLayout.CENTER);
        panLista.add(lista, BorderLayout.CENTER);
        panLista.add(chat2, BorderLayout.CENTER);
        // Pannello inserimento nuovo messaggio
        JPanel nuovoMex = new JPanel(new BorderLayout(20, 5));
        nuovoMex.setBackground(new Color(50, 100, 255)); // Imposta il colore di sfondo del pannello

        JLabel labNuovo = new JLabel("Nuovo Messaggio -> ", JLabel.CENTER);
        nuovoMex.setBackground(new Color(50, 100, 255)); // Imposta il colore di sfondo del pannello

        textNuovo = new JTextField(""); // Campo di testo per l'inserimento di nuovi messaggi

        JButton buttonInvia = new JButton("Invia"); // Bottone per inviare il messaggio
        buttonInvia.addActionListener(this); // Aggiunge il listener per gestire gli eventi del bottone
        // Aggiunge gli oggetti al pannello
        nuovoMex.add(labNuovo, BorderLayout.WEST);
        nuovoMex.add(textNuovo, BorderLayout.CENTER);
        nuovoMex.add(buttonInvia, BorderLayout.EAST);

        this.setLayout(new BorderLayout(0, 5)); // Imposta il layout della finestra
        add(panLista, BorderLayout.CENTER); // Aggiunge il pannello della lista alla finestra
        add(nuovoMex, BorderLayout.SOUTH); // Aggiunge il pannello di inserimento messaggi alla finestra

        connetti(); // Avvia la connessione al server
    }

    /**
     * Metodo per stabilire la connessione al server
     */
    public void connetti() {
        // Istanzia il gestore del servizio di chat con un massimo di 10 connessioni
        gestioneServizio = new ThreadGestioneServizoChat(10, null);
    }

    /**
     * Metodo per gestire gli eventi
     * @param e L'evento generato
     */
    public void actionPerformed(ActionEvent e) {
        String bottone = e.getActionCommand(); // Ottiene il comando dell'azione
        if(bottone.equals("Invia")) { // Se l'azione è "Invia"
            gestioneServizio.spedisciMessaggio(textNuovo.getText()); // Invia il messaggio al server
            textNuovo.setText(""); // Cancella il testo nel campo di testo
        }
    }
}
