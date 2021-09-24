import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class FileHandling {
	private String file_data = new String();

	public FileHandling() {
		try {
			JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
			if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				Scanner myReader = new Scanner(chooser.getSelectedFile());
				while (myReader.hasNextLine()) {
					file_data += myReader.nextLine() + "\n";
				}
				myReader.close();
			} else {
				System.exit(0);
			}
		} catch (FileNotFoundException e) {
			System.out.println("File " + e.getMessage() + " not found!");
		}
	}

	public Board getBoard() {
		String[] parts = file_data.split("\n", 4);
		return new Board(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), parts[3]);
	}
}
