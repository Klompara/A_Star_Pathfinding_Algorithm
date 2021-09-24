package Felder;

public final class Road extends Field {
	private boolean blocked_;

	public Road(boolean blocked, int x, int y) {
		blocked_ = blocked;
		coord_.x_ = x;
		coord_.y_ = y;
	}

	@Override
	public boolean isBlocked() {
		return blocked_;
	}

}
