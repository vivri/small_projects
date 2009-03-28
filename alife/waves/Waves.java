package alife.waves;

import java.util.Random;

public class Waves
{
	private final static float RAINDROP_CHANCE = (float) 1e-2;
	
	private static Random r;
	
	public static void main (String[] args)
	{
		if (args.length != 3)
			return;
		
		Engine engine;
		
		r = new Random (System.currentTimeMillis ());
		
		boolean rain = false;
		
		if (Short.valueOf ( args[2] ) == 0)
			engine = new Engine(true, Short.valueOf ( args[0] ), Short.valueOf ( args[1] ));
		else
		{
			engine = new Engine(false, Short.valueOf ( args[0] ), Short.valueOf ( args[1] ));
			rain = true;
		}
		
		BasicView view = new BasicView (Short.valueOf ( args[0] ), Short.valueOf ( args[1] ));
		
		while (true)
		{
			if (rain)
				rain_on (engine);
			
			view.repaint_waves (engine.tick (), engine.iteration);
		}
	}
	
	private static void rain_on (Engine eng)
	{
		if (r.nextDouble () <= RAINDROP_CHANCE)
		{
			int x = r.nextInt ( eng.getX () );
			int y = r.nextInt ( eng.getY () );
			
			eng.register_input ( x, y );
		}
	}

}
