import java.util.*;
public class Dictionary {
private List<String> words;
	
	public Dictionary() {
		words = new ArrayList<>();
		
		/**
		 * Some uppercase, some lowercase
		 */
		words.add("apple");
		words.add("brave");
		words.add("cider");
		words.add("delta");
		words.add("eagle");
		words.add("flint");
	}
	
	/**
	 * Returns random 5 letter word
	 * @bug not random, returns first always
	 */
	public String getRandomWord() {
		if (words.isEmpty()) {
			return null;
		} else {
			Random randomGenerator = new Random();
			int randomIndex = randomGenerator.nextInt(words.size());
			return (words.get(randomIndex));
			//return words.get(0); //picks random value in array list
		}
	}
	
	/**
	 * Checks for word validity
	 * @bug case sensitive
	 */
	public boolean isValidWord(String guess) {
		return words.contains(guess);
	}
	
}
