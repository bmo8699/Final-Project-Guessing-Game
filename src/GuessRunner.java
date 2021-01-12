
import java.util.ArrayList;
import java.util.Arrays;

public class GuessRunner {

	static Result processGuess(int target, int guess) {
		char des[] = Integer.toString(target).toCharArray();
		char src[] = Integer.toString(guess).toCharArray();
		int hits=0;
		int strikes=0;

		// process strikes
		for (int i=0; i<4; i++) {
			if (src[i] == des[i]) {
				strikes++;
				des[i] = 'a';
				src[i] = 'a';
			}
		}
		// process hits
		for (int i=0; i<4; i++) {
			for (int j=0; j<4; j++) {
				if (src[i]!='a') {
					if (src[i]==des[j]) {
						hits++;
						des[j] = 'a';
						break;
					}
				}
			}
		}
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
		/* A dummy value, you need to code here
		 * to get a target number for your oponent
		 * should be a random number between [1000-9999]
		 */
		ArrayList<Integer> guess_cnt_list = new ArrayList<Integer>();
		for (int j = 1000; j <= 9999; j++) {
			int guess_cnt = 0;
			int target = j;
			Result res = new Result();
			System.out.println("Guess\tResponse\n");
			Guess.prob.clear();
			Guess.guessCount = 1;
			Guess.isHitOrStrike = false;
			Guess.firstThreeGuesses = new ArrayList<Integer>(Arrays.asList(1230, 4567, 7890));
			for (int i = 1000; i <= 9999; i++) {
				Guess.prob.add(i);
			}
			while (res.getStrikes() < 4) {
				/* take a guess from user provided class
				 * the user provided class must be a Guess.class file
				 * that has implemented a static function called make_guess()
				 */
				System.out.println("Target: " + target);
				int guess = Guess.make_guess(res.getHits(), res.getStrikes());
				System.out.printf("%d\n", guess);

				if (guess == -1) {    // user quits
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
			guess_cnt_list.add(guess_cnt);
		}
		int sum = 0;
		for (Integer num:guess_cnt_list) {
			sum += num;
		}
		System.out.println((double) sum/guess_cnt_list.size());
	}
}
