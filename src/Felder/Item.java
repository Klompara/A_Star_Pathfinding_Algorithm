package Felder;

public final class Item extends Field {

	public Item() {
	}

	public Item(int x, int y) {
		coord_.x_ = x;
		coord_.y_ = y;
	}

	@Override
	public boolean isBlocked() {
		return false;
	}

}
