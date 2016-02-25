/**
 * database class
 *
 * @author Megan Tucker Krista McGuigan
 * @version Feb 3, 2016
 */
public class Database {
    private SkipList<String, Rectangle> list;

    /**
     * constructor
     */
    public Database() {
        list = new SkipList<String, Rectangle>();
    }

    /**
     * insert
     *
     * @param name name
     * @param x x
     * @param y y
     * @param w w
     * @param h h
     */
    public void insert(String name, int x, int y, int w, int h) {
        Rectangle newRectangle = new Rectangle(name, x, y, w, h);
        list.insert(name, newRectangle);
    }

    /**
     * search
     *
     * @param name name
     * @return rectangle
     */
    public Rectangle search(String name) {
        return list.search(name);
    }

    /**
     * searchall
     *
     * @param name name
     * @return string
     */
    public String searchAll(String name) {
        String result = list.searchAll(name);
        if (result.isEmpty()) {
            return String.format("Rectangle not found: %s", name);
        }
        else {
            return String.format("Rectangles found:\n%s", result);
        }
    }

    /**
     * dump
     *
     * @return string
     */
    public String dump() {
        return "SkipList dump:\n" + list.toString();
    }

    /**
     * remove name
     * 
     * @param name name
     */
    public void removeName(String name) {
        list.removeName(name);
    }

    /**
     * remove coordinates
     * 
     * @param x x
     * @param y y
     * @param w w
     * @param h h
     */
    public void removeCoord(int x, int y, int w, int h) {
        if (!Rectangle.check(x, y, w, h)) {
            System.out.printf("Rectangle rejected: %d, %d, %d, %d\n", x, y, w,
                    h);
            return;
        }
        Rectangle newRectangle = new Rectangle("", x, y, w, h);
        Rectangle temp = list.removeCoord(newRectangle);
        if (temp != null) {
            System.out.printf("Rectangle removed: %s\n",
                    temp.toString());
        }
        else {
            System.out.printf("Rectangle not found: %d, %d, %d, %d\n", x, y, w,
                    h);
        }
    }

    /**
     * intersections
     * 
     * @return intersections
     */
    public String intersections() {
        return list.intersections();
    }

    /**
     * regionsearch
     * 
     * @param x x
     * @param y y
     * @param w w
     * @param h h
     * @return string
     */
    public String regionsearch(int x, int y, int w, int h) {
        return list.regionsearch(x, y, w, h);
    }
}