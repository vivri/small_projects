package alife.conway_life.simple;

import java.util.Random;

public class Life
{
	private static boolean	field[][];
	private static long		ticks;

	public static void main ( String[] args )
	{
		if (args.length != 2)
			return;

		short x = Short.parseShort ( args[0] );
		short y = Short.parseShort ( args[1] );

		init_random ( x, y );

		View view = new View ( x, y );

		//for (int i = 0;i < 50; i++)
		while (true)
		{
			view.print ( tick () );
			//print();
			//tick();
		}
	}

	private static void init ( short width, short height )
	{
		field = new boolean[width][height];

		for (short x = 0; x < width; x++)
			for (short y = 0; y < height; y++)
				field[x][y] = false;

		ticks = 0;
	}

	private static void init_random ( short width, short height )
	{
		field = new boolean[width][height];

		Random rnd = new Random ( System.currentTimeMillis () );

		for (short x = 0; x < width; x++)
			for (short y = 0; y < height; y++)
			{
				if (rnd.nextDouble () < 0.70)
					field[x][y] = false;
				else
					field[x][y] = true;
			}

		ticks = 0;
	}

	private static boolean[][] tick ()
	{
		int liv_neighbours;
		boolean copy[][] = new boolean[field.length][field[0].length];

		for (int x = 0; x < field.length; x++)
			for (int y = 0; y < field[0].length; y++)
			{
				// CALCULATING LIVING NEIGHBORS

				liv_neighbours = 0;

				if (x > 0)
				{
					if (field[x - 1][y])
						liv_neighbours++;

					if (y > 0 && field[x - 1][y - 1])
						liv_neighbours++;

					if (y < field[0].length - 1 && field[x - 1][y + 1])
						liv_neighbours++;
				}

				if (x < field.length - 1)
				{
					if (field[x + 1][y])
						liv_neighbours++;

					if (y > 0 && field[x + 1][y - 1])
						liv_neighbours++;

					if (y < field[0].length - 1 && field[x + 1][y + 1])
						liv_neighbours++;
				}

				if (y > 0 && field[x][y - 1])
					liv_neighbours++;

				if (y < field[0].length - 1 && field[x][y + 1])
					liv_neighbours++;

				// APPLYING RULES:

				//System.out.println("Living neighbors: " + liv_neighbours);

				if (field[x][y])
				{
					if (liv_neighbours < 2 || 3 < liv_neighbours)
						copy[x][y] = false;
					else
						copy[x][y] = true;
				}

				else
				{
					if (liv_neighbours == 3)
						copy[x][y] = true;
					else
						copy[x][y] = false;
				}

			}

		//copying from temp to real
		for (int x = 0; x < field.length; x++)
			for (int y = 0; y < field[0].length; y++)
				field[x][y] = copy[x][y];

		ticks++;

		return copy;
	}

	private static void print ()
	{
		System.out.printf ( "Iteration: %d\n", ticks );

		System.out.print ( "---------------------------\n" );

		for (int i = 0; i < field.length; i++)
		{
			for (int j = 0; j < field[0].length; j++)
				System.out.printf ( "%c ", field[i][j] ? '#' : ' ' );

			System.out.print ( "\n" );
		}
		System.out.print ( "---------------------------\n\n\n" );

	}
}
