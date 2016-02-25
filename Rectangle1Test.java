import java.io.FileNotFoundException;
import student.TestCase;

/**
 * @author ayaan
 * @version 2
 */
public class Rectangle1Test extends TestCase {

    /**
     * This method sets up the tests that follow.
     */
    public void setUp() {
        // Nothing here yet
    }

    // ----------------------------------------------------------
    /**
     * This method is simply to get code coverage of the class declaration.
     */
    public void testRInit() {
        Rectangle1 rectangle = new Rectangle1();
        assertTrue(rectangle != null);
        String[] s = new String[1];
        s[0] = "SyntaxTest.txt";
        Rectangle1.main(s);
        assertFuzzyEquals("Rectangle rejected: (r_r, -1, -20, 3, 4)\n"
                + "Rectangle rejected: (rec, 7, -8, 1, 3)\n"
                + "Rectangle rejected: (virtual_rec0, 1, 1, 0, 0)\n"
                + "Rectangle rejected: (virtual_REC0, 0, 0, 11, 0)\n"
                + "Rectangle rejected: (inExistRec_0, 1, 1, -1, -2)\n"
                + "Rectangle rejected: (11, 11, 0, 0)\n"
                + "Intersection pairs:\n" + "SkipList dump:\n"
                + "Node has depth 1, Value (null)\n" + "SkipList size is: 0\n"
                + "Rectangle not found: r_r\n" + "Rectangle not removed: r_r\n"
                + "Rectangle rejected: (1, 1, 0, 0)\n"
                + "Rectangles intersecting region: (-5, -5, 20, 20):",
                systemOut().getHistory());
    }

    /**
     * Tests a random file
     */
    public void testFile() {
        try {
            Rectangle1.main(new String[0]);
        }
        catch (Exception e) {
            assertTrue(e instanceof ArrayIndexOutOfBoundsException);
        }
        try {
            String[] s = new String[1];
            s[0] = "invalidFile.txt";
            Rectangle1.main(s);
        }
        catch (Exception e) {
            assertTrue(e instanceof FileNotFoundException);
        }
    }
}
