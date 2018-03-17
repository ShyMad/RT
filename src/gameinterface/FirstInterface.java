package gameinterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FirstInterface {

	private JFrame frmGuess ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FirstInterface window = new FirstInterface();
					window.frmGuess.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FirstInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGuess = new JFrame();
		frmGuess.setResizable(false);
		frmGuess.setTitle("Guess5");
		frmGuess.setBounds(100, 100, 450, 300);
		frmGuess.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGuess.getContentPane().setLayout(null);
		
		JButton btnNewHere = new JButton("New here") ;
		btnNewHere.setForeground(new Color(230, 230, 250));
		btnNewHere.setBackground(new Color(0, 128, 128));
		btnNewHere.setBounds(49, 173, 89, 23);
		frmGuess.getContentPane().add(btnNewHere);
		
		JButton btnConnexion = new JButton("Connexion");
		btnConnexion.setForeground(new Color(253, 245, 230));
		btnConnexion.setBackground(new Color(0, 128, 0));
		btnConnexion.setBounds(281, 173, 89, 23);
		frmGuess.getContentPane().add(btnConnexion);
		
		JLabel lblWelcomeToGuess = new JLabel("Welcome to Guess5");
		lblWelcomeToGuess.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblWelcomeToGuess.setForeground(new Color(0, 128, 0));
		lblWelcomeToGuess.setBounds(107, 75, 215, 26);
		frmGuess.getContentPane().add(lblWelcomeToGuess);
		
		btnNewHere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmGuess.setVisible(false);
		        new CreerCompte().setVisible(true);
			}
		});
		
		btnConnexion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				frmGuess.setVisible(false);
		        new Authentification().setVisible(true);
			}
		});
	}
}
