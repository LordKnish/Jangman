import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
public class HangmanGraphical {
	private static boolean checksize = false;
	private static int wordsizey = 4;
	private static int round = 1;
	private static int scorey = 0;
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException  {
		// TODO Auto-generated method stub
		 Scanner sc = new Scanner(System.in);
		 int lives = 6;
		 
		 String guess = null;
		 ArrayList<String> list = new ArrayList<String>();
		 ArrayList<String> output = new ArrayList<String>();
		 ArrayList<String> wordsdone = new ArrayList<String>();
		 ArrayList<String> high = new ArrayList<String>();
		 ArrayList<String> highname = new ArrayList<String>();
		 int highscore = 0;
	        // New BufferedReader.
	        String documents = new JFileChooser().getFileSystemView().getDefaultDirectory().toString();
	        File file3 = new File(documents + "/words.txt");
		      //Create the file
		        if (file3.createNewFile()){
		        System.out.println("File is created. Please copy the words document contents from the Github to the words.txt");
		        }else{
		        System.out.println("Dictionary loaded.");
		        }
	        BufferedReader reader = new BufferedReader(new FileReader(documents + "/words.txt"));
	        // Add all lines from file to ArrayList.
	        while (true) {
	            String line = reader.readLine();
	            if (line == null) {
	                break;
	            }
	            list.add(line);
	        }

	        // Close it.
	        reader.close();

	        // Print size of ArrayList.
	        System.out.println("Lines: " + list.size());

	        // Print each line.
	        String currentword = list.get(new Random().nextInt(list.size()));
	     
	        
	        // Create the board
	        for(int x = 0; x < currentword.length(); x++){
	        	output.add("_");

	        }
	        //create hangman graphics
	        JFrame window = new JFrame();
	        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        window.setBounds(40, 40, 420, 430);
		     window.getContentPane().add(new MyCanvas());
		        window.setVisible(true);
	        boolean start = true;
	        String menu = "";
	        String wincheck = "";
	     
	      

	        File file = new File(documents + "/hangmanhighscore.txt");
	      //Create the file
	        if (file.createNewFile()){
	        System.out.println("Highscore document created.");
	        }else{
	        }
	         
	        //Write Content
	        FileWriter writer = new FileWriter(file);
	        writer.write("0/computer");
	        writer.close();
	        
	        //start program
	        System.out.println("Welcome to Jangman \n" + "[1] Classic Mode \n" + "[2] Rapid Mode" + "\n Inset a number");
	        menu = sc.next();
	        
	        if(menu.equals("1")){
	        	currentword = list.get(new Random().nextInt(list.size()));
	        while(lives != 0 && menu.equals("1")){
	        	//Check is won
	        	wincheck = "";
	        	for(int x = 0; x < currentword.length(); x++){
	        		wincheck = wincheck + output.get(x);
	        	}
	        	if(wincheck.equals(currentword)){
	        		System.out.println(" You Won! ");
	        		lives = -1;
	        	}
	        	//rewrite the board to include any words that are guessed
		        for(int x = 0; x < currentword.length(); x++){
		        	System.out.print(" " + output.get(x) + " ");

		        }
	        	System.out.print(" Letters Used:");
		        for(int x = 0; x < wordsdone.size(); x++){
	
		        	System.out.print("[" + wordsdone.get(x) + "]");

		        }
		        System.out.print(" Current Lives: " + lives);
		        if(lives == 5){
		        	 window.setVisible(false);
					     window.getContentPane().add(new MyCanvas6());
					        window.setVisible(true);
		        }
		        if(lives == 4){
		        	 window.setVisible(false);
					     window.getContentPane().add(new MyCanvas4());
					        window.setVisible(true);
		        }
		        if(lives == 3){
		        	 window.setVisible(false);
					     window.getContentPane().add(new MyCanvas3());
					        window.setVisible(true);
		        }
		        if(lives == 2){
		        	 window.setVisible(false);
					     window.getContentPane().add(new MyCanvas2());
					        window.setVisible(true);
		        }
		        if(lives == 1){
		        	 window.setVisible(false);
					     window.getContentPane().add(new MyCanvas1());
					        window.setVisible(true);
		        }

		        System.out.println();
		        //input your guess
		        guess = sc.next();
		        wordsdone.add(guess);
		        
		        //check the guess
		        int y = 0;
		        if(guess == currentword){
		        	System.out.println(" You Won! ");
		        }
		        else if(currentword.indexOf(guess) != -1){
					for(int x = 0; x < currentword.length(); x++){
						if(currentword.substring(x, x+1).equals(guess)){
							output.set(x,guess);
						}
					}
		        }
		        else{
		        	lives = lives - 1;

		        }
	        }


	        if(lives == 0){
	        	
	        	window.setVisible(false);
			     window.getContentPane().add(new MyCanvas0());
			        window.setVisible(true);
	        	System.out.println(" YOU LOSE ");
	        	System.out.println("The word was: " + currentword);
	        	
	        }
	        }
	        
	        //rapid fire mode
	        else if(menu.equals("2")){

	        	//get highscore
		        BufferedReader highreader = new BufferedReader(new FileReader(documents + "/hangmanhighscore.txt"));
	        	  while (true) {
	  	            String line2 = highreader.readLine();
	  	            if (line2 == null) {
	  	                break;
	  	            }
	  	            high.add(line2);
	  	        }
		  	        reader.close();
		  	        
		  	        //get highscore name

	        	  //make sure its 4 length
		        	checksize = false;

	  	        // Close it.
	        	int locationofbar = 0;
	        	String highy = high.get(high.size()-1);
	        	highscore = Integer.parseInt(highy.substring(0,highy.indexOf("/")));

	        	locationofbar = highy.indexOf("/");
	  	        System.out.println("The highscore is  " + highy.substring(0,locationofbar) + " and is owned by: " + highy.substring(locationofbar+1,highy.length()));
		        while(lives != 0 && menu.equals("2")){
		        	 for(int x = 0; x < currentword.length(); x++){
				        	System.out.print(" " + output.get(x) + " ");

				        }
		        	//Check if won
		        	wincheck = "";
		        	 //check the guess
			        for(int x = 0; x < currentword.length(); x++){
		        		wincheck = wincheck + output.get(x);
		        	}
		        	if(wincheck.equals(currentword)){
	
		        		if(wordsizey != 17){
			        		wordsizey++;
			        	}
			 
			        	currentword = list.get(new Random().nextInt(list.size()));
			        	while(checksize == false){
			        		if(currentword.length() != wordsizey){
			        		currentword = list.get(new Random().nextInt(list.size()));
			        		}
			        		else{
			        			checksize = true;
			        		}
			        	}
			        	scorey = scorey + lives*10;
			            System.out.println("Score: " + scorey);
			            System.out.println("Round: " + round);
			            wordsdone.clear();
			            wincheck = "";
			            output.clear();
		                lives = lives + 3;
				        for(int x = 0; x < currentword.length(); x++){
				        	output.add("_");

				        }
		        	}

		        	
		        	//rewrite the board to include any words that are guessed
			       
		        	System.out.print(" Letters Used:");
			        for(int x = 0; x < wordsdone.size(); x++){
		
			        	System.out.print("[" + wordsdone.get(x) + "]");

			        }
			        System.out.print(" Current Lives: " + lives);
			        if(lives == 5){
			        	 window.setVisible(false);
						     window.getContentPane().add(new MyCanvas6());
						        window.setVisible(true);
			        }
			        if(lives == 4){
			        	 window.setVisible(false);
						     window.getContentPane().add(new MyCanvas4());
						        window.setVisible(true);
			        }
			        if(lives == 3){
			        	 window.setVisible(false);
						     window.getContentPane().add(new MyCanvas3());
						        window.setVisible(true);
			        }
			        if(lives == 2){
			        	 window.setVisible(false);
						     window.getContentPane().add(new MyCanvas2());
						        window.setVisible(true);
			        }
			        if(lives == 1){
			        	 window.setVisible(false);
						     window.getContentPane().add(new MyCanvas1());
						        window.setVisible(true);
			        }

			        System.out.println();
			        //input your guess

			        
			       
			        guess = sc.next();
			        wordsdone.add(guess);
			        int y = 0;
			        if(guess == currentword){
			            lives = lives + 3;
			        	if(wordsizey != 17){
			        		wordsizey++;
			        	}
			        	checksize = false;
			        	currentword = list.get(new Random().nextInt(list.size()));
			        	while(checksize = false){
			        		if(currentword.length() != wordsizey){
			        		currentword = list.get(new Random().nextInt(list.size()));
			        		}
			        		else{
			        			checksize = true;
			        		}
			        	}
			            System.out.println("Score: " + lives*10);
			            System.out.println("Round: " + round);
			 
			            
			        }
			        else if(currentword.indexOf(guess) != -1){
						for(int x = 0; x < currentword.length(); x++){
							if(currentword.substring(x, x+1).equals(guess)){
								output.set(x,guess);
							}
						}
			        }
			        else if(guess.equals("getwordy1234")){
			        	System.out.println(currentword);
			        }
			        else{
			        	lives = lives - 1;

			        }
		        }
		        System.out.println(highy.substring(0,highy.indexOf("/")));
		        if(lives == 0){
		        	window.setVisible(false);
				     window.getContentPane().add(new MyCanvas0());
				        window.setVisible(true);
		        	System.out.println(" YOU LOSE ");
		        	System.out.println("The word was: " + currentword);
		        	
		        	//write highscore
		        	window.setVisible(false);
		        	String highscorename = "null";
		        	System.out.println("Input a name for the highscore");
		        	highscorename = sc.next();
		        if(scorey > Integer.parseInt(highy.substring(0,highy.indexOf("/")))){
		        	 BufferedWriter bw = null;
		             try {
		       	 String mycontent = Integer.toString(scorey) + "/" + highscorename;
		                //Specify the file name and path here
		       	 File file2 = new File(documents + "/hangmanhighscore.txt");

		       	 /* This logic will make sure that the file 
		       	  * gets created if it is not present at the
		       	  * specified location*/
		       	  if (!file.exists()) {
		       	     file.createNewFile();
		       	  }

		       	  FileWriter fw = new FileWriter(file);
		       	  bw = new BufferedWriter(fw);
		       	  bw.write(mycontent);
		                 System.out.println("Highscore written Successfully");

		             } catch (IOException ioe) {
		       	   ioe.printStackTrace();
		       	}
		       	finally
		       	{ 
		       	   try{
		       	      if(bw!=null)
		       		 bw.close();
		       	   }catch(Exception ex){
		       	       System.out.println("Error in closing the BufferedWriter"+ex);
		       	    }
		       	}
		        }
		        }
	}
	}
		        
		       	 //write highscore name
		         
	

	private static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

	
}
class MyCanvas extends JComponent{

	  public void paint(Graphics g) {
	    g.drawLine (30, 390, 200, 390);  
	    g.drawLine (115, 390, 115, 100);  
	    int location = 0; 
	    int color = 0;
	    g.drawLine (115, 100, 225, 100); 
	    g.drawLine (225, 100, 225, 120);  
	  }
}
	  class MyCanvas6 extends JComponent{

		  public void paint(Graphics g) {
		   // g.drawOval (170, 120, 105, 115);  
			  g.drawOval (185, 120, 75, 80);  
		  }
	}
	  class MyCanvas4 extends JComponent{
		  public void paint(Graphics g) {
	    g.drawLine (225, 200, 225, 280); 
	  }
		  
	  }
	  class MyCanvas3 extends JComponent{
		  public void paint(Graphics g) {
	    g.drawLine (225, 220, 295, 200); 
	  }
	  }
	  class MyCanvas2 extends JComponent{
		  public void paint(Graphics g) {
	    g.drawLine (225, 220, 165, 210);
			//  g.drawLine (295, 20, 225, 220); 
			  
	  }
	  }
	  class MyCanvas1 extends JComponent{
		  public void paint(Graphics g) {
	    g.drawLine (225, 280, 165, 310);
			//  g.drawLine (295, 20, 225, 220); 
			  
	  }
	  }
	  class MyCanvas0 extends JComponent{
		  public void paint(Graphics g) {
	    g.drawLine (225, 280, 275, 310);
			//  g.drawLine (295, 20, 225, 220); 
			  
	  }
	  }