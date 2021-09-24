package Felder;

import java.awt.Color;
import java.awt.Graphics2D;

public final class Road extends Field {
	private boolean blocked_;

	public Road(boolean blocked, int x, int y) {
		blocked_ = blocked;
		coord_.x_ = x;
		coord_.y_ = y;
	}

	public boolean isBlocked() {
		return blocked_;
	}

	public void render(Graphics2D g) {
		if (blocked_) {
			g.setColor(Color.black);
		} else if (this.isFastest()) {
			g.setColor(Color.red);
		} else {
			if (this.getF() > 0) {
				g.setColor(Color.green);
			} else {
				g.setColor(Color.white);
			}
		}
		g.fillRect(coord_.x_, coord_.y_, 1, 1);
	}

}
