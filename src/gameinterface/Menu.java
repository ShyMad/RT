package gameinterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Menu extends JFrame{
	
	private JPanel contentPane;
	
	public Menu(String nomPrenom) {
		setTitle("Guess5");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 606, 467);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 215, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setBounds(0, 0, 606, 467);
		contentPane.setLayout(null);
		
		//Menu
		JLabel lblMenu = new JLabel("Menu");
		lblMenu.setForeground(new Color(0, 128, 0));
		lblMenu.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblMenu.setBounds(200, 70, 150, 50);
		lblMenu.setHorizontalAlignment(JLabel.CENTER);
		contentPane.add(lblMenu);
		
		//Affichage du Score
		JLabel lblScore = new JLabel("Score global: ");
		lblScore.setForeground(new Color(0, 128, 0));
		lblScore.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblScore.setBounds(15, 5, 350, 43);
		contentPane.add(lblScore);
		
		//Infos sur le Joueur
		JLabel lblJoueur = new JLabel("Joueur: "+nomPrenom);
		lblJoueur.setForeground(new Color(0, 128, 0));
		lblJoueur.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblJoueur.setBounds(370, 5, 350, 43);
		contentPane.add(lblJoueur);
				
		//Bouton pour une nouvelle partie
		JButton btnStartGame = new JButton("Lancer une partie");
		btnStartGame.setBackground(new Color(100, 149, 237));
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		        new ClientGame(nomPrenom).setVisible(true);
		        setVisible(false);
			}
		});
		btnStartGame.setBounds(150, 150, 250, 50);
		contentPane.add(btnStartGame);
		
		//Bouton statistiques
		JButton btnStatistics = new JButton("Statistiques");
		btnStatistics.setBackground(new Color(100, 149, 237));
		btnStatistics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		        //new ClientGame("secret").setVisible(true);
				setVisible(false);
			}
		});
		btnStatistics.setBounds(150, 250, 250, 50);
		contentPane.add(btnStatistics);
		
		//Bouton se deconnecter
		JButton btnDeconnecter = new JButton("Se déconnecter");
		btnDeconnecter.setBackground(new Color(100, 149, 237));
		btnDeconnecter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		        new Authentification().setVisible(true);
		        setVisible(false);
			}
		});
		btnDeconnecter.setBounds(150, 350, 250, 50);
		contentPane.add(btnDeconnecter);
	}
}
