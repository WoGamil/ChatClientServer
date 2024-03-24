import java.awt.*; // Importa le classi per la gestione delle interfacce grafiche
import java.net.*; // Importa le classi per la gestione delle connessioni di rete
import java.io.*; // Importa le classi per la gestione degli stream di input/output
import javax.swing.*; // Importa le classi per la creazione di finestre grafiche

public class ThreadChatClient implements Runnable {
    private List lista; // Lista per visualizzare i messaggi della chat
    Thread me; // Thread corrente
    private Socket client; // Socket per la connessione al server
    private BufferedReader input = null; // BufferedReader per leggere i messaggi dal server
    private PrintWriter output = null; // PrintWriter per inviare i messaggi al server

    /**
     * Costruttore della classe ThreadChatClient
     * @param lista Lista per visualizzare i messaggi della chat
     * @param ipServer Indirizzo IP del server
     * @param porta Porta del server
     */
    public ThreadChatClient(List lista, String ipServer, int porta) {
        this.lista = lista;
        try {
            // Inizializza la connessione al server tramite un socket
            client = new Socket(ipServer, porta);
            this.input = new BufferedReader(new InputStreamReader(client.getInputStream()));
            this.output = new PrintWriter(client.getOutputStream(), true);
        } catch (Exception e) {
            // Gestisce eventuali eccezioni durante la connessione al server
            JOptionPane.showMessageDialog(null, "Impossibile connettersi al server");
        }
        me = new Thread(this);
        me.start(); // Avvia il thread corrente
    }

    // Metodo run richiesto per implementare l'interfaccia Runnable
    public void run() {
        // Attende le ricezioni di messaggi dal server e aggiunge i messaggi alla lista
        try {
            String mex = null;
            while ((mex = input.readLine()) != null) {
                // Aggiunge il messaggio ricevuto alla lista dei messaggi
                lista.add(mex);
                lista.select(lista.getItemCount()-1);
            }
        } catch (Exception e) {
            // Gestisce eventuali eccezioni durante la lettura dei messaggi dal server
            //Empty
        }
    }

    /**
     * Metodo per inviare un messaggio al server
     * @param messaggio Messaggio da inviare
     */
    public void spedisciMessaggioChat(String messaggio) {
        try {
            // Invia il messaggio al server tramite il PrintWriter
            output.println(messaggio);
        } catch (Exception e) {
            // Gestisce eventuali eccezioni durante l'invio del messaggio al server
            //Empty
        }
    }
}
