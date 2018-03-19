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
	public ClientMM(){
		this.nomPrenom= nomPrenom;
	      Socket socket;
	  	BufferedReader in;
	  	PrintWriter out;
	          try {
	  	//demande d'ouverture d'une connexion sur le serveur local et le numero de port 6009
	           socket = new Socket("localhost",6009);

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
	  	//demande d'ouverture d'une connexion sur le serveur local et le numero de port 6009
	           socket = new Socket("localhost",6009);

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
	  	if(message_distant.equalsIgnoreCase("true")){
	  		auth = true;
	  	}else auth=false;
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

	public static void setAuth(boolean auth) {
		ClientMM.auth = auth;
	}

	public static void main(String[] args) {
    	
  
    }
}
