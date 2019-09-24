package polys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class Vertex {
	point position;
	point velocity = new point(0, 0);
	List<rod> rods = new ArrayList<rod>();

	public Vertex(point p) {
		position = p;
	}

	public void updatevel(Vertex[] newmap) {
		for (rod a : rods) {
			point dif = point.add(position.mult(-1), newmap[a.to].position);
			velocity = point.add(velocity, dif.mult(0.001*(dif.dist()-a.length)));
		}

	}

	public void updatepos() {
		position =point.add(position, velocity.mult(0.00001));
	}

	public void addrod(rod e) {
		rods.add(e);
	}

	public static point invertedlog(point p) {
		double dist = Math.sqrt(p.x * p.x + p.y * p.y);
		point nolength = p.mult(1 / dist);
		double mult = -Math.log(dist);
		point result = nolength.mult(mult);
		return result;
	}

}
