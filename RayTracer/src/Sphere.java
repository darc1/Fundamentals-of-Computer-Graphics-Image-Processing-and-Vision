import java.util.Random;

public class Sphere extends Surfaces {

	Random random = new Random(6000);
	Point direction;
	float movementSize;
	double radius;
	Point center, intersectionPoint; // used for calculating the normal


	public Sphere(Point center, double radius, int index, Point direction, float movementSize){
		this.center = center;
		this.radius = radius;
		this.material_index = index - 1;
		this.direction = direction;
		this.movementSize = movementSize;
		myType = type.sphere;
	}

	/** return the intersection point if there is */
	public double getIntersection(Point p, Vector rayDirection) {
		// the variables L and tca were taken from the slides

		Vector L = new Vector(p, center);
		if(isMoving()){
			double movement = random.nextDouble()*movementSize;
			double x = (movement*direction.x) + center.x;
			double y = (movement*direction.y) + center.y;
			double z = (movement*direction.z) + center.z;
			L = new Vector(p, new Point(x, y, z));
		}

		double tca = Vector.dotProduct(L, rayDirection);
		if (tca < 0)
			return -1;
		double d = Vector.dotProduct(L, L) - tca * tca;
		if (d > radius * radius)
			return -1;
		double t = tca - Math.sqrt(radius * radius - d);
		return t;
	}

	private boolean isMoving() {
		return direction != null;
	}


	public void setIntersectionPoint(Point intersectionPoint) {
		this.intersectionPoint = intersectionPoint;
	}
	
	
	/** using the intersectionPoint with the sphere, create the sphere's normal */
	public Vector getNormal() {
		Vector normal = new Vector(center, intersectionPoint);
		normal.normalise();
		return normal;
	}

	public type getType() {
		return type.sphere;
	}

	public String toString() {
		return "Sp.: r=" + radius + ", c=" + center;
	}
}
