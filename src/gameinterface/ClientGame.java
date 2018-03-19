package gameinterface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entity.ClientMM;
import entity.Partie;

import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Graphics;

public class ClientGame extends JFrame {

	private JPanel contentPane;
	private JTextField txtWord;
	private String nom;
	private String code;
	private String motSecret;
	private Partie partie;
	private long temps;
	private long debut;
	private long fin;
	private Timer timer;


	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientGame frame = new ClientGame("secret",this.nomPrenom);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public ClientGame(String nom,String code) {
		this.nom = nom;
		this.code = code;
		Random rand = new Random();
        String str="";
        String stri="";
		for(int i = 0 ; i < 5 ; i++){
		  char c = (char)(rand.nextInt(26) + 97);
		  str += c;
		}
		motSecret=str.toUpperCase();
		System.out.print(motSecret);
		partie = new Partie(str);
		setTitle("Guess5");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 606, 467);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 215, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/*JButton btnStartGame = new JButton("Lancer une partie");
		btnStartGame.setBackground(new Color(100, 149, 237));
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnStartGame.setBounds(37, 373, 135, 23);
		contentPane.add(btnStartGame);*/
		
		JButton btnExit = new JButton("Abandonner");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ClientMM();
		        new Menu(nom,code).setVisible(true);
		        setVisible(false);
			}
		});
		btnExit.setBackground(new Color(255, 0, 0));
		btnExit.setBounds(443, 380, 119, 23);
		contentPane.add(btnExit);
		
		JLabel lblInsrerVotreMot = new JLabel("Ins\u00E9rer votre mot ici :");
		lblInsrerVotreMot.setBounds(89, 304, 300, 14);
		contentPane.add(lblInsrerVotreMot);
		
		//Résultat à afficher 
		JLabel lblResultat = new JLabel();
		lblResultat.setBounds(70, 350, 600, 30);
		lblResultat.setFont(new Font("Tahoma", Font.CENTER_BASELINE, 20));
		contentPane.add(lblResultat);
		
		//Bouton Envoyer
		JButton btnSend = new JButton("Envoyer");
		JLabel lblCorrectPlace = new JLabel("");
		lblCorrectPlace.setFont(new Font("Tahoma", Font.CENTER_BASELINE, 16));
		JLabel lblEffectPres = new JLabel("");
		lblEffectPres.setFont(new Font("Tahoma", Font.CENTER_BASELINE, 16));
		
		JTextPane textListWords = new JTextPane();
		textListWords.setEditable(false);
		textListWords.setText(lblCorrectPlace.getText());
		textListWords.setBackground(new Color(238, 232, 170));
		textListWords.setBounds(89, 96, 410, 197);
		contentPane.add(textListWords);
		
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.add(lblCorrectPlace);
				if(partie.comparer(txtWord.getText(), motSecret) == true){
					// Le joueur trouve le mot 
					fin();
					temps();
					timer.cancel();
					lblResultat.setText("Vous avez gagné. Félicitations! Durée du jeu: "+temps+"s");
					//System.out.println("debut: "+debut+"fin: "+fin+"temps: "+temps);
					lblResultat.setForeground(new Color(0, 255, 0));
				}
				else {   // Le joueur n'a pas trouvé le mot 
					
								/* ******Nombre de lettres correctement placées******** */
								int comptCorrectPlace=0;
								for(int i=0;i<5;i++){
									// Nombre de lettres correctement placées
									if(txtWord.getText().charAt(i)==motSecret.charAt(i)){
										comptCorrectPlace++;
									}			
								}		
								 /* *******Nombre de lettres effectivement présentes*******  */
								int comptEffectPres=0;
					            for (int i = 0; i < 5; i++) {
					                for (int j = 0; j < 5; j++) {
					                    if (txtWord.getText().charAt(j) == motSecret.charAt(i)) {
					                    	comptEffectPres++;
					                        break;
					                    }
					                }
					            }
					            
								// Afficher le nombre de lettres correctement placées et le nombre de lettres effectivement presentes 
								lblCorrectPlace.setText("Nombre de lettres correctement placées: "+String.valueOf(comptCorrectPlace)+"\nNombre de lettres effectivement présentes: "+String.valueOf(comptEffectPres));
								textListWords.setText(lblCorrectPlace.getText());
								contentPane.add(textListWords);
								
								// Afficher le fait qu'il n'a pas trouvé le mot 
								lblResultat.setText("Vous avez raté le mot. Dommage!");
								lblResultat.setForeground(new Color(255, 0, 0));			
								
					}
			}
		});
		btnSend.setBackground(new Color(60, 179, 113));
		btnSend.setBounds(422, 304, 89, 23);
		contentPane.add(btnSend);
		
		txtWord = new JTextField() ;
		txtWord.setBounds(230, 305, 110, 20);
		contentPane.add(txtWord);
		txtWord.setColumns(10);
		txtWord.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e) {
		    }
			public void keyTyped(KeyEvent e) {
				lblResultat.setText("");
		    }
			public void keyReleased(KeyEvent e) {
				int pos = txtWord.getCaretPosition();
				txtWord.setText(txtWord.getText().toUpperCase());
				txtWord.setCaretPosition(pos);
		    }
		});
		
		JLabel lblTitle = new JLabel("Guess5!");
		lblTitle.setFont(new Font("Trebuchet MS", Font.BOLD, 28));
		lblTitle.setBounds(214, 29, 119, 43);
		contentPane.add(lblTitle);
		
		//Affichage du Score
		JLabel lblScore = new JLabel("Score actuel: ");
		lblScore.setForeground(new Color(0, 128, 0));
		lblScore.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblScore.setBounds(15, 5, 350, 43);
		contentPane.add(lblScore);
		
		//Affichage du temps ecoulé
		JLabel lblTemps = new JLabel("Temps ecoulé: ");
		JLabel lblT = new JLabel() ;
		timer = new Timer();

		timer.schedule( new TimerTask() {
		    public void run() {
				lblT.setText(String.valueOf(temps));
				fin();
				temps();		    }
		 }, 0, 1000);
		
		lblTemps.setForeground(new Color(0, 128, 0));
		lblTemps.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTemps.setBounds(15, 25, 350, 43);
		contentPane.add(lblTemps);
		lblT.setForeground(new Color(0, 128, 0));
		lblT.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblT.setBounds(150, 25, 350, 43);
		contentPane.add(lblT);
		
		//Infos sur le Joueur
		JLabel lblJoueur = new JLabel("Joueur: "+nom);
		lblJoueur.setForeground(new Color(0, 128, 0));
		lblJoueur.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblJoueur.setBounds(370, 5, 350, 43);
		contentPane.add(lblJoueur);
		
		//Démarrage du chrono
		  debut();
		
	}
	
	public void debut(){
		this.debut = System.currentTimeMillis();
	}
	
	public void fin(){
		this.fin = System.currentTimeMillis();
	}
	
	public void temps(){
		temps = (fin-debut)/1000;
	}
}
