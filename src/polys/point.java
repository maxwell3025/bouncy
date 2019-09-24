package polys;

public class point {

double x;
double y;
	public point(double x,double y) {
		this.x=x;
		this.y=y;
	}
	public static point add(point a,point b){
		return new point(a.x+b.x,a.y+b.y);
	}
	public point mult(double scale){
		return new point(x*scale,y*scale);
	}
	public double dist(){
		return(Math.sqrt(x*x+y*y));
	}

}
