package entity;

import java.io.IOException;
import java.net.UnknownHostException;

import gameinterface.ClientGame;

import java.net.InetAddress;
import java.net.Socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class ClientMM {
	private static String nomPrenom;
	
	public ClientMM(String nomPrenom){
		this.nomPrenom= nomPrenom;
	      Socket socket;
	  	BufferedReader in;
	  	PrintWriter out;
	          try {
	  	//demande d'ouverture d'une connexion sur le serveur local et le numero de port 6004
	           socket = new Socket("localhost",6004);

	  	//attente d'une reponse - lecture
	  	in = new BufferedReader (new InputStreamReader (socket.getInputStream()));
	  	String message_distant = in.readLine();
	  	System.out.println("message :"+ message_distant);

	  	//reponse par politesse
	  	out = new PrintWriter(socket.getOutputStream());
	  	if(message_distant.contains("Bonjour")){
	  		out.println("oh le serveur!");
	  		new ClientGame(nomPrenom).setVisible(true);
	  		}else{out.println("impoli!");}
	  	out.flush();

	  	//attente d'une reponse - lecture
	  	in = new BufferedReader (new InputStreamReader (socket.getInputStream()));
	  	message_distant = in.readLine();
	  	System.out.println("message :"+ message_distant);

	          //fermeture de la connexion
	  	socket.close();

	          }catch (UnknownHostException e) {
	  		 e.printStackTrace();
	          }catch (IOException e) {
	              e.printStackTrace();
	          }
	}
	
    public static void main(String[] args) {
    	
  
    }
}
