public class Main {

	public static void main(String[] args) {
		FileHandling file_handling = new FileHandling();
		Board board = file_handling.getBoard();
		AStar pathfinding = new AStar(board);
		Renderer renderer = new Renderer(board);
		Thread tLogic = new Thread(pathfinding);
		Thread tRenderer = new Thread(renderer);
		tRenderer.start();
		tLogic.start();
	}

}
