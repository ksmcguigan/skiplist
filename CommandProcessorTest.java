import java.io.FileNotFoundException;
import student.TestCase;

/**
 *
 * @author Krista McGuigan (kmcguig3)
 * @author Megan Tucker (megdt923)
 * @version 2/1/2016
 */
public class CommandProcessorTest extends TestCase {
    private CommandProcessor cp;
    private int x;
    private int y;
    private int w;
    private int h;
    private String name = "";

    /**
     * This method sets up the tests that follow.
     *
     * @throws FileNotFoundException
     */
    public void setUp() throws FileNotFoundException {
        cp = new CommandProcessor("SyntaxTest.txt");
    }

    /**
     * Tests the insert method when a rectangle was successfully inserted
     */
    public void testInsertPass() {
        x = 3;
        y = 3;
        w = 3;
        h = 3;
        name = "r1";
        cp.insert(name, x, y, w, h);
        assertFuzzyEquals("Rectangle inserted: (r1, 3, 3, 3, 3)",
                systemOut().getHistory());
    }

    /**
     * Tests the insert method when a rectangle was not able to be inserted
     * because of the width
     */
    public void testInsertFailW() {
        x = 3;
        y = 3;
        w = -3;
        h = 3;
        name = "r2";
        cp.insert(name, x, y, w, h);
        assertFuzzyEquals("Rectangle rejected: (r2, 3, 3, -3, 3)",
                systemOut().getHistory());
    }

    /**
     * Tests the insert method when a rectangle was not able to be inserted
     * because of the height
     */
    public void testInsertFailH() {
        x = 3;
        y = 3;
        w = 3;
        h = -3;
        name = "r2";
        cp.insert(name, x, y, w, h);
        assertFuzzyEquals("Rectangle rejected: (r2, 3, 3, 3, -3)",
                systemOut().getHistory());
    }

    /**
     * Tests the insert method when the upper left corner x-coordinate is not
     * valid
     */
    public void testInsertFailX() {
        x = -3;
        y = 3;
        w = 3;
        h = 3;
        name = "r2";
        cp.insert(name, x, y, w, h);
        assertFuzzyEquals("Rectangle rejected: (r2, -3, 3, 3, 3)",
                systemOut().getHistory());
    }

    /**
     * Tests the insert method when the upper left corner y-coordinate is not
     * valid
     */
    public void testInsertFailY() {
        x = 3;
        y = -3;
        w = 3;
        h = 3;
        name = "r2";
        cp.insert(name, x, y, w, h);
        assertFuzzyEquals("Rectangle rejected: (r2, 3, -3, 3, 3)",
                systemOut().getHistory());
    }

    /**
     * Tests the insert method when the width (W+X) is out of bounds
     *
     */
    public void testInsertFailWX() {
        x = 1000;
        y = 3;
        w = 200;
        h = 3;
        name = "r2";
        cp.insert(name, x, y, w, h);
        assertFuzzyEquals("Rectangle rejected: (r2, 1000, 3, 200, 3)",
                systemOut().getHistory());
    }

    /**
     * Tests the insert method when the height (Y+H) is out of bounds
     */
    public void testInsertFailYH() {
        x = 3;
        y = 1000;
        w = 3;
        h = 200;
        name = "r2";
        cp.insert(name, x, y, w, h);
        assertFuzzyEquals("Rectangle rejected: (r2, 3, 1000, 3, 200)",
                systemOut().getHistory());
    }

    /**
     * Tests the remove by name method
     */
    public void testRemoveName() {
        name = "r3";
        cp.removeName(name);
        assertFuzzyEquals("Rectangle not removed: r3",
                systemOut().getHistory());
    }

    /**
     * Tests the remove by location method
     */
    public void testRemoveCoord() {
        x = 3;
        y = 3;
        w = -3;
        h = 3;
        cp.removeCoord(x, y, w, h);
        assertFuzzyEquals("Rectangle rejected: (3, 3, -3, 3)",
                systemOut().getHistory());
    }

    /**
     * Tests the regionsearch method when an invalid rectangle is passed in
     */
    public void testRegionsearchInvalidA() {
        x = 3;
        y = 3;
        w = -3;
        h = 3;
        cp.regionsearch(x, y, w, h);
        assertFuzzyEquals("Rectangle rejected: (3, 3, -3, 3):",
                systemOut().getHistory());
    }

    /**
     * Tests the regionsearch method when an invalid rectangle is passed in
     */
    public void testRegionsearchInvalidB() {
        x = 3;
        y = 3;
        w = 3;
        h = -3;
        cp.regionsearch(x, y, w, h);
        assertFuzzyEquals("Rectangle rejected: (3, 3, 3, -3):",
                systemOut().getHistory());
    }

    /**
     * Tests the intersections method
     */
    public void testIntersection() {
        cp.intersections();
        assertFuzzyEquals("Intersection pairs:", systemOut().getHistory());
    }

    /**
     * Tests the search method
     */
    public void testSearch() {
        name = "r4";
        cp.search(name);
        assertFuzzyEquals("Rectangle not found: r4", systemOut().getHistory());
    }

    /**
     * Tests the dump method
     */
    public void testDump() {
        cp.dump();
        assertFuzzyEquals("SkipList dump:\n"
                + "Node has depth 1, Value (null)\n" + "SkipList size is: 0",
                systemOut().getHistory());
    }
}