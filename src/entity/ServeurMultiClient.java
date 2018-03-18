package entity;


import java.io.IOException;
import java.net.UnknownHostException;

import java.net.Socket;
import java.net.ServerSocket;
import java.net.Socket;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

public class ServeurMultiClient{

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
	    
    public static void main(String[] args) {
        ServerSocket socket;
        try {
        socket = new ServerSocket(6002);
        Thread t = new Thread(new Service(socket));
        t.start();
        System.out.println("J'attends des connexions mais pas trop!");
	} catch (IOException e) {e.printStackTrace();}
    }
}

class Service implements Runnable{
	
	private ServerSocket socketserveur  ;
        private Socket s ;

	private static int nbrclient = 0;
	private static final int nbMaxClient =10;

        public Service(ServerSocket socket){
            socketserveur = socket;
        }
     
        public void run(){
	try {

	    PrintWriter out;
	    BufferedReader in;
        
            System.out.println("Serveur en attente");
	   
	    while(nbrclient<nbMaxClient){

 	       // écoute d'un service entrant -association socket client et socket serveur.		
               s = socketserveur.accept(); 
	       String sonIp=s.getInetAddress().toString();
	       nbrclient ++;	
               System.out.println("le client "+nbrclient+" s'est connecté :"+ sonIp);
	      
	    	// envoi d'un message par politesse - ecriture
	   	out = new PrintWriter(s.getOutputStream());
	   	Thread.sleep(1000);
	   	out.println("Bonjour "+sonIp+", quel est votre nom?");
	   	out.flush();

	   	//attente d'une reponse - lecture
		Thread.sleep(1000); 
	   	in = new BufferedReader (new InputStreamReader (s.getInputStream()));
	   	String nomRecu = in.readLine();
	   	System.out.println("message :"+ nomRecu);    

		//reponse par politesse
		out = new PrintWriter(s.getOutputStream());
		if(nomRecu!=null){
			if(nomRecu.contains("Bonjour")){
				out.println("merci"+nomRecu+", bye!");
			}else{out.println("impoli, bye!");}
			out.flush();
		}
	
           	//cloture de la connexion avec le service entrant
	   	Thread.sleep(1000);
	   	s.close();
           	System.out.println("déconnexion du client numero "+nbrclient+" d'ip "+sonIp); 
	
	   } 
		// écoute d'un service entrant -association socket client et socket serveur.		
               s = socketserveur.accept(); 
	       nbrclient ++;	
               System.out.println("le client "+nbrclient+" s'est connecté !"); 
			
		// envoi d'un message de trop de connexions
	   	out = new PrintWriter(s.getOutputStream());
	   	Thread.sleep(1000);
	   	out.println("Trop de clients, débordé, désolé!");
	   	out.flush();

           	//cloture de la connexion avec le service entrant
	   	s.close();
           	//cloture de l'ecoute
           	socketserveur.close();   
	}	
	catch (IOException e) {e.printStackTrace();}            
        catch (Exception e) {e.printStackTrace();}
    }

}
