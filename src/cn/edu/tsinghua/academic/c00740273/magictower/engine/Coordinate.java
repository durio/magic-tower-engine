package cn.edu.tsinghua.academic.c00740273.magictower.engine;

import java.io.Serializable;

public final class Coordinate implements Serializable {

	private static final long serialVersionUID = 1L;

	private int z, x, y;

	public static Coordinate ZERO = new Coordinate(0, 0, 0);

	public Coordinate(int z, int x, int y) {
		this.z = z;
		this.x = x;
		this.y = y;
	}

	public int getZ() {
		return this.z;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	@Override
	public int hashCode() {
		return this.z ^ this.x ^ this.y;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Coordinate)) {
			return false;
		}
		Coordinate c = (Coordinate) o;
		return c.z == this.z && c.x == this.x && c.y == this.y;
	}

	public int compareCoordinate(Coordinate coord) {
		if (this.x < coord.x && this.y < coord.y && this.z < coord.z) {
			return -2;
		} else if (this.x > coord.x && this.y > coord.y && this.z > coord.z) {
			return 2;
		} else if (this.x < coord.x || this.y < coord.y || this.z < coord.z) {
			return -1;
		} else if (this.x > coord.x || this.y > coord.y || this.z > coord.z) {
			return 1;
		} else {
			return 0;
		}
	}
}
