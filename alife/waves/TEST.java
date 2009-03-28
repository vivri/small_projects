package alife.waves;

public class TEST
{
	public static void main (String[] args)
	{
		if (args.length != 3)
			return;
		
		Engine engine;
		
		if (Short.valueOf ( args[2] ) == 0)
			engine = new Engine(true, Short.valueOf ( args[0] ), Short.valueOf ( args[1] ));
		else
			engine = new Engine(false, Short.valueOf ( args[0] ), Short.valueOf ( args[1] ));		
				
		for (int i = 0; i < 50; i++)
			print (engine.tick (), engine.iteration);
		
	}
	
	private static void print(final float[][] field, long iteration)
	{
		System.out.printf("Iteration: %d\n", iteration);

		for (int i = 0; i < field.length + 2; i++)
			System.out.print("-");
		System.out.print("\n");

		for (int i = 0; i < field.length; i++)
		{
			for (int j = 0; j < field.length; j++)
				System.out.printf("%.2f\t", field[i][j]);

			System.out.print("\n");

		}
		for (int i = 0; i < field.length + 2; i++)
			System.out.print("-");

		System.out.print("\n\n");
	}
}
