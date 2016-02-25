/**
 * Rectangle class
 *
 * @author Krista McGuigan (kmcguig3)
 * @author Megan Tucker (megdt923)
 * @version 2/1/16
 */
public class Rectangle implements Comparable {
    private String name;
    private int x;
    private int y;
    private int h;
    private int w;

    /**
     * The rectangle constructor to create a rectangle object
     *
     * @param name the name
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param w the width
     * @param h the height
     */
    public Rectangle(String name, int x, int y, int w, int h) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    /**
     * Gets the name of the rectangle
     *
     * @return name the name of the rectangle
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the rectangle
     *
     * @param name the name of the rectangle
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the x-coordinate
     *
     * @return x the x-coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the x-coordinate
     *
     * @param x the x-coordinate
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Gets the y-coordinate
     *
     * @return y the y-coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the y-coordinate
     *
     * @param y the y-coordinate
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Gets the height
     *
     * @return h the height
     */
    public int getH() {
        return h;
    }

    /**
     * Sets the height
     *
     * @param h the height
     */
    public void setH(int h) {
        this.h = h;
    }

    /**
     * Gets the width
     *
     * @return w the width
     */
    public int getW() {
        return w;
    }

    /**
     * Sets the width
     *
     * @param w the width
     */
    public void setW(int w) {
        this.w = w;
    }

    /**
     * tostring
     * 
     * @return string
     */
    public String toString() {
        return String.format("(%s, %d, %d, %d, %d)", name, x, y, w, h);
    }

    /**
     * intersects
     * 
     * @param r r
     * @return if intersects
     */
    public boolean intersects(Rectangle r) {
        return (x + w >= r.getX() && r.getX() + r.getW() >= x
                && y + h >= r.getY() && r.getY() + r.getH() >= y);
    }

    /**
     * check
     * 
     * @param x x
     * @param y y
     * @param w w
     * @param h h
     * @return check
     */
    public static boolean check(int x, int y, int w, int h) {
        return !(w <= 0 || h <= 0 || x < 0 || y < 0 || x + w > 1024
                || y + h > 1024);
    }

    /**
     * compare to
     * 
     * @param o o
     * @return int
     */
    public int compareTo(Object o) {
        if (o instanceof Rectangle) {
            Rectangle r = (Rectangle) o;
            if (r.getX() == x && r.getY() == y && r.getH() == h
                    && r.getW() == w) {
                return 0;
            }
        }
        return -1;
    }
}