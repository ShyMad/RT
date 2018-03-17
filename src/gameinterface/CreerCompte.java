package gameinterface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entity.Joueur;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CreerCompte extends JFrame {

	private JPanel contentPane;
	private JTextField txtNom;
	private JTextField txtPrenom;
	private JLabel lblSuccess;
	private JButton btnConnexion;
	private JButton btnAjouter;
	private String Code;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreerCompte frame = new CreerCompte();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws FileNotFoundException 
	 */
	
	public void CreatePlayer() throws FileNotFoundException {
		Joueur j = null;
		String nom = txtNom.getText();
		String prenom = txtPrenom.getText();
		String code="";
		Random rand = new Random();
        int str=0;
        int randomNum = 0;
        String part = "" ;
       	for(int i = 0 ; i < 5 ; i++){
		  //char c = (char)(rand.nextInt(26) + 97);
		  randomNum = rand.nextInt((10 - 1) + 1) + 1;
		  str += randomNum;
		  //System.out.print(c+" ");
       	}
       	
		if((txtNom.getText()!="" || txtPrenom.getText()!="")&&(txtNom.getText().length()>=2)&&txtPrenom.getText().length()>=2) {
			part = nom.toLowerCase().substring(0,2).concat(prenom.toLowerCase().substring(0,2)).concat(""+str);
			j = new Joueur(nom,prenom,part);
			code = part;
			 String player = j.getNom()+" "+j.getPrenom()+" "+j.getCodeLicencie();
		        File file = new File("authFile.txt");
		        
		        FileWriter fw;
		         try {
		            //Création de l'objet
		            fw = new FileWriter(file,true);
		           //On écrit la chaîne
		            fw.write(player+"\n");
		        	 //PrintStream fileStream = new PrintStream(file);
		        	 //fileStream.println(player);
		            //On ferme le flux
		            fw.close();
		        }catch (FileNotFoundException e) {
		            e.printStackTrace();
		          } catch (IOException e) {
		            e.printStackTrace();
		          }
		         btnAjouter.setEnabled(false);
		         lblSuccess.setText("Votre code: "+part);
		            //this.setVisible(false);
			       // new ClientGame().setVisible(true);
		
		}else {
			 btnAjouter.setEnabled(false);
			lblSuccess.setText("Inserer Votre Nom et Prenom");
			JOptionPane.showMessageDialog(this,
				    "Entrer votre nom et prenom.",
				    "Inane error",
				    JOptionPane.ERROR_MESSAGE);
		}
		
	}
	public CreerCompte() {
		setTitle("Guess5");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300) ;
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtNom = new JTextField() ;
		txtNom.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				btnAjouter.setEnabled(true);
			}
		});
		txtNom.setBounds(237, 85, 130, 20);
		contentPane.add(txtNom);
		txtNom.setColumns(10);
		
		txtPrenom = new JTextField();
		txtPrenom.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				btnAjouter.setEnabled(true);
			}
		});
		txtPrenom.setBounds(237, 128, 130, 20);
		contentPane.add(txtPrenom);
		txtPrenom.setColumns(10);
		
		JLabel lblVotreNom = new JLabel("Votre Nom :");
		lblVotreNom.setBounds(49, 88, 69, 14);
		contentPane.add(lblVotreNom);
		
		JLabel lblVotrePrenom = new JLabel("Votre Prenom :");
		lblVotrePrenom.setBounds(49, 131, 86, 14);
		contentPane.add(lblVotrePrenom);
		
		JLabel lblCrerVotreCompte = new JLabel("Cr\u00E9er Votre Compte!");
		lblCrerVotreCompte.setForeground(new Color(0, 128, 0));
		lblCrerVotreCompte.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblCrerVotreCompte.setBounds(49, 26, 224, 31);
		contentPane.add(lblCrerVotreCompte);
		
		btnAjouter = new JButton("Ajouter");
		btnAjouter.setEnabled(false);
		btnAjouter.setBackground(new Color(34, 139, 34));
		btnAjouter.setBounds(46, 200, 89, 23);
		contentPane.add(btnAjouter);
		
		lblSuccess = new JLabel("");
		lblSuccess.setForeground(new Color(244, 164, 96));
		lblSuccess.setBounds(156, 236, 117, 14);
		contentPane.add(lblSuccess);
		
		btnConnexion = new JButton("Se Connecter");
		btnConnexion.setBackground(new Color(32, 178, 170));
		btnConnexion.setBounds(272, 200, 123, 23);
		contentPane.add(btnConnexion);
		
		btnAjouter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					CreatePlayer();
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}
