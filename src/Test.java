import java.util.HashMap;

public class Test {
    public static void main (String args[]) {
        HashMap<Integer, Integer> digitFreq = new HashMap<Integer, Integer>();
        for (int a = 0; a < 10; a++) {
            digitFreq.put(a,0);
        }
        for (int i = 1000; i <= 9999; i++) {
            System.out.println("--");
            for (int j = 0; j < 4; j++) {
                int digit = (int) (i / Math.pow(10, j) % 10);
                int frequency = digitFreq.get(digit).intValue();
                digitFreq.put(digit, ++frequency);
            }
        }
        System.out.println(digitFreq.toString());
    }
}
