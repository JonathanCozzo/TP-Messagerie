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

public interface Serveur extends java.rmi.Remote {

    public void connect(String id)
            throws java.rmi.RemoteException;

    public void send(String message, String id)
            throws java.rmi.RemoteException;

    public void bye(String id)
            throws java.rmi.RemoteException;

    public ArrayList<String> who()
            throws java.rmi.RemoteException;

    public ArrayList<Map<Integer, String>> getMessages(int nbMessages)
            throws java.rmi.RemoteException;
}
