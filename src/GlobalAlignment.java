import java.util.HashMap;

public class GlobalAlignment extends Algorithms {

	HashMap<String, Integer> cells = new HashMap<String, Integer>();

	String[][] rotations;
	String cellRotation = null;

	protected String[] aligned = new String[2];

	protected int findGlobalScore(String firstSequence, String secondSequence) {

		int value;

		firstSequence = "-" + firstSequence;
		secondSequence = "-" + secondSequence;

		rotations = new String[secondSequence.length()][firstSequence.length()];

		if (!cells.isEmpty())
			cells.clear();

		for (int x = -1; x < secondSequence.length(); x++) {
			if (x != -1)
				System.out.print(secondSequence.charAt(x) + "\t");
			for (int y = 0; y < firstSequence.length(); y++) {

				if (x == -1) {
					System.out.print("\t" + firstSequence.charAt(y));
					continue;
				}
				if (x == 0 && y == 0) {
					value = 0;

				} else if (y == 0) {
					value = cells.get(pointToKey(x - 1, y)) + gap;
					rotations[x][y] = "up";

				} else if (x == 0) {
					value = cells.get(pointToKey(x, y - 1)) + gap;
					rotations[x][y] = "left";

				} else {
					value = findCellBigScoreAndRotation(
							x,
							y,
							checkMatching(firstSequence.charAt(y),
									secondSequence.charAt(x)));
					rotations[x][y] = cellRotation;
				}
				cells.put(pointToKey(x, y), value);

				System.out.print(cells.get(pointToKey(x, y)));

				System.out.print(rotations[x][y] + "\t");
			}
			System.out.println();

		}
		
		findGlobalAlignment(firstSequence, secondSequence);

		return cells.get(pointToKey(secondSequence.length() - 1,
				firstSequence.length() - 1));
	}

	protected void findGlobalAlignment(String firstSequence,
			String secondSequence) {

		aligned[0] = "";
		aligned[1] = "";

		int buyuk = firstSequence.length();
		if (buyuk < secondSequence.length())
			buyuk = secondSequence.length();
		int x = secondSequence.length() - 1, y = firstSequence.length() - 1;

		for (int i = buyuk - 1; i > 0; i--) {

			if (rotations[x][y] == "crss") {
				aligned[0] = firstSequence.charAt(y) + aligned[0];
				aligned[1] = secondSequence.charAt(x) + aligned[1];

				x--;
				y--;
			} else if (rotations[x][y] == "left") {
				aligned[0] = firstSequence.charAt(y) + aligned[0];
				aligned[1] = "-" + aligned[1];

				y--;
			} else if (rotations[x][y] == "up") {
				aligned[0] = "-" + aligned[0];
				aligned[1] = secondSequence.charAt(x) + aligned[1];

				x--;
			}
		}

		System.out.println(aligned[0]);
		System.out.println(aligned[1]);

	}

	private String pointToKey(int x, int y) {
		String key = null;
		key = Integer.toString(x) + "," + Integer.toString(y);
		return key;
	}

	private int findCellBigScoreAndRotation(int x, int y, boolean matching) {
		int left = cells.get(pointToKey(x, y - 1)) + gap;
		int up = cells.get(pointToKey(x - 1, y)) + gap;
		int cross = cells.get(pointToKey(x - 1, y - 1));

		if (matching)
			cross += match;
		else
			cross += mismatch;

		int big = left;
		cellRotation = "left";
		if (big < up) {
			big = up;
			cellRotation = "up";
		}

		if (big < cross) {
			big = cross;
			cellRotation = "crss";
		}

		return big;
	}

	private boolean checkMatching(char x, char y) {
		boolean matching = false;
		if (x == y)
			matching = true;
		return matching;
	}

}
