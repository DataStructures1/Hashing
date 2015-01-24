
public class HashObject {

	public static HashDisplay hashDisplay;

	private int capacity;
	private int keyCount;
	private int collisionCount;
	private int quadIncrement;

	private String[] hashTable;
	private EasyReader inFile;
	private int APIcount = 0;
	private int hashmethod;

	public HashObject(int n, int rehashChoice) {
		System.out.println(rehashChoice);
		capacity = n;
		quadIncrement = -1;
		hashTable = new String[capacity];
		hashmethod = rehashChoice;
		// 0 means adjacency rehasher
		// 1 means linear rehasher
		// 2 means quadratic rehasher
		// 3 means custom rehasher
	}

	public void loadTable(String fileName) {

		inFile = new EasyReader(fileName);
		keyCount = 0;

		if (inFile.bad()) {

			System.err.println("Can't open " + fileName);
			System.exit(1);
		}

		int hashIndex = 0;

		// read a word
		String aWord = inFile.readLine(); // file has one word per line

		while (!inFile.eof()) {
			APIcount = 0;
			keyCount++;
			aWord = aWord.trim();
			// System.out.print( aWord + "  ");
			quadIncrement = -1;
			hashIndex = hasher(aWord);
			hashDisplay.paintProbe(hashIndex);
			System.out.print(hashIndex + ", ");
			quadIncrement = 1;
			if (hashTable[hashIndex] == null) {
				hashTable[hashIndex] = aWord;

			} else {
				do {
					collisionCount++;
					hashIndex = hashChooser(hashIndex);
					hashDisplay.paintProbe(hashIndex);
					System.out.print(hashIndex + ", ");
				} while (hashTable[hashIndex] != null);

				hashTable[hashIndex] = aWord;
				System.out.println(": " + aWord);
			}

			// read next word
			aWord = inFile.readLine();
		}
		inFile.close();
	}

	private int hasher(String s) {
		int sum = 0;

		for (int i = 0; i < s.length(); i++)
			sum = Math.abs(26 * sum + (int) s.charAt(i)) % capacity;

		return sum;
	}

	private int hashChooser(int oldHashValue) {
		switch (hashmethod) {
		case 0: {
			return adjacencyRehasher(oldHashValue);
		}
		case 1: {
			return linRehasher(oldHashValue);
		}
		case 2: {
			return quadRehasher(oldHashValue);
		}
		case 3: {
			return customRehasherDK(oldHashValue);
		}
		case 4: {
			return customRehasherDA(oldHashValue);
		}
		case 5: {
			return customRehasherLCU(oldHashValue);
		}
		case 6: {
			return customRehasherAK(oldHashValue);
		}
		case 7: {
			return customRehasherAS(oldHashValue);
		}
		case 8: {
			return customRehasherRE(oldHashValue);
		}
		}
		return -1;
	}

	/******************************************************************
	 * adjacency rehasher implements newIndex = 1 + oldIndex
	 * 
	 ******************************************************************/
	private int adjacencyRehasher(int oldIndex) {
		return (oldIndex + 1) % capacity;
	}

	/******************************************************************
	 * linear rehasher implements newIndex = A * oldIndex + B for arbitrary
	 * constants A and B relatively prime to "capacity"
	 * 
	 ******************************************************************/
	private int linRehasher(int oldIndex) {
		return (13 * oldIndex + 113) % capacity;
	}

	/******************************************************************
	 * quadratic rehasher implements newIndex = oldIndex + increment where each
	 * increment is two more than the last increment
	 * 
	 ******************************************************************/
	private int quadRehasher(int oldIndex) {
		quadIncrement += 2;
		return (oldIndex + quadIncrement) % capacity;
	}
	
	/******************************************************************
	 * custom rehasher implements ? ? ? ? ? ? ? your design
	 * 
	 ******************************************************************/
	private int customRehasherDK(int oldIndex) {
		return 0;
	}
	/******************************************************************
	 * custom rehasher implements ? ? ? ? ? ? ? your design
	 * 
	 ******************************************************************/
	private int customRehasherDA(int oldIndex) {
		return 0;
	}
	/******************************************************************
	 * custom rehasher implements ? ? ? ? ? ? ? your design
	 * 
	 ******************************************************************/
	private int customRehasherLCU(int oldIndex) {
		return 0;
	}
	/******************************************************************
	 * custom rehasher implements ? ? ? ? ? ? ? your design
	 * 
	 ******************************************************************/
	private int customRehasherAK(int oldIndex) {
		return 0;
	}
	/******************************************************************
	 * custom rehasher implements ? ? ? ? ? ? ? your design
	 * 
	 ******************************************************************/
	private int customRehasherAS(int oldIndex) {
		return 0;
	}
	/******************************************************************
	 * custom rehasher implements ? ? ? ? ? ? ? your design
	 * 
	 ******************************************************************/
	private int customRehasherRE(int oldIndex) {
		return 0;
	}


	public int find(String aWord) {
		// returns index of hashTable
		// or -1 if not found.
		nameStrategy();
		System.out.println(aWord);
		quadIncrement = 1;

		int hashIndex = hasher(aWord);
		System.out.print(hashIndex + ", ");
		while (hashTable[hashIndex] != null && !hashTable[hashIndex].equals(aWord)) {
			hashIndex = hashChooser(hashIndex);
			System.out.print(hashIndex + ", ");
		}
		if (hashTable[hashIndex] == null)
			return -1;
		else
			return hashIndex;
	}

	public int getCapacity() {
		return capacity;
	}

	public double getLoadFactor() {
		return (double) keyCount / capacity;
	}

	public double getCollisionRate() {
		return (double) collisionCount / keyCount;
	}

	public String toString() {

		String str = "";
		for (int i = 0; i < hashTable.length; i++)
			str += hashTable[i] + "\n";

		str += "word count = " + keyCount + "\n";
		str += "load factor = " + getLoadFactor() + "\n";
		str += "collision rate = " + getCollisionRate() + "\n";
		return str;
	}

	private void nameStrategy() {
		switch (hashmethod) {
		case 0: {
			System.out.println("using adjacency.");
			break;
		}
		case 1: {
			System.out.println("using linear.");
			break;
		}
		case 2: {
			System.out.println("using quadratic.");
			break;
		}
		case 3: {
			System.out.println("using custom.");
			break;
		}
		}
	}

}
