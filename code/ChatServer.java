import java.awt.*; // Importa le classi per la gestione delle interfacce grafiche
import javax.swing.*; // Importa le classi per la creazione di finestre grafiche

public class ChatServer extends JFrame {
    /**
     * Costruttore della classe ChatServer
     */
    public ChatServer() {
        super("Chat server"); // Imposta il titolo della finestra
        this.setSize(new Dimension(500, 300)); // Imposta le dimensioni della finestra
        this.setLocationRelativeTo(null); // Centra la finestra nello schermo
        this.setEnabled(true); // Imposta la proprietà "enabled"
        this.setBackground(Color.blue); // Imposta il colore dello sfondo della finestra

        // Crea il pannello per l'inserimento e la visualizzazione dei messaggi
        PannelloChatServer pan = new PannelloChatServer();
        this.getContentPane().add(pan); // Aggiunge il pannello alla finestra
        this.setVisible(true); // Rende la finestra visibile
    }
}
