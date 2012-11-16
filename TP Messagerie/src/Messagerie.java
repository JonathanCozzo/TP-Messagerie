/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jonathancozzo
 */
import java.net.InetAddress;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.*; // Hashtable

public class Messagerie extends UnicastRemoteObject implements Serveur {

	private ArrayList<String> listeUsers;
	private Map<Integer, String> tableauId;
	private Map<Integer, String> tableauMessages;

	// Implémentation du constructeur
	public Messagerie() throws java.rmi.RemoteException {
		this.listeUsers = new ArrayList<String>();
		this.tableauId = new HashMap<Integer, String>();
		this.tableauMessages = new HashMap<Integer, String>();
	}

	// Implémentation des méthodes distantes

	public void connect(String id) throws java.rmi.RemoteException {
		this.listeUsers.add(id);
	}

	public void send(String message, String id) throws java.rmi.RemoteException {
		this.tableauId.put(this.tableauId.size(), id);
		this.tableauMessages.put(this.tableauMessages.size(), message);
	}

	public void bye(String id) throws java.rmi.RemoteException {
		this.listeUsers.remove(id);
	}

	public ArrayList<String> who() throws java.rmi.RemoteException {
		return this.listeUsers;
	}

	public ArrayList<Map<Integer, String>> getMessages(int nbMessages)
			throws java.rmi.RemoteException {
		ArrayList<Map<Integer, String>> messages = new ArrayList<Map<Integer, String>>();
		int i;
		Map<Integer, String> tabId = this.tableauId;
		Map<Integer, String> tabMessages = this.tableauMessages;
		for (i = 0; i < nbMessages; i++) {
			tabId.remove(i);
			tabMessages.remove(i);
		}
		messages.add(0, tabId);
		messages.add(1, tabMessages);
		return messages;
	}

	public static void main(String args[]) {
		int port; String URL;
		try {
		// transformation d ’une chaîne de caractères en entier
		Integer I = new Integer(args[0]);
		port = I.intValue();
		} catch (Exception ex) {
		System.out.println(" Please enter: Server <port>"); return;
		}
		try {
		// Création du serveur de nom - rmiregistry
		Registry registry = LocateRegistry.createRegistry(port);
		// Création d ’une instance de l’objet messagerie
		Messagerie obj = new Messagerie();
		// Calcul de l’URL du serveur
		URL = "//127.0.0.1:8080/chatservice";
		Naming.rebind(URL, obj);
		} catch (Exception exc) {System.out.println("Error");}
	}
}
