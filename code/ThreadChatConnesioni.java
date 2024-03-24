import java.net.*; // Importa le classi per la gestione delle connessioni di rete
import java.io.*; // Importa le classi per la gestione degli stream di input/output

public class ThreadChatConnesioni implements Runnable {

    private ThreadGestioneServizoChat gestoreChat; // Riferimento al gestore del servizio di chat
    private Socket client = null; // Socket per la comunicazione con il client
    private BufferedReader input = null; // BufferedReader per leggere i messaggi dal client
    private PrintWriter output = null; // PrintWriter per inviare i messaggi al client
    Thread me; // Thread corrente

    /**
     * Costruttore della classe ThreadChatConnesioni
     * @param gestoreChat Riferimento al gestore del servizio di chat
     * @param client Socket per la comunicazione con il client
     */
    public ThreadChatConnesioni (ThreadGestioneServizoChat gestoreChat, Socket client) {
        this.gestoreChat = gestoreChat;
        this.client = client;
        try {
            // Inizializza gli stream di input/output per la comunicazione con il client
            this.input = new BufferedReader(new InputStreamReader(client.getInputStream()));
            this.output = new PrintWriter(this.client.getOutputStream(), true);
        } catch (Exception e) {
            // Gestisce eventuali eccezioni durante l'inizializzazione degli stream
            output.println("Errore nella lettura");
        }
        me = new Thread(this);
        me.start(); // Avvia il thread corrente
    }

    // Metodo run richiesto per implementare l'interfaccia Runnable
    public void run() {
        while (true) {
            try {
                String mex = null;
                // Rimane in attesa dei messaggi mandati dal client
                while ((mex = input.readLine()) == null) {
                    // Attendere finché non si riceve un messaggio
                }
                // Invoca il metodo del gestoreChat per ripetere a tutti il messaggio ricevuto
                gestoreChat.spedisciMessaggio(mex);
            } catch (Exception e) {
                // Gestisce eventuali eccezioni durante l'invio del messaggio a tutti i client
                output.println("Errore nella spedizione del messaggio a tutti.");
            }
        }
    }

    // Metodo per spedire un messaggio al client corrente
    public void spedisciMessaggioChat(String messaggio) {
        try {
            output.println(messaggio);
        } catch (Exception e) {
            // Gestisce eventuali eccezioni durante l'invio del messaggio al client corrente
            output.println("Errore nella spedizione del singolo messaggio.");
        }
    }
}
