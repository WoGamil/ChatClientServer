import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PannelloChatClient extends JFrame implements ActionListener {
    private ThreadChatClient gestioneServizio;
    private JTextField textNuovo;
    //private JTextField textNome; // Campo di testo per inserire il nome del client
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
        panLista.setBackground(new Color(50, 100, 255));
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
        panLista.add(chat1, BorderLayout.CENTER);
        panLista.add(lista, BorderLayout.CENTER);
        panLista.add(chat2, BorderLayout.CENTER);
        
        // Pannello inserimento nuovo messaggio
        JPanel nuovoMex = new JPanel(new BorderLayout(20, 5));
        nuovoMex.setBackground(new Color(50, 100, 255));

        /*JLabel labNome = new JLabel("Nome:"); // Etichetta per il campo del nome
        textNome = new JTextField(""); // Campo di testo per il nome del client
        JLabel labNuovo = new JLabel("Messaggio:"); // Etichetta per il campo del messaggio*/

        textNuovo = new JTextField("");
        
        JButton buttonInvia = new JButton("Invia");
        buttonInvia.addActionListener(this);
        
        // Aggiunge gli oggetti al pannello
        //nuovoMex.add(labNome, BorderLayout.WEST);
        //nuovoMex.add(textNome, BorderLayout.CENTER);
        //nuovoMex.add(labNuovo, BorderLayout.WEST);
        nuovoMex.add(textNuovo, BorderLayout.CENTER);
        nuovoMex.add(buttonInvia, BorderLayout.EAST);

        this.setLayout(new BorderLayout(0, 5));
        add(panLista, BorderLayout.CENTER);
        add(nuovoMex, BorderLayout.SOUTH);

        connetti();
    }

    /**
     * Metodo per connettersi al servizio di chat
     */
    public void connetti() {
        gestioneServizio = new ThreadChatClient(lista, ipServer, porta);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

}
