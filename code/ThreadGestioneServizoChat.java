import java.awt.List;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

public class ThreadGestioneServizoChat implements Runnable {
  
  private int nrMaxConnessioni;
  private List lista; // Crea una lista
  private ThreadChatConnesioni[] listaConnessioni;
  Thread me;
  private ServerSocket serverChat;

  /**
   * @param numeroMaxConnessioni
   * @param lista
   */
  public ThreadGestioneServizoChat(int numeroMaxConnessioni, List lista) {
    this.nrMaxConnessioni = nrMaxConnessioni - 1;
    this.lista = lista;
    this.listaConnessioni = new ThreadChatConnesioni[this.nrMaxConnessioni];
    me = new Thread(this);
    me.start();
  }

  public void run() {
    
    boolean continua = true;
    // instanzio la connesione del server per la chat
    try{
      serverChat = new ServerSocket(6789);
    } catch(Exception e) {
      JOptionPane.showMessageDialog(null, "Impossibile instanziare il server");
      continua = false;
    }

    if(continua) {
      // accetto le connessioni chat
      try{
          for(int xx = 0; xx < nrMaxConnessioni; xx++) {
              Socket tempo = null;
              tempo = serverChat.accept();
              listaConnessioni[xx] = new ThreadChatConnesioni(this,tempo);
          }
          serverChat.close();
      }catch(Exception e){
          JOptionPane.showMessageDialog(null, "Impossibile instanziare server chat");
      }
    }
  } // fine metodo "run"

  public void spedisciMessaggio(String mex) {
    //Scrivo il mex nella mia lista
    lista.add(mex);
    lista.select(lista.getItemCount()-1);
    //Mando il messaggio agli altri
    for(int xx = 0; xx < this.nrMaxConnessioni; xx++) {
      if(listaConnessioni[xx] != null) {
        listaConnessioni[xx].spedisciMessaggioChat(mex);
      }
    }
  } 
} //Fine classe ThreadGestioneServizioChat