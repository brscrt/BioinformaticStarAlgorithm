import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileOperations {

	protected void fileRead(String fileName) {
		String[] scores;
		String separator = ",";

		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			scores = reader.readLine().split(separator);

			Algorithms.match = Integer.parseInt(scores[0].replaceAll(
					"[^-?0-9]+", ""));
			Algorithms.mismatch = Integer.parseInt(scores[1].replaceAll(
					"[^-?0-9]+", ""));
			Algorithms.gap = Integer.parseInt(scores[2].replaceAll("[^-?0-9]+",
					""));

			String buffer = reader.readLine();
			while (buffer != null) {
				Algorithms.sequences.add(buffer);
				buffer = reader.readLine();
			}

			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
