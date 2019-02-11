public class Main {

	public static void main(String[] args) {
		FileOperations fileOperations = new FileOperations();
		fileOperations.fileRead("star.txt");
		// GlobalAlignment gAlignment=new GlobalAlignment();
		// gAlignment.findGlobalScore("ABFaE", "ABCE");
		StarAlgorithm starAlgorithm = new StarAlgorithm();
		starAlgorithm.findStar();
		starAlgorithm.generateStarAlignment();
	}

}
