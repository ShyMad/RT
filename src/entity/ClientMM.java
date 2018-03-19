package entity;

import java.io.IOException;
import java.net.UnknownHostException;

import gameinterface.Authentification;
import gameinterface.ClientGame;
import gameinterface.CreerCompte;

import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class ClientMM {
	private static String nomPrenom;
	private static boolean auth = false;
	private static String send="";

	public ClientMM(){
		this.nomPrenom= nomPrenom;
	      Socket socket;
	  	BufferedReader in;
	  	PrintWriter out;
	          try {
	  	//demande d'ouverture d'une connexion sur le serveur local et le numero de port 6011
	           socket = new Socket("localhost",6011);

	  	//attente d'une reponse - lecture
	  	in = new BufferedReader (new InputStreamReader (socket.getInputStream()));
	  	String message_distant = in.readLine();
	  	System.out.println("message :"+ message_distant);

	  	//reponse par politesse
	  	out = new PrintWriter(socket.getOutputStream());
	  	if(message_distant.contains("Bonjour")){
	  		out.println("QUIT");
            	System.out.println("quitRequest");
	  		}else{out.println("impoli!");}
	  	out.flush();

	  	//attente d'une reponse - lecture
	  	in = new BufferedReader (new InputStreamReader (socket.getInputStream()));
	  	message_distant = in.readLine();
	  	System.out.println("message:"+ message_distant);

	          //fermeture de la connexion
	  	socket.close();

	          }catch (UnknownHostException e) {
	  		 e.printStackTrace();
	          }catch (IOException e) {
	              e.printStackTrace();
	          }
	}
	
	public ClientMM(String fct,String nom,String code){
	      Socket socket;
	  	BufferedReader in;
	  	PrintWriter out;
	          try {
	  	//demande d'ouverture d'une connexion sur le serveur local et le numero de port 6011
	           socket = new Socket("localhost",6011);

	  	//attente d'une reponse - lecture
	  	in = new BufferedReader (new InputStreamReader (socket.getInputStream()));
	  	String message_distant = in.readLine();
	  	System.out.println("message :"+ message_distant);

	  	//reponse par politesse
	  	out = new PrintWriter(socket.getOutputStream());
	  	if(message_distant.contains("Bonjour")){
	  		out.println(fct+" "+nom+" "+code);
	  		}else{out.println("impoli!");}
	  	out.flush();

	  	//attente d'une reponse - lecture
	  	in = new BufferedReader (new InputStreamReader (socket.getInputStream()));
	  	message_distant = in.readLine();
	  	System.out.println("message rep:"+ message_distant);
	  	String[] type = message_distant.split(" ");
	  	switch(type[0]){
	  	case "AUTH": {	if(type[1].equalsIgnoreCase("true")){
	  					auth = true;
					  	}else if (type[1].equalsIgnoreCase("false")) auth=false;
					  	}
	  				break;
	  	case "SEND": if(message_distant!=null)
	  				send = type[1];
	  				else send="null";
	  				break;
	  	case "QUIT": break;
	  	default: send=type[0];
	  				
	  	}
	  	
	          //fermeture de la connexion
	  	socket.close();

	          }catch(SocketException e){
	              System.err.println("LA CONNEXION A ETE INTERROMPUE ! ");
	          }
	          catch (UnknownHostException e) {
	  		 e.printStackTrace();
	          }catch (IOException e) {
	              e.printStackTrace();
	          }
	}
	
    public static boolean isAuth() {
		return auth;
	}

    public static String isSend() {
		return send;
	}

	public static void main(String[] args) {
    	
  
    }
}
