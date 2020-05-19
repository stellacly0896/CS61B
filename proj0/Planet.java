public class Planet{
	private static final double G = 6.67e-11; //Gravitational constant

	public double xxPos; //current x position
	public double yyPos; //current y position
	public double xxVel; //current velocity in the x position
	public double yyVel; //current velocity in the y position
	public double mass;
	public String imgFileName;

// Constructor to initialize an instance of the Planet class
	public Planet(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
// Constructor to initialize an identical Planet object
	public Planet(Planet p){
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

// Calculate the distance between two Planets
	public double calcDistance(Planet p){
		double dx = p.xxPos - this.xxPos;
		double dy = p.yyPos - this.yyPos;
		double distance = Math.sqrt(dx * dx + dy * dy);
		return distance;
	}

// Calculate the force exerted on this Planet by the given Planet
	public double calcForceExertedBy(Planet p){
		double r = this.calcDistance(p);
		double F = (G * this.mass * p.mass) / (r * r);
		return F;
	}

	public double calcForceExertedByX(Planet p){
		double F = this.calcForceExertedBy(p);
		double r = this.calcDistance(p);
		double dx = p.xxPos - this.xxPos;
		double fX = (F * dx) / r;
		return fX;
	}
	public double calcForceExertedByY(Planet p){
		double F = this.calcForceExertedBy(p);
		double r = this.calcDistance(p);
		double dy = p.yyPos - this.yyPos;
		double fY = (F * dy) / r;
		return fY;
	}

	public double calcNetForceExertedByX(Planet [] allPlanets){
		double netX = 0;
		for(Planet planet : allPlanets){
			if (!this.equals(planet)){//ignore any planet in the array that is equal to the current planet
				netX += calcForceExertedByX(planet);
			}
		}
		return netX;
	}

	public double calcNetForceExertedByY(Planet [] allPlanets){
		double netY = 0;
		for(Planet planet : allPlanets){
			if (!this.equals(planet)){
				netY += calcForceExertedByY(planet);
			}
		}
		return netY;
	}

	public void update(double dt,double fX,double fY){
		double aX = fX / this.mass;
		double aY = fY / this.mass;
		this.xxVel = this.xxVel + dt * aX;
		this.yyVel = this.yyVel + dt * aY;
		this.xxPos = this.xxPos + dt * this.xxVel;
		this.yyPos = this.yyPos + dt * this.yyVel;

	}

	public void draw(){
		StdDraw.picture(this.xxPos,this.yyPos,this.imgFileName);
	}

}