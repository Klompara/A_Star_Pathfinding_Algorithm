package Felder;

import java.awt.Color;
import java.awt.Graphics2D;

public final class Item extends Field {

	public Item() {
	}

	public Item(int x, int y) {
		coord_.x_ = x;
		coord_.y_ = y;
	}

	public boolean isBlocked() {
		return false;
	}

	public void render(Graphics2D g) {
		g.setColor(Color.blue);
		g.fillRect(coord_.x_, coord_.y_, 1, 1);
	}

}
