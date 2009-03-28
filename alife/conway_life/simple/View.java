package alife.conway_life.simple;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class View extends JFrame
{
	private static final long	serialVersionUID	= 1L;

	private static final int	X					= 100;
	private static final int	Y					= 100;

	private static final int	MAGNIFY				= 5;
	
	private static final int	BORDER_OFFSET		= 5;
	private static final int	YOFFSET				= 25;

	private static final Color	ALIVE				= Color.green.darker ();
	private static final Color	DEAD				= Color.gray.brighter ();

	protected View ( short width, short height )
	{
		super ( "The Simple Life", null );

		setBounds ( X, Y, width * MAGNIFY + BORDER_OFFSET * 2, height * MAGNIFY + YOFFSET + BORDER_OFFSET );
		setDefaultCloseOperation ( EXIT_ON_CLOSE );
		setResizable ( false );

		setVisible ( true );
	}

	protected void print ( boolean[][] field )
	{
		Graphics graphics = getGraphics ();

		// graphics.setColor ( DEAD );
		// graphics.setPaintMode ();
		// graphics.drawString ( "Waves!", 300, 400 );
		// graphics.fillRect ( 0, 0, field.length, field[0].length);

		// graphics.drawRect ( 0, 0, field.length * 2 - 100, field[0].length * 2
		// - 100 );

		for (short x = 0; x < field.length; x++)
			for (short y = 0; y < field[0].length; y++)
			{
				if (field[x][y])
					graphics.setColor ( ALIVE );

				else
					graphics.setColor ( DEAD );

				graphics.fillRect ( x * MAGNIFY + BORDER_OFFSET, y * MAGNIFY + YOFFSET, MAGNIFY + 1, MAGNIFY + 1 );
			}

		// paint ( graphics );

		try
		{
			Thread.sleep ( 60 );
		} catch (InterruptedException ignore)
		{
		}

	}

}
