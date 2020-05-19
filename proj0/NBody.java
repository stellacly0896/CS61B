
public class NBody{
	@Deprecated
	public static double readRadius(String filename){
		In in = new In(filename);
		in.readInt(); //this int will not be used for this method
		double R = in.readDouble();
		return R;
	}

	@Deprecated
	public static Planet[] readPlanets(String filename){
		In in = new In(filename);
		int N = in.readInt(); // the number of planets
		in.readDouble();
		Planet[] planets = new Planet[N];

		for (int i=0;i< N;i++){
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String imgFileName = in.readString();
			planets[i] = new Planet(xxPos,yyPos,xxVel,yyVel,mass,imgFileName);
		}
		return planets;
	}


	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		Planet[] planets = readPlanets(filename);
		double radius = readRadius(filename);


		StdDraw.enableDoubleBuffering();

		double time = 0;
		while (time<T){
			double[] xForces = new double[planets.length];
			double[] yForces = new double[planets.length];

			for (int i=0;i<planets.length;i++){
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}

			for (int i=0;i<planets.length;i++){ 
				planets[i].update(dt,xForces[i],yForces[i]);
			}

			StdDraw.setScale(-radius, radius);
			StdDraw.clear();
			StdDraw.picture(0,0,"images/starfield.jpg");
			for(Planet planet : planets){
				planet.draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
			time += dt;
		}
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
		    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		    		planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
		    		planets[i].yyVel, planets[i].mass, planets[i].imgFileName); 
		}
	}
}


