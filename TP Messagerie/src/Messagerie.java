/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jonathancozzo
 */
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.*; // Hashtable

public class Messagerie extends UnicastRemoteObject implements Serveur {

    private String[] listeUsers;
    private Map<Integer, String> tableauId;
    private Map<Integer, String> tableauMessages;

    // Implémentation du constructeur
    public HelloServeur(String msg) throws java.rmi.RemoteException {
        message = msg;
    }
    // Implémentation de la méthode distante

    public void sayHello() throws java.rmi.RemoteException {
        System.out.println(message);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
}
