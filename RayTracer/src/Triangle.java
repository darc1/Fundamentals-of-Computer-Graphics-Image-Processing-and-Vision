import java.util.Random;

public class Triangle extends Surfaces {

	private Point p1;
	private Point p2;
	private Point p3;
	public Point direction;
	float movementSize;
	double currentRand;
	Random random = new Random();

	public Triangle(Point p1, Point p2, Point p3, int index, Point direction, float movementSize) {
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		this.direction = direction;
		this.movementSize = movementSize;
		this.material_index = index - 1;
		myType = type.triangle;
	}

	public type getType() {
		return type.triangle;
	}

	@Override
	public String toString() {
		return "Tr.: v1=" + getP1() + ", v2=" + getP2() + ", v3=" + getP3();
	}

	public double getIntersection(Point p, Vector dir) {
		if(isMoving()){
			currentRand = random.nextDouble();
		}
		Vector normal = getNormal();
		double d = Vector.pointMulVector(getP1(), normal);
		double t = -((Vector.pointMulVector(p, normal)) - d) / Vector.dotProduct(dir, normal);
		/* checking if the point in the triangle */
		Boolean bool = checkIfInTriangle(p, dir, t);
		if (bool == false)
			return -1;
		return t;
	}
	
	private Boolean checkIfInTriangle(Point p0, Vector dir, double t) {
		return checkSide(p0, dir, t, getP1(), getP2()) && checkSide(p0, dir, t, getP2(), getP3()) && checkSide(p0, dir, t,
				getP3(),
				getP1())
				|| checkSide(p0, dir, t, getP2(), getP1()) && checkSide(p0, dir, t, getP3(), getP2()) && checkSide(p0, dir, t,
				getP1(),
				getP3());
	}

	private boolean checkSide(Point p0, Vector dir, double t, Point t1, Point t2) {
		Point intersection = Point.findPoint(p0, dir, t);
		Vector v1 = new Vector(p0, t1);
		Vector v2 = new Vector(p0, t2);
		Vector N1 = Vector.crossProduct(v2, v1);
		double d1 = -(Vector.pointMulVector(p0, N1));
		if (Vector.pointMulVector(intersection, N1) + d1 < 0)
			return false;
		return true;
	}

	public Vector getNormal() {
		Vector v1 = new Vector(getP1(), getP2());
		Vector v2 = new Vector(getP1(), getP3());
		Vector normal = Vector.crossProduct(v1, v2);
		normal.normalise();
		return normal;
	}

	private boolean isMoving() {
		return direction != null;
	}

	private Point movePoint(Point p){
		double x = (currentRand*movementSize*direction.x) + p.x;
		double y = (currentRand*movementSize*direction.y) + p.y;
		double z = (currentRand*movementSize*direction.z) + p.z;
		return new Point(x, y, z);
	}

	public Point getP1() {
		if(isMoving()){
			return movePoint(p1);
		}
		return p1;
	}


	public Point getP2() {
		if(isMoving()){
			return movePoint(p2);
		}
		return p2;
	}


	public Point getP3() {
		if(isMoving()){
			return movePoint(p3);
		}
		return p3;
	}

}