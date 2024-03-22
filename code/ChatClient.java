import java.awt.*;
import javax.swing.*;

public class ChatClient extends JFrame {

    /**
     * 
     */
    public ChatClient () {
        super("Chat client");
        this.setSize(new Dimension(500, 300)); // setto la grandezza della finestra
        this.setLocationRelativeTo(null);      // la metto al centro dello schermo
        this.setEnabled(true);                 // setto la proprietà enabled
        this.setBackground(Color.blue);        // setto il colore dello sfondo

        // creo il pannello per l'inserimento e la visualizzazione dei messaggi
        PannelloChatClient pan = new PannelloChatClient();
        this.getContentPane().add(pan);
        this.setVisible(true);
    }

}
