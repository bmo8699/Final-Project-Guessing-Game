public class GuessRunner {

	static Result processGuess(int target, int guess) {
		int hits=0;
		int strikes=0;

		int numbers[] = new int[10];
		for(int i = 0; i < 4; i++) {
			int targetDigit = (int) (target / Math.pow(10,i) % 10);
			int guessDigit = (int) (guess / Math.pow(10,i) % 10);
			if (targetDigit == guessDigit) {
				strikes++;
			} else {
				if (numbers[targetDigit] < 0) hits++;
				if (numbers[guessDigit] > 0) hits++;
				numbers[targetDigit]++;
				numbers[guessDigit]--;
			}
		}
		/* Provide your implementation to process 
		 * the guess by your oponent.
		 * Your code need to properly count the number of strikes and hits
		 * Code to print out the output of the guess is provided below
		 */
		 
		System.out.printf("\t");
		if (strikes==4)	{ // game over
			System.out.printf("4 strikes - Game over\n");
			return new Result(hits, strikes);
		}
		if (hits==0 && strikes==0)
			System.out.printf("Miss\n");
		else if(hits>0 && strikes==0)
			System.out.printf("%d hits\n", hits);
		else if(hits==0 && strikes>0)
			System.out.printf("%d strikes\n", strikes);
		else if(hits>0 && strikes>0)
			System.out.printf("%d strikes and %d hits\n", strikes, hits);
		return new Result(hits, strikes);
	}

	public static void main(String[] args) {
		int guess_cnt = 0;
		/* A dummy value, you need to code here
		 * to get a target number for your oponent
		 * should be a random number between [1000-9999]
		 */
		int target = 9987;
		Result res = new Result();
		System.out.println("Guess\tResponse\n");
		for(int i = 0; i <= 9999; i++) {
			Guess.prob.add(i);
		}
		while(res.getStrikes() < 4) {
			/* take a guess from user provided class
			 * the user provided class must be a Guess.class file
			 * that has implemented a static function called make_guess()
			 */
			int guess = Guess.make_guess(res.getHits(), res.getStrikes());
			System.out.println("" + res.getHits() + " hits "+ res.getStrikes() + " strike");
			System.out.printf("%d\n", guess);
			
			if (guess == -1) {	// user quits
				System.out.printf("you quit: %d\n", target);
				return;
			}
			guess_cnt++;
			
			/* You need to code this method to process a guess
			 * provided by your oponent
			 */
			res = processGuess(target, guess);
		}
		System.out.printf("Target: %d - Number of guesses: %d\n", target, guess_cnt);
	}
}
