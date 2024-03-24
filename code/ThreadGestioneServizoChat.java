import java.awt.List; // Importa la classe List dall'API AWT
import java.net.ServerSocket; // Importa la classe ServerSocket per creare un server
import java.net.Socket; // Importa la classe Socket per la comunicazione di rete
import javax.swing.JOptionPane; // Importa la classe JOptionPane per mostrare finestre di dialogo

public class ThreadGestioneServizoChat implements Runnable {
  
  private int nrMaxConnessioni; // Numero massimo di connessioni consentite
  private List lista; // Lista per memorizzare i messaggi della chat
  private ThreadChatConnesioni[] listaConnessioni; // Array di thread per gestire le connessioni
  Thread me; // Thread corrente
  private ServerSocket serverChat; // ServerSocket per accettare connessioni

  /**
   * Costruttore della classe ThreadGestioneServizoChat
   * @param numeroMaxConnessioni Numero massimo di connessioni consentite
   * @param lista Lista per memorizzare i messaggi della chat
   */
  public ThreadGestioneServizoChat(int numeroMaxConnessioni, List lista) {
    this.nrMaxConnessioni = nrMaxConnessioni - 1;
    this.lista = lista;
    this.listaConnessioni = new ThreadChatConnesioni[this.nrMaxConnessioni];
    me = new Thread(this);
    me.start(); // Avvia il thread corrente
  }

  // Metodo run richiesto per implementare l'interfaccia Runnable
  public void run() {
    boolean continua = true;
    // instanzio la connesione del server per la chat
    try{
      serverChat = new ServerSocket(6789); // Crea un ServerSocket sulla porta 6789
    } catch(Exception e) {
      JOptionPane.showMessageDialog(null, "Impossibile instanziare il server"); // Mostra un messaggio di errore se non è possibile creare il server
      continua = false;
    }

    if(continua) {
      // accetto le connessioni chat
      try{
          for(int xx = 0; xx < nrMaxConnessioni; xx++) {
              Socket tempo = null;
              tempo = serverChat.accept(); // Accetta una connessione e restituisce un socket per comunicare con il client
              listaConnessioni[xx] = new ThreadChatConnesioni(this,tempo); // Crea un thread per gestire la connessione
          }
          serverChat.close(); // Chiude il ServerSocket
      }catch(Exception e){
          JOptionPane.showMessageDialog(null, "Impossibile instanziare server chat"); // Mostra un messaggio di errore se non è possibile gestire le connessioni
      }
    }
  } // fine metodo "run"

  // Metodo per spedire un messaggio a tutti i client connessi
  public void spedisciMessaggio(String mex) {
    // Scrivo il messaggio nella lista della chat
    lista.add(mex);
    lista.select(lista.getItemCount()-1); // Seleziona l'ultimo messaggio nella lista
    // Mando il messaggio agli altri
    for(int xx = 0; xx < this.nrMaxConnessioni; xx++) {
      if(listaConnessioni[xx] != null) {
        listaConnessioni[xx].spedisciMessaggioChat(mex); // Chiama il metodo per inviare il messaggio a ciascun client
      }
    }
  } 
} // Fine classe ThreadGestioneServizioChat
