package entity;

import java.util.Random;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random rand = new Random();
        int str=0;
        int randomNum = 0;
       	for(int i = 0 ; i < 3 ; i++){
		  //char c = (char)(rand.nextInt(26) + 97);
		  randomNum = rand.nextInt((10 - 1) + 1) + 1;
		  str += randomNum;
		 // System.out.print(str);
       	}
       	String code ="";
       	String nom = "Imad";
       	String Prenom ="Benoit" ;
       	
       	String part = nom.toLowerCase().substring(0,2).concat(Prenom.toLowerCase().substring(0,2)).concat(""+str);
       	System.out.println(part);
	}

}
