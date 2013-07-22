package net.liamdouglas.speedflashcards;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class CardLogic {
	
	File cardsFile = null;
	String cardsFileName = "cards.txt";
	ArrayList<Card> cards = new ArrayList<Card>();
	int nextCard = 0;
	
	public CardLogic() {
		
	}
	
	/**
	 * Prepare the file containing cards for use
	 * @return boolean indicating status of preparation
	 */
	public boolean prepareCardsFile() {
		cardsFile = new File(cardsFileName);
		try {
			cardsFile.createNewFile();
			return true;
		} catch (IOException e) {
			System.out.println("An error occurred preparing cards.txt.");
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Load the cards stored in the cards file into memory
	 * @return boolean indicating the success of the load
	 */
	public boolean loadCardsFile() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(cardsFile));
			String line;
			while ((line = br.readLine()) != null) {
				String[] cardStrings = line.split(",");
				cards.add(new Card(cardStrings[0],cardStrings[1]));
			}
			
			br.close();
			return true;
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred loading cards.txt.");
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			System.out.println("An error occurred reading cards.txt.");
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Shuffle the cards into a new order
	 */
	private void shuffleCards() {
		long seed = System.nanoTime();
		Collections.shuffle(cards, new Random(seed));
	}
	
	/**
	 * Returns the next card and prepares the next one
	 * @return the next Card object in the array
	 */
	public Card getNextCard() {
		Card c = cards.get(nextCard);
		System.out.println("Array size: " + cards.size());
		if (nextCard == (cards.size()-1)) {
			nextCard = 0;
			shuffleCards();
		} else {
			nextCard++;
		}
		return c;
	}

}
