import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.util.*;

class WordleJunitTests{
	private Dictionary dict;
	private Feedback fb;
	private WordleGame game;
	private String secretWord;
	
	@BeforeEach
	void setup() {
		game = new WordleGame();
		secretWord = game.getSecretWord();
		
	}
	//tests to see if a correct guess will be correct
	@Test
	void testCorrectWord() {
		fb = game.makeGuess(secretWord);
		assertTrue(fb.isCorrect());
	}
	//tests that an incorrect guess will not be correct
	@Test
	void testIncorrectWord() {
		if ((game.getSecretWord()).equals("delta")){
			fb = game.makeGuess("flint");
			assertFalse(fb.isCorrect());
		}else {
			fb = game.makeGuess("delta");
			assertFalse(fb.isCorrect());
		}
	
		
	}
	//tests to see if the letter E will be marked as yellow 
	//void  testPartialCorrectPattern() {
		 //char[] charList = secretWord.toCharArray();
		// charList[0] = charList
		//fb = game.makeGuess("cider");
		//assertEquals(fb.getPattern(),"BBBYB");
	//}
	//checks that case sensitive guesses are not a factor
	@Test
	void testCaseSensitveWord() {
		assertDoesNotThrow(()->game.makeGuess("ApPlE"));
	}
	//tests that a guess with more or less than 5 letters will return correct error message
	@Test
	void testGuessNot5LettersLong() {
		Exception e = assertThrows(IllegalArgumentException.class, () -> game.makeGuess("Banana"));
		assertTrue(e.getMessage().contains("Guess must be exactly 5 letters."));
		e = assertThrows(IllegalArgumentException.class, () -> game.makeGuess("Pan"));
		assertTrue(e.getMessage().contains("Guess must be exactly 5 letters."));
	}
	//tests that a guess made that is not in the dict will return correct error message
	@Test
	void testGuessNotInDict() {
		Exception e = assertThrows(IllegalArgumentException.class, () -> game.makeGuess("Hello"));
		assertTrue(e.getMessage().contains("Word not found in dictionary."));
	}
	//tests that after a correct guess is made another guess will receiver the error code "Game already ended"
	@Test
	void testGameAlreadyOver() {
		fb = game.makeGuess(secretWord);
		Exception e = assertThrows(IllegalStateException.class, () -> game.makeGuess("Flint"));
		assertTrue(e.getMessage().contains("Game already ended."));
	}
	//Check if game is over after correct guess 
	@Test
	void testGameIsOverAfterCorrect() {
		fb = game.makeGuess(secretWord);
		assertTrue(game.isGameOver());
	}
	
	//Check if game is over after too many guesses
	@Test
	void testGameIsOverAfterTooManyGuesses() {
		if ((game.getSecretWord()).equals("delta")){
			fb = game.makeGuess("flint");
			fb = game.makeGuess("flint");
			fb = game.makeGuess("flint");
			fb = game.makeGuess("flint");
			fb = game.makeGuess("flint");
			fb = game.makeGuess("flint");
			assertTrue(game.isGameOver());
		}else {
			fb = game.makeGuess("delta");
			fb = game.makeGuess("delta");
			fb = game.makeGuess("delta");
			fb = game.makeGuess("delta");
			fb = game.makeGuess("delta");
			fb = game.makeGuess("delta");
			assertTrue(game.isGameOver());
		}
		
		
			
	}
	
	//Check if game is not over after incorrect guess 
	@Test
	void testGameIsNotOverAfterIncorrect() {
		fb = game.makeGuess("flint");
		assertFalse(game.isGameOver());
	}
	//Check if game is not over after less that 6 guesses
	@Test
	void testGameIsOverAfterLessThan6Guesses() {
		fb = game.makeGuess("flint");
		fb = game.makeGuess("flint");
		fb = game.makeGuess("flint");
		fb = game.makeGuess("flint");
		fb = game.makeGuess("flint");
		assertFalse(game.isGameOver());
				
	}
	//Will a null string be handled properly
	@Test
	void testNullGuess() {
		Exception e = assertThrows(IllegalArgumentException.class, () -> game.makeGuess(""));
		assertTrue(e.getMessage().contains("Guess must be exactly 5 letters."));
	}
	
	//checks to make sure random word is not from an empty list
	@Test
	void testDictWordNotNull() {
		Dictionary dict = new Dictionary();
		String word = dict.getRandomWord();
		assertNotEquals(word,null);
		
	}
	
	//checks to make sure word is picked randomly
	@Test
	void testRandomnessOfSecretWord() {
		Set <String> RandomWords = new HashSet<>();
		for (int i = 0; i<50;i++) {
			RandomWords.add(game.getSecretWord());
			game = new WordleGame();
		}
		assertTrue(RandomWords.size() > 5);
		
		
	}
	
	
		
	
	
	
}
