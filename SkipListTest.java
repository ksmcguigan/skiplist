import student.TestCase;
import student.TestableRandom;

/**
 * // -------------------------------------------------------------------------
 * /** skiplist test
 *
 * @author megantucker
 * @version Feb 3, 2016
 */
public class SkipListTest extends TestCase {
    private SkipList<String, Integer> list;
    private SkipList<String, Rectangle> listRect;

    /**
     * setup
     */
    public void setUp() {
        list = new SkipList<String, Integer>();
        listRect = new SkipList<String, Rectangle>();
    }

    /**
     * constructor
     */
    public void testConstructor() {
        assertEquals(list.getLevel(), 0);
        assertEquals(list.getSize(), 0);
    }

    /**
     * test randomlevel
     */
    public void testRandomLevel() {
        TestableRandom.setNextInts(0, 1, 0);
        assertEquals(list.randomLevel(), 0);
        assertEquals(list.randomLevel(), 1);
    }

    /**
     * test insert
     */
    public void testInsert() {
        TestableRandom.setNextInts(1, 0, 1, 0, 1, 1, 0);
        list.insert("B", 1);
        assertEquals(list.getLevel(), 1);
        assertEquals(list.getSize(), 1);
        list.insert("C", 1);
        assertEquals(list.getSize(), 2);
        list.insert("A", 1);
        assertEquals(list.getSize(), 3);
        assertEquals(list.getLevel(), 2);
    }

    /**
     * test search
     */
    public void testSearch() {
        TestableRandom.setNextInts(1, 0, 1, 0, 1, 1, 0);
        list.insert("B", 1);
        list.insert("C", 2);
        list.insert("A", 3);

        assertTrue(list.search("A").equals(3));
        assertTrue(list.search("B").equals(1));
        assertTrue(list.search("C").equals(2));
    }

    /**
     * test search all
     */
    public void testSearchAll() {
        TestableRandom.setNextInts(1, 0, 1, 0, 1, 1, 0);
        Rectangle a = new Rectangle("A", 4, 6, 1, 7);
        Rectangle b = new Rectangle("A", 1, 2, 3, 4);
        listRect.insert("A", a);
        listRect.insert("A", b);

        assertEquals(listRect.searchAll("A"),
                "(A, 4, 6, 1, 7)\n(A, 1, 2, 3, 4)\n");
    }

    /**
     * test remove name
     */
    public void testRemoveName() {
        Rectangle a = new Rectangle("A", 4, 6, 1, 7);
        listRect.insert("A", a);
        assertTrue(listRect.removeName("A"));
        assertFalse(listRect.removeName("B"));
    }

    
    /**
     * test remove coordinates
     */
    /*public void testRemoveCoord() {
        Rectangle a = new Rectangle("A", 4, 6, 1, 7);
        Rectangle b = new Rectangle("B", 4, 6, 1, 7);
        Rectangle c = new Rectangle("C", -1, -6, -1, -7);
        Rectangle d = new Rectangle("D", 1000, 1000, 200, 200);
        listRect.insert("A", a);
        listRect.insert("C", c);
        listRect.insert("D", d);
        assertTrue(listRect.removeCoord(a));
        assertFalse(listRect.removeCoord(b));
        assertFalse(listRect.removeCoord(c));
        assertFalse(listRect.removeCoord(d));
    }*/

    /**
     * test intersections
     */
    public void testIntersections() {
        Rectangle a = new Rectangle("A", 0, 0, 200, 300);
        Rectangle b = new Rectangle("B", 0, 0, 15, 100);
        listRect.insert("A", a);
        listRect.insert("B", b);
        assertEquals("Intersection pairs: \n(A, 0, 0, 200, 300) | "
                + "(B, 0, 0, 15, 100)\n(B, 0, 0, 15, 100) | "
                + "(A, 0, 0, 200, 300)", listRect.intersections());
    }

    /**
     * test regionsearch
     */
    public void testRegionsearch() {
        Rectangle a = new Rectangle("A", 0, 0, 200, 300);
        listRect.insert("A", a);
        assertEquals(
                "Rectangles intersecting region (0, 0, 1000, 1000):"
                        + "\n(A, 0, 0, 200, 300)",
                listRect.regionsearch(0, 0, 1000, 1000));
    }

}