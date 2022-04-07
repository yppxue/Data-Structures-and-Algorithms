public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        Deque<Character> deque = new ArrayDeque<>();
        for (int i=0; i < word.length(); i++){
            deque.addLast(word.charAt(i));
        }
        return deque;
    }
    public boolean isPalindrome(String word){
        if (word == null){
            return false;
        }
        if (word.length() == 0 || word.length() == 1){
            return false;
        }

        Deque<Character> wordDeque = wordToDeque(word);
        while (wordDeque.size() > 1){
            if (wordDeque.removeFirst() != wordDeque.removeLast()){
                return false;
            }
        }
        return true;

    }
    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> wordDeque = wordToDeque(word);
        while (wordDeque.size() > 1){
            if (!cc.equalChars(wordDeque.removeFirst(), wordDeque.removeLast())){
                return false;
            }
        }
        return true;
    }
}
