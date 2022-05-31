/**
Samuel Sarantos
 Homework 2
 */
import java.util.*;

public class HangmanManager {
    private Set<String> wordList;
    private SortedSet<Character> guesses;
    private int remainingGuesses;

    public HangmanManager(Collection<String> dictionary, int length, int max) {
        if (length < 1 || max < 0)
            throw new IllegalArgumentException();

        remainingGuesses = max;
        guesses = new TreeSet<Character>();
        wordList = new TreeSet<String>();

        for (String word : dictionary) {
            if (word.length() == length)
                wordList.add(word);
        }
    }

    public Set<String> words() {
        return wordList;
    }

    public int guessesLeft() {
        return remainingGuesses;
    }

    public SortedSet<Character> guesses() {
        return guesses;
    }

    public String pattern() {
        if (wordList.isEmpty())
            throw new IllegalStateException();
        return pattern(wordList.iterator().next());
    }


    private int countMatches (String pattern, char guess) {
        int matches = 0;
        for (int i = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) == guess)
                matches++;
        }
        return matches;
    }

    private void updateWordList(Map<String, Set<String>> families) {
        for (String word : wordList) {
            String currentPattern = pattern(word);
            if (!families.containsKey(currentPattern))
                families.put(currentPattern, new TreeSet<String>());
            families.get(currentPattern).add(word);
        }
        wordList = families.get(getLargestKey(families));
    }

    private String getLargestKey(Map<String, Set<String>> families) {
        int maxLength = 0;
        String maxKey = "";
        for (String key : families.keySet()) {
            if (families.get(key).size() > maxLength) {
                maxLength = families.get(key).size();
                maxKey = key;
            }
        }
        return maxKey;
    }

    private String pattern(String word) {
        String builder = "";
        for (int i = 0; i < word.length(); i++) {
            if (guesses.contains(word.charAt(i)))
                builder += word.substring(i, i + 1);
            else
                builder += "-";
        }
        return builder;
    }
    public int record(char guess) {
        if (wordList.isEmpty() || remainingGuesses == 0)
            throw new IllegalStateException();
        else if (!wordList.isEmpty() && guesses.contains(guess))
            throw new IllegalArgumentException();

        Map<String, Set<String>> families = new TreeMap<String, Set<String>>();
        String initialPattern = this.pattern();
        guesses.add(guess);
        updateWordList(families);

        if (this.pattern().equals(initialPattern))
            remainingGuesses--;
        return countMatches(this.pattern(), guess);
    }
}
