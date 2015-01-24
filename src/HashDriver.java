
//CLOSED Hashing Demo

import javax.swing.*;

import java.awt.*;

public class HashDriver {
   private static String[] collisionHandler = { "1-adjacency", "2-linear",
		 "3-quadratic", "4-custom"  };

   public static void main(String[] args) {
	  String str = (String) JOptionPane.showInputDialog(null,
			"Select a Hashing Algorithm", "HASHING", JOptionPane.PLAIN_MESSAGE,
			null, collisionHandler, "select");

	  // convert the menu selection to an int
	  int rehasherNumber = (int) str.charAt(0) - 49;

	  // instantiate the has table object
	  // 5003 is the number of bins
	  HashObject hObject = new HashObject(5003, rehasherNumber);

	  // set class variables in the support classes.
	  HashDisplay.hashTable = hObject;
	  HashFrame.hashObject = hObject;

	  // set up the GUI
	  HashFrame hFrame = new HashFrame(800, 500);
	  hFrame.setLocation(new java.awt.Point(100, 150));
	  hFrame.setVisible(true);

	  // load the strings into the hash table
	  // there are 4835 words in the file
	  hObject.loadTable("GradRecWords.txt");

	  System.out.println(hObject);
	  
	  while (true) {
		  //System.out.println("Load Factor: " + 4835/5003. );
		 String target = JOptionPane.showInputDialog(null, "What word shall we find? ");

		 int indexOfMatch = hObject.find(target);

		 if (indexOfMatch < 0)
			System.out.println("\n" + target + " is not in the list! ");
		 else
			System.out.println("\n"+target + " was found at index " + indexOfMatch);
	  }
   }
}
