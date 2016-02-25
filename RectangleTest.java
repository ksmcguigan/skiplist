import student.TestCase;

/**
 * // -------------------------------------------------------------------------
 * /** rectangle test
 *
 * @author megantucker
 * @version Feb 3, 2016
 */
public class RectangleTest extends TestCase {
    private Rectangle r;

    /**
     * setup
     */
    public void setUp() {
        r = new Rectangle("hi", 1, 2, 3, 4);
    }

    /**
     * test
     */
    public void test() {
        r.setX(2);
        assertEquals(r.getX(), 2);
        r.setY(2);
        assertEquals(r.getY(), 2);
        r.setW(2);
        assertEquals(r.getW(), 2);
        r.setH(2);
        assertEquals(r.getH(), 2);
    }
    
    /**
     * test intersects
     */
    public void testIntersects() {
        Rectangle r1 = new Rectangle("", 0, 0, 1000, 1000);
        Rectangle r2 = new Rectangle("", 0, 0, 4, 4);
        assertTrue(r1.intersects(r2));
        Rectangle r3 = new Rectangle("", 10, 10, 2, 2);
        Rectangle r4 = new Rectangle("", 0, 0, 4, 4);
        assertFalse(r3.intersects(r4));
        Rectangle r5 = new Rectangle("", 0, 0, 4, 4);
        Rectangle r6 = new Rectangle("", 10, 10, 2, 2);
        assertFalse(r5.intersects(r6));
        Rectangle r7 = new Rectangle("", 0, 0, 20, 1);
        Rectangle r8 = new Rectangle("", 10, 10, 2, 2);
        assertFalse(r7.intersects(r8));
        Rectangle r9 = new Rectangle("", 0, 20, 20, 1);
        Rectangle r10 = new Rectangle("", 10, 10, 2, 2);
        assertFalse(r9.intersects(r10));
    }
    
    /**
     * test check
     */
    public void testCheck() {
        assertTrue(Rectangle.check(0, 0, 1000, 1000));
        assertFalse(Rectangle.check(-3, 0, 1000, 1000));
        assertFalse(Rectangle.check(0, -3, 1000, 1000));
        assertFalse(Rectangle.check(0, 0, -3, 1000));
        assertFalse(Rectangle.check(0, 0, 1000, -3));
        assertFalse(Rectangle.check(200, 0, 1000, 1000));
        assertFalse(Rectangle.check(0, 200, 1000, 1000));
    }
    
    /**
     * test compare to
     */
    public void testCompareTo() {
        Rectangle r1 = new Rectangle("", 0, 0, 1000, 1000);
        Rectangle r2 = new Rectangle("", 0, 0, 1000, 1000);
        assertEquals(r1.compareTo(r2), 0);
        Rectangle r3 = new Rectangle("", 3, 0, 1000, 1000);
        assertEquals(r1.compareTo(r3), -1);
        Rectangle r4 = new Rectangle("", 0, 3, 1000, 1000);
        assertEquals(r1.compareTo(r4), -1);
        Rectangle r5 = new Rectangle("", 0, 0, 3, 1000);
        assertEquals(r1.compareTo(r5), -1);
        Rectangle r6 = new Rectangle("", 0, 0, 1000, 3);
        assertEquals(r1.compareTo(r6), -1);
    }
}