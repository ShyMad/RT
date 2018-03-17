package gameinterface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.Font;

public class ClientGame extends JFrame {

	private JPanel contentPane;
	private JTextField txtWord;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientGame frame = new ClientGame();
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
	public ClientGame() {
		setTitle("Guess5");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 606, 467);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 215, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnStartGame = new JButton("Lancer une partie");
		btnStartGame.setBackground(new Color(100, 149, 237));
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnStartGame.setBounds(37, 373, 135, 23);
		contentPane.add(btnStartGame);
		
		JButton btnExit = new JButton("Quitter");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnExit.setBackground(new Color(255, 0, 0));
		btnExit.setBounds(443, 373, 119, 23);
		contentPane.add(btnExit);
		
		JLabel lblInsrerVotreMot = new JLabel("Ins\u00E9rer votre mot ici :");
		lblInsrerVotreMot.setBounds(89, 308, 119, 14);
		contentPane.add(lblInsrerVotreMot);
		
		JButton btnSend = new JButton("Envoyer");
		btnSend.setBackground(new Color(60, 179, 113));
		btnSend.setBounds(422, 304, 89, 23);
		contentPane.add(btnSend);
		
		txtWord = new JTextField() ;
		txtWord.setBounds(203, 305, 194, 20);
		contentPane.add(txtWord);
		txtWord.setColumns(10);
		
		JTextPane textListWords = new JTextPane();
		textListWords.setEditable(false);
		textListWords.setBackground(new Color(238, 232, 170));
		textListWords.setBounds(89, 96, 410, 197);
		contentPane.add(textListWords);
		
		JLabel lblTitle = new JLabel("Guess5!");
		lblTitle.setFont(new Font("Trebuchet MS", Font.BOLD, 28));
		lblTitle.setBounds(214, 29, 119, 43);
		contentPane.add(lblTitle);
	}
}
