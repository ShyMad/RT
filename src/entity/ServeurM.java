/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ibelarabi
 */
public class ServeurM {
    
    private static List<Joueur> JoueurListe;
    private static List<Joueur> ScoreListe;
    // la socket du client
    private static Socket clientSock = null;
    private static ServerSocket serveurSocket = null;
   // private static PrintStream os = null;
   // private static DataInputStream ins = null; 
    private static final int maxClients = 10;
    private static final clientThread[] tabThreads = new clientThread[maxClients];
    private File authFile;
    private File scoreFile;
    private FileReader fr;
    
    public void insertIntoAuthFile(Joueur j){
        
        String player = j.getNom()+" "+j.getPrenom()+" "+j.getCodeLicencie();
        File file = new File("authFile.txt");
        FileWriter fw;
         try {
            //Création de l'objet
            fw = new FileWriter(file,true);
           //On écrit la chaîne
            fw.write(player);
            //On ferme le flux
            fw.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
          } catch (IOException e) {
            e.printStackTrace();
          }
    }
    
    public boolean Authentif(String nom,String code){
        File file = new File("authFile.txt");
        BufferedReader br = null;
        String str;
        boolean response= false;
        int err =0;
       try {
           fr = new FileReader(file);
           br = new BufferedReader(fr);
           while ((str = br.readLine()) != null){
        //  System.out.println(stri);
                String[] tab= str.split(" ");
                if(tab[0].compareTo(nom)==0 && tab[2].compareTo(code) == 0){
                    response = true;
                    err = 0;
                }
                else
                    err++;
    	 }
            if(err !=0)
                response = false;
            //On ferme le flux
            fr.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
          } catch (IOException e) {
            e.printStackTrace();
          }
        return response;
    }
    public static void main(String args[]) {
     /*
     * Open a server socket on the portNumber (default 2222)
     */
        int numPort = 5000;
        try{
            serveurSocket = new ServerSocket(numPort);
        }catch(IOException e){
            System.err.println(e);
        }
    /*
     * Create a client socket for each connection and pass it to a new client
     * thread.
     */
        while(true){
            try{
                clientSock = serveurSocket.accept();
                for(int i = 0; i < maxClients; i++){
                    if(tabThreads[i] == null ){
                        tabThreads[i] = new clientThread(clientSock,tabThreads);
                        tabThreads[i].start();
                        break;
                    }
                }
            } catch (IOException ex) {
                System.err.println(ex);
            }
        }
    }
    
   
}
