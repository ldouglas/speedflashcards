package net.liamdouglas.speedflashcards;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class CardsLauncher {
	
	File cardsFile = null;
	String cardsFileName = "cards.txt";
	ArrayList<Card> cards = new ArrayList<Card>();
	
	private CardsLauncher() {
		
	}
	
	/**
	 * Check the existence of the file containing cards
	 * @return boolean indicating whether the file was successfully loaded
	 */
	private boolean prepareCardsFile() {
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
	
	private boolean loadCardsFile() {
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

	public static void main(String[] args) {
		CardsLauncher cl = new CardsLauncher();
		if (!cl.prepareCardsFile()) {
			//leave the program? or don't start
		}
		cl.loadCardsFile();
	}

}
