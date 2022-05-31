import org.junit.*;     // JUnit tools
import java.util.*;     // Collections
import java.io.*;       // File access

/**
 * This comment has been modified by Samuel Sarantos
 */
public class HangmanManagerTest {

    /* Loads the words in fileName and returns the set of all words in that file*/
    private Set<String> getDictionary() {
        try {
            Scanner fileScanner = new Scanner(new File("dictionary.txt"));
            Set<String> dictionary = new HashSet<>();
            while(fileScanner.hasNext()) {
                dictionary.add(fileScanner.next());
            }
            return dictionary;
        } catch(FileNotFoundException e) {
            Assert.fail("Something went wrong.");      //Something went wrong
        }
        /* Should never be reached. */
        return new HashSet<>();
    }

    /**
     * All test functions are not used but could be
     */
    @Test
    public void patternNegativeTest() {
        HangmanManager hmm = new HangmanManager(new LinkedList<>(), 4, 10);
        try{
            hmm.pattern();
            Assert.fail("should throw based on bad pattern");
        }catch(IllegalStateException b){
        }
    }
    private void constructorFail(Set<String> d, int length, int maximum) {
        try {
            new HangmanManager(d, length, maximum);
            Assert.fail("No exception thrown");
        } catch (RuntimeException e) {}
    }

    private void constructorHappyCase(Set<String> dictionary, int length, int max) {
        try {
            new HangmanManager(dictionary, length, max);
        } catch (RuntimeException e) {
            Assert.fail(e.getMessage());
        }
    }
    @Test
    public void wordsTest() {
        Set<String> t = getDictionary();
        HangmanManager h = new HangmanManager(t, 3, 17);

        Assert.assertNotSame(h.words(), t);
        t.add("abc");
        Assert.assertNotSame(h.words(), t);
    }
    @Test
    public void recordTest() {
        HangmanManager hMan = new HangmanManager(getDictionary(), 4, 10);
        hMan.record('b');
        Set<String> hs = new HashSet<>();
        hs.add("ally");
        hs.add("cool");
        hs.add("deal");
        hs.add("else");
        hs.add("flew");
        hs.add("good");
        hs.add("hope");
        Assert.assertEquals(hMan.words(), hs);

    }
    @Test
    public void patternTest() {
        HangmanManager h = new HangmanManager(getDictionary(), 4, 7);
        Assert.assertEquals(h.pattern(), "- - - -");
        int a = h.record('b');
        Assert.assertEquals(h.pattern(), "- - - -");
        Assert.assertEquals(0, a);
        Assert.assertEquals("decrease by 1", 6, h.guessesLeft());

        int b = h.record('o');
        Assert.assertEquals(h.pattern(), "- - - -");
        Assert.assertEquals(0, b);
        Assert.assertEquals("decrease by 1 again" , 5, h.guessesLeft());

        int c = h.record('a');
        Assert.assertEquals(h.pattern(), "- - - -");
        Assert.assertEquals(0, c);
        Assert.assertEquals("decrease by 1 again" , 4, h.guessesLeft());

        int d = h.record('e');
        Assert.assertEquals(h.pattern(), "- - e -");
        Assert.assertEquals(0, c);
        Assert.assertEquals("stops decreasing" , 4, h.guessesLeft());
    }
}

