public class Palindrome {

    public Deque<Character> wordToDeque(String word){
        if (word.isEmpty()){
            return null;
        }
        Deque<Character> charDeque = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++){
            charDeque.addLast(word.charAt(i));
        }
        return charDeque;
    }

    public boolean isPalindrome(String word){
        Deque<Character> deq = wordToDeque(word);
        return isPalindromeHelper(deq);
    }

    public boolean isPalindromeHelper(Deque<Character> deque) {
        if (deque.size() <= 1)
            return true;
        char a = deque.removeFirst();
        char b = deque.removeLast();
        return a == b && isPalindromeHelper(deque);
    }

    /* Overload isPalindrome() as off by 1. */
    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> deque = wordToDeque(word);
        return isOffByNPalindromeHelper(deque, cc);
    }

    public boolean isOffByNPalindromeHelper(Deque<Character> deque, CharacterComparator cc) {
        if (deque.size() <= 1)
            return true;
        char a = deque.removeFirst();
        char b = deque.removeLast();
        return cc.equalChars(a, b) && isOffByNPalindromeHelper(deque, cc);
    }
}
