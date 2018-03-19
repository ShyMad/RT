package gameinterface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entity.ClientMM;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.awt.Color;

import javax.security.auth.callback.ChoiceCallback;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Authentification extends JFrame {

	private JPanel contentPane;
	private JTextField txtNom;
	private JTextField txtCode;
	private JLabel lblInfo;
	private JButton btnLogIn;
	private String nomPrenom;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Authentification frame = new Authentification();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Authentification() {
		setResizable(false);
		setTitle("Connexion");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 606, 467);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(255, 215, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAuthentification = new JLabel("Se Connecter");
		lblAuthentification.setForeground(new Color(0, 128, 0));
		lblAuthentification.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblAuthentification.setBounds(44, 30, 145, 32);
		contentPane.add(lblAuthentification);
		

		btnLogIn = new JButton("Entrer");
		

		JButton btnLogIn = new JButton("Entrer");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ClientMM("AUTH",txtNom.getText(),txtCode.getText());
				if(ClientMM.isAuth()) {					
				        new Menu(txtNom.getText(),txtCode.getText()).setVisible(true);
				        setVisible(false);
				}else {
					 lblInfo.setText("Nom ou code incorrect !");
				}
				}
					
			
		});

		btnLogIn.setBackground(new Color(0, 128, 0));
		btnLogIn.setBounds(386, 115, 89, 23);
		contentPane.add(btnLogIn);
		
		JLabel lblNewLabel = new JLabel("Votre Nom:");
		lblNewLabel.setBounds(44, 91, 111, 14);
		contentPane.add(lblNewLabel);
		
		txtNom = new JTextField();
		txtNom.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				lblInfo.setText("");
			}
		});
		txtNom.setBounds(165, 88, 204, 20);
		contentPane.add(txtNom);
		txtNom.setColumns(10);
		
		lblInfo = new JLabel("");
		lblInfo.setForeground(Color.RED);
		lblInfo.setBounds(165, 180, 264, 14);
		contentPane.add(lblInfo);
		
		txtCode = new JTextField();

		txtCode.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				lblInfo.setText("");
			}
		});
		
		txtCode.setForeground(Color.BLACK);
		//txtCode.setForeground(new Color(253, 245, 230));
		//txtCode.setText("Guess5");
		txtCode.setBounds(165, 136, 204, 20);
		contentPane.add(txtCode);
		txtCode.setColumns(10);
		
		JLabel lblVotreCode = new JLabel("Votre Code:");
		lblVotreCode.setBounds(44, 139, 73, 14);
		contentPane.add(lblVotreCode);
		
		JButton btnMenu = new JButton("Accueil");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				new FirstInterface().getFrmGuess().setVisible(true);;
			}
		});
		btnMenu.setBounds(22, 359, 133, 23);
		contentPane.add(btnMenu);
		
	}
	
}
