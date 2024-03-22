import java.awt.*;
import javax.swing.*;

public class ChatServer extends JFrame {
    /**
     * 
     */
    public ChatServer () {
        super("Chat server");
        this.setSize(new Dimension(500, 300)); // setto la grandezza della finestra
        this.setLocationRelativeTo(null);      // la metto al centro dello schermo
        this.setEnabled(true);                 // setto la proprietà enabled
        this.setBackground(Color.blue);        // setto il colore dello sfondo

        // creo il pannello per l'inserimento e la visualizzazione dei messaggi
        PannelloChatServer pan = new PannelloChatServer();
        this.getContentPane().add(pan);
        this.setVisible(true);
    }
}
