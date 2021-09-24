import java.util.ArrayList;

import Felder.Field;
import Felder.Item;
import Felder.Road;

public class Board {
	private int height_;
	private int width_;
	private int number_items_;

	private String map_data_;

	private ArrayList<Item> items_ = new ArrayList<Item>();

	private ArrayList<ArrayList<Field>> board_ = new ArrayList<ArrayList<Field>>();

	private Item start_;
	private Item goal_;

	public Board(int height, int width, int number_items, String map_data) {
		this.height_ = height;
		this.width_ = width;
		this.number_items_ = number_items;
		this.map_data_ = map_data;
		buildMap();
	}

	private void buildMap() {
		map_data_ = map_data_.replace("\n", "");
		ArrayList<Field> fields_ = new ArrayList<>();
		for (int y = 0; y < height_; y++) {
			for (int x = 0; x < width_; x++) {
				int index = y * width_ + x;
				Field temp_field;

				if (Character.isLetter(map_data_.charAt(index))) {
					temp_field = new Item(x, y);
					items_.add((Item) temp_field);
				} else if (map_data_.charAt(index) == '#') {
					temp_field = new Road(false, x, y);
				} else if (map_data_.charAt(index) == '%') {
					temp_field = new Road(true, x, y);
				} else {
					temp_field = null;
				}

				fields_.add(temp_field);
			}
		}

		for (int y = 0; y < height_; y++) {
			board_.add(new ArrayList<Field>());
			for (int x = 0; x < width_; x++) {
				board_.get(y).add(fields_.get(y * width_ + x));
			}
		}

		this.start_ = items_.get(0);
		this.goal_ = items_.get(0);
	}

	public int getHeight() {
		return height_;
	}

	public void setHeight(int height_) {
		this.height_ = height_;
	}

	public int getWidth() {
		return width_;
	}

	public void setWidth(int width_) {
		this.width_ = width_;
	}

	public int getNumber_items() {
		return number_items_;
	}

	public void setNumber_items(int number_items_) {
		this.number_items_ = number_items_;
	}

	public String getMap_data() {
		return map_data_;
	}

	public void setMap_data(String map_data_) {
		this.map_data_ = map_data_;
	}

	public ArrayList<ArrayList<Field>> getBoard() {
		return board_;
	}

	public void setBoard(ArrayList<ArrayList<Field>> board_) {
		this.board_ = board_;
	}

	public Item getStart() {
		return start_;
	}

	public void setStart(Item start_) {
		this.start_ = start_;
	}

	public Item getGoal() {
		return goal_;
	}

	public void setGoal(Item goal_) {
		this.goal_ = goal_;
	}

}
