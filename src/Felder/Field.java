package Felder;

import java.awt.Graphics2D;

public abstract class Field {
	private int field_id_ = 0;
	private static int counter = 0;

	public class Coordinates {
		public int x_;
		public int y_;
	}

	public Coordinates coord_ = new Coordinates();

	private int f_ = 0;
	private int g_ = 0;
	private int h_ = 0;
	private Field[] neighbors = { null, null, null, null };
	private Field previous;
	private boolean fastest;

	abstract public boolean isBlocked();

	abstract public void render(Graphics2D g);

	public Field() {
		counter++;
		field_id_ = counter;

	}

	public Field(int x, int y) {
		coord_.x_ = x;
		coord_.y_ = y;
		field_id_ = counter;
	}

	public void setNeighbor_right(Field neighbor_right) {
		neighbors[1] = neighbor_right;
	}

	public void setNeighbor_left(Field neighbor_left) {
		neighbors[3] = neighbor_left;
	}

	public void setNeighbor_top(Field neighbor_top) {
		neighbors[0] = neighbor_top;
	}

	public void setNeighbor_bottom(Field neighbor_bottom) {
		neighbors[2] = neighbor_bottom;
	}

	public Field getNeighbor(int i) {
		return neighbors[i];
	}

	public int getFieldId() {
		return field_id_;
	}

	public Coordinates getPosition() {
		return coord_;
	}

	public int getF() {
		return f_;
	}

	public void setF(int f_) {
		this.f_ = f_;
	}

	public int getG() {
		return g_;
	}

	public void setG(int g_) {
		this.g_ = g_;
	}

	public int getH() {
		return h_;
	}

	public void setH(int h_) {
		this.h_ = h_;
	}

	public Field getPrevious() {
		return previous;
	}

	public void setPrevious(Field previous) {
		this.previous = previous;
	}

	public boolean isFastest() {
		return fastest;
	}

	public void setFastest(boolean isFastest) {
		this.fastest = isFastest;
	}
}
