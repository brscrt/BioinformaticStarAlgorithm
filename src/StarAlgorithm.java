public class StarAlgorithm extends Algorithms {

	private GlobalAlignment globalAlignment = new GlobalAlignment();
	private int sequenceSize = sequences.size();
	private int[][] scoreTable = new int[sequenceSize][sequenceSize - 1];
	private String[][][] alignmentTable = new String[sequenceSize][sequenceSize - 1][2];
	private int[] scores = new int[sequenceSize];
	private int Star;

	protected void findStar() {
		generateScoreTable();
		calculateScores();
		Star = findMaxScoreRow();
		System.out.println("\nStar= S" + (Star)+"= "+sequences.get(Star));

	}

	protected void generateStarAlignment() {
		String alignedStar = findLongestStar();
		System.out.println("alignedStar=   " + alignedStar+"\n");

		int numberGap;
		String buffer;

		for (int i = 0; i < sequenceSize - 1; i++) {

			numberGap = 0;
			buffer = "";

			if (alignedStar != alignmentTable[Star][i][0]) {
				for (int j = 0; j < alignedStar.length(); j++) {
					if (alignmentTable[Star][i][0].length() <= j - numberGap) {
						buffer += "-";
					} else if (alignmentTable[Star][i][0].charAt(j - numberGap) != alignedStar
							.charAt(j)) {
						buffer += "-";
						numberGap++;
					} else {
						buffer += alignmentTable[Star][i][1].charAt(j
								- numberGap);
					}

				}

				alignmentTable[Star][i][1] = buffer;
			}

		}

		boolean afterStar = false;

		for (int i = 0; i < sequenceSize - 1; i++) {
			if (i == Star) {
				System.out.println("(*)S" + Star + "= " + alignedStar);
				afterStar = true;
			}
			if (afterStar)
				System.out.println("   S" + (i + 1) + "= "
						+ alignmentTable[Star][i][1]);
			else
				System.out.println("   S" + i + "= "
						+ alignmentTable[Star][i][1]);
		}
	}

	private void generateScoreTable() {

		for (int i = 0; i < sequences.size(); i++) {
			for (int j = i; j < sequences.size() - 1; j++) {
				scoreTable[i][j] = globalAlignment.findGlobalScore(
						sequences.get(i), sequences.get(j + 1));

				scoreTable[j + 1][i] = scoreTable[i][j];

				alignmentTable[j + 1][i][1] = alignmentTable[i][j][0] = globalAlignment.aligned[0];
				alignmentTable[j + 1][i][0] = alignmentTable[i][j][1] = globalAlignment.aligned[1];

				System.out.println("S"+(i)+"-S"+(j+1)+" score= "+scoreTable[i][j]+"\n");
			}
		}

	}

	private void calculateScores() {
		int scoreBuffer = 0;
		for (int i = 0; i < sequenceSize; i++) {
			scoreBuffer = 0;
			for (int j = 0; j < sequenceSize - 1; j++) {
				scoreBuffer += scoreTable[i][j];				
			}
			scores[i] = scoreBuffer;
			System.out.println("total alignment score by S"+i+"= "+scores[i]);
		}
	}

	private int findMaxScoreRow() {
		int max = scores[0];
		int id = 0;
		for (int i = 1; i < scores.length; i++) {
			if (max < scores[i]) {
				max = scores[i];
				id = i;
			}
		}
		return id;
	}

	private String findLongestStar() {

		String max = alignmentTable[Star][0][0];

		for (int i = 0; i < sequenceSize - 1; i++) {
			if (max.length() < alignmentTable[Star][i][0].length())
				max = alignmentTable[Star][i][0];
		}
		int x, y;
		String buffer;
		
		for (int i = 0; i < sequenceSize - 1; i++) {
			x = y = 0;
			buffer = "";
			//System.out.println(alignmentTable[Star][i][0]);

			if (alignmentTable[Star][i][0] != max) {
				while (x < max.length()) {
					
					if (y == alignmentTable[Star][i][0].length()) {
						buffer += "-";
						x++;
					} else if ((alignmentTable[Star][i][0].charAt(y) == '-')
							&& (max.charAt(x) == '-')) {
						buffer += "-";
						x++;
						y++;
					} else if (alignmentTable[Star][i][0].charAt(y) == '-') {
						buffer += "-";
						y++;
					} else if (max.charAt(x) == '-') {
						buffer += "-";
						x++;
					} else {
						buffer += max.charAt(x);
						x++;
						y++;
					}
				}
				max = buffer;
			}

		}
		return max;
	}

}
