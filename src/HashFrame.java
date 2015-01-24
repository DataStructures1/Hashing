
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class HashFrame extends JFrame { 
			
	public static HashObject hashObject;
	private HashDisplay hashDisplay;
	
	public HashFrame( int width, int height  )	{
		
		hashDisplay = new HashDisplay( width, height );
		
		HashObject.hashDisplay = hashDisplay; 
		setSize(new java.awt.Dimension(width, height));
		getContentPane().add( hashDisplay );

    }

  }
