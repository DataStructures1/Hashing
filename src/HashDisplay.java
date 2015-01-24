
import java.awt.*;
import javax.swing.*;

public class HashDisplay extends Canvas {
	
	private int offsetV;				               //pixels
	public static HashObject hashTable;
	private int[] collisions = new int[ hashTable.getCapacity() ];
	int width, height;
	
	public HashDisplay( int w, int h ) {
		
		width = w;
		height = h;
		offsetV = 60;
		setSize(new java.awt.Dimension(width, height));
		setBackground( Color.white );
		
	}
	
	public void paintProbe( int index ) {
		
		collisions[index] ++;
		Graphics g = getGraphics();
		g.setColor( Color.red );
		
		int xCoord = index%width;
		int yCoord = index/width + 1;
		
		g.fillRect( xCoord, yCoord*offsetV - collisions[index], 1,1 );
	}
	
    //paint on the canvas object
	public void paint( Graphics g )
	{
		super.paint( g );
		
		//erase
		g.setColor( Color.white );
		g.fillRect(0,0,getWidth(), getHeight() );
		
		//border
		g.setColor( Color.black );					
		g.drawLine( 0, 0, 0, getHeight()-1 );				
		g.drawLine( 0, 0, getWidth()-1, 0 );	
		g.drawLine( 0, getHeight()-1, getWidth()-1, getHeight()-1 );				
		g.drawLine( getWidth()-1, 0, getWidth()-1, getHeight()-1 );	
		
		for(int i=0; i<hashTable.getCapacity()/width; i++ )
			g.drawLine( 0, (i+1)*offsetV, getWidth()-1, (i+1)*offsetV );	
	} 
}
