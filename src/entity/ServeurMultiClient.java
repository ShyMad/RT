package entity;


import java.io.IOException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

import java.net.Socket;
import java.net.SocketException;
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

/**
 * 
 * @author ibelarabi & laitkhouya
 *
 */

public class ServeurMultiClient {

	private FileReader fr;

	/**
	 * la fonction main du serveur
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ServerSocket socket;
		try {
			socket = new ServerSocket(6012);
			Thread t = new Thread(new Service(socket));
			t.start();
			System.out.println("J'attends des connexions mais pas trop!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class Service implements Runnable {

	private ServerSocket socketserveur;
	private Socket s;

	private static int nbrclient = 0;
	private static final int nbMaxClient = 10;
	private boolean closeConnexion = false;
	private boolean FIND = false;
	public List<String> game = new ArrayList<String>();

	public Service(ServerSocket socket) {
		socketserveur = socket;
	}

	// La fonction genWord()
	/**
	 * function qui genere un mot secret
	 * 
	 * @return
	 */
	public String genWord() {
		Random rand = new Random();
		String str = "";
		String stri = "";
		for (int i = 0; i < 5; i++) {
			char c = (char) (rand.nextInt(26) + 97);
			str += c;
		}
		String motSecret = str.toUpperCase();
		return motSecret;
	}

	// la fonction check
	/**
	 * function qui permet de tester l'existance d'un joueur
	 * 
	 * @param txtNom
	 * @param txtCode
	 * @return
	 */
	public boolean check(String txtNom, String txtCode) {
		File file = new File("authFile.txt");
		boolean ok = false;
		try {
			// Création de l'objet de lecture
			FileReader fr = new FileReader(file);

			int counter = 0;
			BufferedReader br = null;
			br = new BufferedReader(fr);
			int err = 0;

			String stri = "";
			while ((stri = br.readLine()) != null) {
				String[] tab = stri.split(" ");
				if (tab[0].compareTo(txtNom) == 0 && tab[2].compareTo(txtCode) == 0) {
					System.out.println("Welcome " + tab[0] + " " + tab[1]);
					ok = true;
					err = 0;
					br.close();
					break;
				} else
					err++;
				counter++;
			}

			if (err != 0) {
				ok = false;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ok;
	}

	// La fonction makeFileForClient
	/**
	 * function permet de generer un fichier de partie pour chaque joueur
	 * 
	 * @param code
	 * @param line
	 */
	public void makeFileForClient(String code, String line) {
		File file = new File(code + ".txt");

		FileWriter fw;
		try {
			// Cr√©ation de l'objet
			fw = new FileWriter(file, true);
			// On √©crit la cha√Æne
			fw.write(line + "\n");
			// On ferme le flux
			fw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// La fonction checkWord
	/**
	 * function qui fait le test si le mot ajouter par le client correspond au
	 * mot secret
	 * 
	 * @param word
	 * @return
	 */
	public String checkWord(String word) {
		String[] parts = word.split(" ");
		String code = parts[0];
		String mot = parts[1];
		String state = "";
		for (String c : game) {
			String[] line = c.split(" ");
			if (code.equals(line[0])) {
				if (mot.equals(line[2])) {
					FIND = true;
					state = line[2] + " " + mot + " " + DateFormat.getTimeInstance(DateFormat.MEDIUM).format(new Date())
							+ " " + "Match";
				} else
					state = line[2] + " " + mot + " " + DateFormat.getTimeInstance(DateFormat.MEDIUM).format(new Date())
							+ " " + "unMatch";
			} else
				System.err.println("joueur introuvable!");

		}
		return state;
	}

	// La fonction CreatePlayer(nom,prenom)
	/*
	 * la fonction qui permet d'ajouter un joueur au fichier authFile.txt et
	 * retourn le code licence généré
	 * 
	 * @param nom
	 * 
	 * @param prenom
	 * 
	 * @return
	 * 
	 * @throws FileNotFoundException
	 */
	public String CreatePlayer(String nom, String prenom) throws FileNotFoundException {
		Joueur j = null;
		String code = "";
		Random rand = new Random();
		int str = 0;
		int randomNum = 0;
		String part = "";
		for (int i = 0; i < 5; i++) {
			randomNum = rand.nextInt((10 - 1) + 1) + 1;
			str += randomNum;
		}

		if ((nom != "" || prenom != "") && (nom.length() >= 2) && prenom.length() >= 2) {
			part = nom.toLowerCase().substring(0, 2).concat(prenom.toLowerCase().substring(0, 2)).concat("" + str);
			j = new Joueur(nom, prenom, part);
			code = part;
			String player = j.getNom() + " " + j.getPrenom() + " " + j.getCodeLicencie();
			File file = new File("authFile.txt");
			FileWriter fw;
			try {
				// Création de l'objet
				fw = new FileWriter(file, true);
				// On écrit la chaîne
				fw.write(player + "\n");
				// PrintStream fileStream = new PrintStream(file);
				// fileStream.println(player);
				// On ferme le flux
				fw.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return part;
		} else {
			return null;
		}

	}

	public void run() {
		try {

			PrintWriter out;
			BufferedReader in;

			System.out.println("Serveur en attente");

			while (nbrclient < nbMaxClient) {

				// écoute d'un service entrant -association socket client et
				// socket serveur.
				s = socketserveur.accept();
				String sonIp = s.getInetAddress().toString();
				nbrclient++;
				System.out.println("le client " + nbrclient + " s'est connecté :" + sonIp);

				// envoi d'un message par politesse - ecriture
				out = new PrintWriter(s.getOutputStream());
				Thread.sleep(1000);
				out.println("Bonjour " + sonIp + ", quel est votre nom?");
				out.flush();

				// attente d'une reponse - lecture
				Thread.sleep(1000);
				in = new BufferedReader(new InputStreamReader(s.getInputStream()));
				String nomRecu = in.readLine();
				System.out.println("message :" + nomRecu);

				// reponse par politesse
				out = new PrintWriter(s.getOutputStream());
				if (nomRecu != null) {
					String toSend = "";
					String[] tabCommand = nomRecu.split(" ");
					switch (tabCommand[0]) {
					case "SEND":
						// SEND [ ] NOM [ ] PRENOM
						String word = tabCommand[1] + " " + tabCommand[2];
						toSend = "SEND " + CreatePlayer(tabCommand[1], tabCommand[2]);
						break;
					case "AUTH":
						// AUTH [] NOM [] CODE
						String nom = tabCommand[1];
						String code = tabCommand[2];
						if (check(nom, code)) {
							toSend = "AUTH true";
							System.out.println("truuue");
						} else {
							toSend = "AUTH false";
							System.out.println("faaalse");
						}
						break;
					case "QUIT":
						toSend = "QUIT Communication terminÈe";
						nbrclient--;
						closeConnexion = true;
						break;
					case "START": // START [] MOT
						String mot = genWord();
						game.add(tabCommand[1] + " " + tabCommand[2] + " " + mot + " "
								+ DateFormat.getTimeInstance(DateFormat.MEDIUM).format(new Date()));
						makeFileForClient(tabCommand[1], checkWord(mot));
						toSend = "START " + mot;
						break;
					default:
						break;
					}
					out.println(toSend);
					out.flush();
					if (closeConnexion) {
						System.err.println("COMMANDE CLOSE DETECTEE ! ");
						out = null;
						in = null;
						s.close();
						break;
					}
				}

				// cloture de la connexion avec le service entrant
				Thread.sleep(1000);
				s.close();
				System.out.println("déconnexion du client numero " + nbrclient + " d'ip " + sonIp);

			}
			// écoute d'un service entrant -association socket client et socket
			// serveur.
			s = socketserveur.accept();
			nbrclient++;
			System.out.println("le client " + nbrclient + " s'est connecté !");

			// envoi d'un message de trop de connexions
			out = new PrintWriter(s.getOutputStream());
			Thread.sleep(1000);
			out.println("Trop de clients, débordé, désolé!");
			out.flush();

			// cloture de la connexion avec le service entrant
			s.close();
			// cloture de l'ecoute
			socketserveur.close();
		} catch (SocketException e) {
			System.err.println("LA CONNEXION A ETE INTERROMPUE ! ");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
