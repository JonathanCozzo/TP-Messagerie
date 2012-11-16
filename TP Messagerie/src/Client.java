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
import java.io.*;

public class Client {

    static String id;
    static int nbMessages;

    public Client() {
        this.id = null;
        this.nbMessages = 0;
    }

    public static void main(String args[]) {
        try {
            // Récupération d'un stub sur l'objet serveur.
            Messagerie messagerie = (Messagerie) Naming.lookup("//ma_machine/mon_serveur");

            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            String entree = in.readLine();

            while (true) {
                String[] entreeClavier = entree.split("\\s+");
                if (entreeClavier[0] == "connect") {
                    messagerie.connect(id);
                    System.out.print("Bienvenue sur la messagerie !\n");
                } else if (entreeClavier[0] == "send") {
                    messagerie.send(entree.substring(5), id);
                } else if (entreeClavier[0] == "bye") {
                    messagerie.bye(id);
                    System.out.print("Aurevoir !\n");
                    break;
                } else if (entreeClavier[0] == "who") {
                    ArrayList<String> listeUsers = messagerie.who();
                    System.out.print("Liste des utilisateurs connectés :\n");
                    System.out.print(listeUsers);
                } else {
                    System.out.print("Erreur : veuillez entrée une commande valide\n");
                }
                // Rafraichissement et affichage des nouveaux messages
                ArrayList<Map<Integer, String>> messages = messagerie.getMessages(nbMessages);
                nbMessages += messages.get(0).size();
                int i;
                for (i = 0; i < messages.get(0).size(); i++) {
                    System.out.print(messages.get(0).get(i) + " : " + messages.get(1).get(i) + "\n");
                }
            }

        } catch (Exception exc) {
            System.out.print("Erreur");
        }
    }
}
