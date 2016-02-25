import student.TestCase;

/**
 * db test
 *
 * @author megantucker
 * @version Feb 3, 2016
 */
public class DatabaseTest extends TestCase {
    private Database db;

    /**
     * setup
     */
    public void setUp() {
        db = new Database();
    }

    /**
     * test search
     */
    public void testSearch() {
        db.insert("A", 4, 6, 1, 7);
        assertEquals(db.search("A").toString(), "(A, 4, 6, 1, 7)");
    }

    /**
     * test search all empty
     */
    public void testSearchAllEmpty() {
        assertEquals(db.searchAll("A"), "Rectangle not found: A");
    }

    /**
     * test search all
     */
    public void testSearchAll() {
        db.insert("A", 4, 6, 1, 7);
        db.insert("A", 1, 2, 3, 4);
        assertEquals(db.searchAll("A"),
                "Rectangles found:\n(A, 4, 6, 1, 7)\n(A, 1, 2, 3, 4)\n");
    }
    
    /**
     * test remove
     */
    public void testRemove() {
        db.insert("A", 4, 6, 1, 7);
        db.insert("B", 2, 3, 1, 7);
        db.removeName("A");
        db.removeCoord(2, 3, 1, 7);
        assertEquals(db.searchAll("A"), "Rectangle not found: A");
        assertEquals(db.searchAll("B"), "Rectangle not found: B");
    }
    
    /**
     * test intersections
     */
    /*public void testIntersections() {
        db.insert("A", 4, 6, 1, 7);
        db.insert("B", 2, 3, 1, 7);
        
    }*/
}