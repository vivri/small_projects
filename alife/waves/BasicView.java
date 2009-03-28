package alife.waves;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class BasicView extends JFrame
{
	private static final long	serialVersionUID	= 1L;

	private static final Color	base				= Color.magenta.darker ().darker ();
	
//	private static final float	color_coeficcient	= 50; 

	private static final Color	dark1				= base.darker ();
	private static final Color	dark2				= dark1.darker ();
	private static final Color	dark3				= dark2.darker ();

	private static final Color	bright1				= base.brighter ();
	private static final Color	bright2				= bright1.brighter ();
	private static final Color	bright3				= bright2.brighter ();

	private static final float	threshold_1			= (float) -0.71428571;
	private static final float	threshold_2			= (float) -0.42857143;
	private static final float	threshold_3			= (float) -0.14285714;
	private static final float	threshold_4			= (float) 0.14285714;
	private static final float	threshold_5			= (float) 0.42857143;
	private static final float	threshold_6			= (float) 0.71428571;

	private static final int	X					= 100;
	private static final int	Y					= 100;

	private static final int	MAGNIFY				= 3;
	
	private static final int	BORDER_OFFSET		= 5;
	private static final int	YOFFSET				= 25;
	
	protected BasicView ( short width, short height )
	{
		super ( "Waves 0.1", null );

		setBounds ( X, Y, width * MAGNIFY + BORDER_OFFSET * 2, height * MAGNIFY + YOFFSET + BORDER_OFFSET);
		setDefaultCloseOperation ( EXIT_ON_CLOSE );
		setResizable ( false );

		setVisible ( true );
	}

	protected void repaint_waves ( final float[][] field, long iteration)
	{
		Graphics graphics = getGraphics ();

		graphics.setColor ( base );
		setTitle ( "Waves 0.1");

		graphics.setPaintMode ();
		//graphics.drawString ( "Waves!", 300, 400 );
		//paint ( graphics );
		
		//graphics.drawRect ( 0, 0, field.length * 2 - 100, field[0].length * 2 - 100 );
		
		for (short x = 0; x < field.length; x ++)
			for (short y = 0; y < field[0].length; y++)
			{
				//System.out.println("<" + base.getGreen ()+">");
				//Color c = new Color (base.getRed () + (int)(field[x][y] * 10), base.getGreen (), base.getBlue ());
				
				//graphics.setColor ( c );
				
				if (field[x][y] < threshold_1)
					graphics.setColor ( dark3 );
				else if (field[x][y] < threshold_2)
					graphics.setColor ( dark2 );
				else if (field[x][y] < threshold_3)
					graphics.setColor ( dark1 );
				else if (field[x][y] < threshold_4)
					graphics.setColor ( base );
				else if (field[x][y] < threshold_5)
					graphics.setColor ( bright1 );
				else if (field[x][y] < threshold_6)
					graphics.setColor ( bright2 );
				else
					graphics.setColor ( bright3 );
				
				graphics.fillRect ( x * MAGNIFY + BORDER_OFFSET, y * MAGNIFY + YOFFSET, MAGNIFY + 1, MAGNIFY + 1 );
				
			}
		
		//paint ( graphics );

		try
		{
			Thread.sleep ( 75 );
		}
		catch (InterruptedException ignore)
		{
		}
	}
}
