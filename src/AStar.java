import java.util.ArrayList;

import Felder.Field;

public class AStar implements Runnable {

	private Board board;
	private ArrayList<Field> open_set_ = new ArrayList<Field>();

	public AStar(Board board) {
		this.board = board;
		open_set_.add(board.getStart());
	}

	private Field getLowestFScore() {
		Field smallest_score = open_set_.get(0);
		for (int i = open_set_.size() - 1; i >= 0; i--) {
			if (open_set_.get(i).getF() < smallest_score.getF()) {
				smallest_score = open_set_.get(i);
			}
		}
		return smallest_score;
	}

	private void addNeighbors(Field current) {
		int x = current.getPosition().x_;
		int y = current.getPosition().y_;

		if (x < board.getWidth() - 1) {
			current.setNeighbor_right(board.getBoard().get(y).get(x + 1));
		}

		if (x > 0) {
			current.setNeighbor_left(board.getBoard().get(y).get(x - 1));
		}

		if (y < board.getHeight() - 1) {
			current.setNeighbor_bottom(board.getBoard().get(y + 1).get(x));
		}
		if (y > 0) {
			current.setNeighbor_top(board.getBoard().get(y - 1).get(x));
		}
	}

	private double calculateHeursitic(Field a, Field b) {
		int x_start = a.getPosition().x_;
		int y_start = a.getPosition().y_;

		int x_end = b.getPosition().x_;
		int y_end = b.getPosition().y_;

		return Math.sqrt(Math.pow(x_end - x_start, 2) + Math.pow(y_end - y_start, 2));
	}

	@Override
	public void run() {
		long startTime = System.currentTimeMillis();
		ArrayList<Field> closed_set_ = new ArrayList<>();
		board.getStart().setF((int) calculateHeursitic(board.getStart(), board.getGoal()));
		board.getStart().setG(0);
		boolean finished = false, found = false;
		while (open_set_.size() != 0 && !finished) {
			Field current = getLowestFScore();
			if (current.getFieldId() == board.getGoal().getFieldId()) {
				finished = true;
				found = true;
			}

			open_set_.remove(current);
			closed_set_.add(current);
			addNeighbors(current);

			for (int i = 0; i < 4; i++) {
				Field neighbor = current.getNeighbor(i);
				if (neighbor != null) {
					if (neighbor.getFieldId() == board.getGoal().getFieldId()) {
						reconstructPath(current);
						finished = true;
						found = true;
					}
					if (!neighbor.isBlocked() && !closed_set_.contains(neighbor)) {
						int temp_g = current.getG() + 1;
						if (open_set_.contains(neighbor)) {
							if (temp_g < neighbor.getG()) {
								neighbor.setG(temp_g);
							}
						} else {
							neighbor.setG(temp_g);
							open_set_.add(neighbor);
						}
						neighbor.setH((int) calculateHeursitic(neighbor, board.getGoal()));
						neighbor.setF(neighbor.getH() + neighbor.getG());
						neighbor.setPrevious(current);
					}
				}
			}
		}
		long duration = (System.currentTimeMillis() - startTime);
		System.out.println("PATH " + (found ? "" : "NOT ") + "FOUND AFTER " + duration + "ms");
	}

	public void reconstructPath(Field current) {
		Field temp = current;
		while (temp.getPrevious() != null) {
			temp.setFastest(true);
			temp = temp.getPrevious();
		}
	}
}
