
/** This class outputs all palindromes in the words file in the current directory. */
public class PalindromeObnFinder {
    public static void main(String[] args) {
        int minLength = 4;
        Palindrome palindrome = new Palindrome();
        for (int n = 0; n < 25; n++) {
            OffByN obn = new OffByN(n);
            In in = new In("../library-fa20/data/words.txt");
            System.out.print(n + ":");
            int count = 0;
            while (!in.isEmpty()) {
                String word = in.readString();
                if (word.length() >= minLength && palindrome.isPalindrome(word, obn)) {
                    //System.out.println(word);
                    count ++;
                }
            }
            System.out.println(count);
        }
    }
}
