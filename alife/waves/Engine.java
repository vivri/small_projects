package alife.waves;

import java.util.Random;

public class Engine
{
	/*
	 * Height values: [-1,1], with 0.0 being level.
	 */
	private final static float	NORMALIZING_FACTOR	= (float) 1;

	private final static float	PERSISTENCE			= (float) 2.0;
	private final static float	INERTIA_FACTOR		= (float) 4.0;
	private final static float	LEVELING_TENDENCY	= (float) 0.5;
	private final static float	CELLULAR_BONDAGE	= (float) 1.0;

	// private final static float RAINDROP_CHANCE = (float) 0.001;

	// FOR FUTURE IMPLEMENTATIONS
	private final static float	INPUT_STENGTH		= (float) -0.6;

	private Cell				field[][];
	private float				copy[][];

	private boolean				input[][];							// for every given iteration, sees whether there's input to some region.

	protected long				iteration;

	protected Engine ( boolean init_random, short x, short y )
	{
		if (init_random)
			init_rand ( x, y );
		else
			init_level ( x, y );

		iteration = 0;
	}

	private void init_level ( short x, short y )
	{
		field = new Cell[x][y];
		copy = new float[x][y];
		input = new boolean[x][y];

		for (int i = 0; i < x; i++)
			for (int j = 0; j < y; j++)
			{
				field[i][j] = new Cell ( 0, 0 );
				input[i][j] = false;
			}

	}

	private void init_rand ( short x, short y )
	{
		field = new Cell[x][y];
		copy = new float[field.length][field[0].length];
		input = new boolean[x][y];

		Random r = new Random ( System.currentTimeMillis () );

		for (int i = 0; i < x; i++)
			for (int j = 0; j < y; j++)
			{
				float h = (r.nextFloat () - (float) 0.5) * 2; // fitted to [-1, 1)
				field[i][j] = new Cell ( h, 0 );
				input[i][j] = false;
			}
	}

	protected final float[][] tick ()
	{
		for (int x = 0; x < field.length; x++)
			for (int y = 0; y < field[0].length; y++)
			{
				/*
				 * double rand = Math.random (); if (rand < RAINDROP_CHANCE) { field[x][y] = (float) 0.3; } else {
				 */

				/*
				 * READING NEIGHBORING CELLS Each cell has maximum 8 neighbors, minimum 3. Walls do not count, nor wrap-around.
				 */
				byte neighbors = 0;
				float sum_of_neighbors = 0;

				if (x > 0) // 3 cases
				{
					neighbors++;
					sum_of_neighbors += field[x - 1][y].height;

					if (y > 0)
					{
						neighbors++;
						sum_of_neighbors += field[x - 1][y - 1].height;
					}
					if (y < field[0].length - 1)
					{
						neighbors++;
						sum_of_neighbors += field[x - 1][y + 1].height;
					}
				}

				if (x < field.length - 1) // 3 cases
				{
					neighbors++;
					sum_of_neighbors += field[x + 1][y].height;

					if (y > 0)
					{
						neighbors++;
						sum_of_neighbors += field[x + 1][y - 1].height;
					}
					if (y < field[0].length - 1)
					{
						neighbors++;
						sum_of_neighbors += field[x + 1][y + 1].height;
					}
				}

				// 2 remaining cases
				if (y > 0)
				{
					neighbors++;
					sum_of_neighbors += field[x][y - 1].height;
				}

				if (y < field[0].length - 1)
				{
					neighbors++;
					sum_of_neighbors += field[x][y + 1].height;
				}

				// CALCULATING:

				float neighbor_average = sum_of_neighbors / (float) neighbors;
				float neighbour_dif = neighbor_average - field[x][y].height;

				float from_input = 0;
				if (input[x][y])
					from_input = INPUT_STENGTH;

				// calculating the new height:
				copy[x][y] = field[x][y].height * PERSISTENCE + neighbour_dif * CELLULAR_BONDAGE - field[x][y].height * LEVELING_TENDENCY + field[x][y].y_vector * INERTIA_FACTOR + from_input;

				//Normalizing to [-1, 1]:
				copy[x][y] = copy[x][y] / ((float) Math.sqrt ( (float) Math.pow ( copy[x][y], 2 ) + NORMALIZING_FACTOR ));

				// calculating the new vector:
				field[x][y].y_vector = copy[x][y] - field[x][y].height;
				
				//cleaning input
				input[x][y] = false;

			}
		for (int x = 0; x < field.length; x++)
			for (int y = 0; y < field[0].length; y++)
				field[x][y].height = copy[x][y];
		
		iteration++;

		return copy;
	}

	/*
	 * This is for future implementations.
	 */
	protected boolean register_input ( int x, int y )
	{
		if (x < 0 || x > input.length || y < 0 || y > input[0].length)
			return false;

		input[x][y] = true;
		return true;

	}

	protected int getX ()
	{
		return field.length;
	}

	protected int getY ()
	{
		return field[0].length;
	}

	private class Cell
	{
		protected float	height;
		protected float	y_vector;

		protected Cell ( float h, float v )
		{
			height = h;
			y_vector = v;
		}
	}

}
