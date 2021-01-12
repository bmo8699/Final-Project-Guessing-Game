import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

/*
 * You need to implement an algorithm to make guesses
 * for a 4-digits number in the method make_guess below
 * that means the guess must be a number between [1000-9999]
 * PLEASE DO NOT CHANGE THE NAME OF THE CLASS AND THE METHOD
 */
public class Guess {
	static ArrayList<Integer> prob = new ArrayList<Integer>(); //probable guesses, initialized in GuessRunner class
	static int guessCount = 1; //number of guess
	static boolean isHitOrStrike = false;
	static ArrayList<Integer> firstThreeGuesses = new ArrayList<Integer>(Arrays.asList(1230,4567,7890));  //First three guesses to avoid duplicate digits (e.g. 2222) when random and narrow the pool
	static int guess;
	public static int make_guess(int hits, int strikes) {
		// just a dummy guess
		/* IMPLEMENT YOUR GUESS STRATEGY HERE*/
		if (guessCount != 1) {
			for (int i = 0; i < prob.size(); i++) {
				if (!isPossible(strikes, hits, prob.get(i), guess)) {
					prob.remove(i);
					i--;
				}
			}
		}
		if (prob.size() > 1) {
			guess = prob.get((int)(Math.random() * prob.size())); //random from possible answers
			if (guessCount <= 1 && (strikes > 0 || hits > 1)) {
				isHitOrStrike = true;
			}
			if (guessCount <= 1 && !isHitOrStrike) { //First three counts without hit or strike
				int rand = new Random().nextInt(firstThreeGuesses.size());
				guess = firstThreeGuesses.get(rand);
				firstThreeGuesses.remove(rand);
			}
			guessCount++;
		} else if (!prob.isEmpty()){
			guess = prob.get(0);
		}

		return guess;
	}

	//Check if probable answer contains hit or strike digits from guess
	private static boolean isPossible(int strikes,int hits,int answer,int guess) {
		int tempStrikes = 0, tempHits = 0;
		int numbers[] = new int[10];
		for(int i = 0; i < 4; i++) {
			int answerDigit = (int) (answer / Math.pow(10, i) % 10); //Get prob answer digit
			int guessDigit = (int) (guess / Math.pow(10, i) % 10); //Get guess digit
			if (answerDigit == guessDigit) {
				tempStrikes++;
			} else {
				if (numbers[answerDigit] < 0) tempHits++;
				if (numbers[guessDigit] > 0) tempHits++;
				numbers[answerDigit]++;
				numbers[guessDigit]--;
			}
		}
		return (tempStrikes == strikes) && (tempHits == hits);
	}
}