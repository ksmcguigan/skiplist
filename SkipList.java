import java.lang.reflect.Array;
import java.util.*;

/**
 * The SkipList class maintains rectangles sorted by name
 *
 * @author Krista McGuigan (kmcguig3)
 * @author Megan Tucker (megdt923)
 * @version 2/2/2016
 * @param <K> the K
 * @param <V> the V
 */
public class SkipList<K extends Comparable<K>, V extends Comparable> {
    private Random rand;
    private int size;
    private SkipNode head;
    private int level;

    /**
     * The constructor for the SkipList class
     */
    public SkipList() {
        rand = new student.TestableRandom();
        size = 0;
        level = 0;
        head = new SkipNode(0, null, null);
    }

    /**
     * random level
     *
     * @return level the level
     */
    int randomLevel() {
        int lev = 0;
        while (rand.nextInt(2) != 0) {
            lev++;
        }
        return lev;
    }

    /**
     * Adjust head
     *
     * @param newLevel the new level to adjust head to
     */
    private void adjustHead(int newLevel) {
        SkipNode newHead = new SkipNode(newLevel, null, null);
        for (int i = 0; i < head.forward.length; i++) {
            newHead.forward[i] = head.forward[i];
        }
        level = newLevel;
        head = newHead;
    }

    /**
     * insert
     *
     * @param key the key
     * @param val the val
     * @return boolean if insertion happened
     */
    public boolean insert(K key, V val) {
        int newLevel = randomLevel();

        if (level < newLevel) {
            adjustHead(newLevel);
        }

        @SuppressWarnings("unchecked") // Generic array allocation
        SkipNode[] update = (SkipNode[]) Array
                .newInstance(SkipList.SkipNode.class, level + 1);
        SkipNode x = head; // Start at header node
        for (int i = level; i >= 0; i--) { // Find insert position
            while ((x.forward[i] != null)
                    && (key.compareTo((x.forward[i]).key()) > 0)) {
                x = x.forward[i];
            }
            update[i] = x; // Track end at level i
        }

        x = new SkipNode(newLevel, key, val);
        for (int i = 0; i <= newLevel; i++) { // Splice into list
            x.forward[i] = update[i].forward[i]; // Who x points to
            update[i].forward[i] = x; // Who y points to
        }
        size++;
        return true;
    }

    /**
     * Searches the database for the first matching element
     *
     * @param key key to search for
     * @return V element if one exists, null otherwise
     */
    public V search(K key) {
        SkipNode x = head; // Dummy header node
        for (int i = level; i >= 0; i--)
            while ((x.forward[i] != null)
                    && (key.compareTo(x.forward[i].key()) > 0))
                x = x.forward[i]; // Go one last step
        x = x.forward[0]; // Move to actual record, if it exists
        if ((x != null) && (key.compareTo(x.key()) == 0))
            return x.val();
        else
            return null;
    }

    /**
     * Searches for all instances of key, returns string representing result
     *
     * @param key Key to search for
     * @return String representing all results found
     */
    public String searchAll(K key) {
        SkipNode x = head; // Dummy header node
        for (int i = level; i >= 0; i--)
            while ((x.forward[i] != null)
                    && (key.compareTo(x.forward[i].key()) > 0))
                x = x.forward[i]; // Go one last step
        x = x.forward[0]; // Move to actual record, if it exists
        String result = "";
        while (x != null && key.compareTo(x.key) == 0) {
            result = x.val().toString() + "\n" + result;
            x = x.forward[0];
        }
        return result;
    }

    /**
     * removeName iterates through the entire structure to remove the key from
     * the SkipList
     * 
     * @param key the key to be removed
     * @return boolean whether the node has been removed
     */
    public boolean removeName(K key) {
        SkipNode removeNode = null;
        SkipNode x = head; // Temp header node
        for (int i = level; i >= 0; i--) {
            while ((x.forward[i] != null)
                    && (key.compareTo(x.forward[i].key()) > 0)) {
                x = x.forward[i]; // Go one last step
            }
        }
        removeNode = x.forward[0];

        if (removeNode == null || removeNode.key.compareTo(key) != 0) {
            System.out.printf("Rectangle not removed: %s\n", key);
            return false;
        }
        else {
            x = head; // return x to head of list
            for (int i = removeNode.forward.length - 1; i >= 0; i--) {
                while (x.forward[i] != null) {
                    if (x.forward[i] == removeNode) {
                        x.forward[i] = removeNode.forward[i];
                        break;
                    }
                    else {
                        x = x.forward[i]; // move to next item on level
                    }
                }
            }
            System.out.printf("Rectangle removed: %s\n",
                    removeNode.val.toString());
            size--;
            return true;
        }
    }

    /**
     * removeCoord iterates through the entire structure to remove the key from
     * the SkipList
     * 
     * @param val val
     * @return boolean
     */
    public V removeCoord(V val) {

        SkipNode removeNode = null;
        SkipNode x = head; // Temp header node

        // use compareCoordinates instead of compareTo to check for match
        while (x.forward[0] != null && val.compareTo(x.forward[0].val) != 0) {
            x = x.forward[0]; // iterate forward
        }
        // save pointer to desired remove node
        removeNode = x.forward[0];
        // exit early if we found no matching node
        if (removeNode == null || val.compareTo(removeNode.val) != 0) {
            return null;
        }
        x = head; // return x to head of list
        for (int i = removeNode.forward.length - 1; i >= 0; i--) {
            while (x.forward[i] != null) {
                // check if the next node is the node we are trying to remove
                if (x.forward[i] == removeNode) { // at the node to remove
                    // set forward to be the forward of removed, skipping it
                    x.forward[i] = removeNode.forward[i];
                    // break out so we can delete from next level
                    break;
                }
                else { // move to next item on level
                    x = x.forward[i];
                }
            }
        }
        size--;
        return removeNode.val();
    }

    /**
     * intersections
     * 
     * @return intersections string
     */
    public String intersections() {
        SkipNode x = head;

        String result = "Intersection pairs: ";
        Rectangle xRect;
        Rectangle yRect;

        for (int i = 0; i < size; i++) {
            x = x.forward[0];
            SkipNode y = head;
            xRect = (Rectangle) x.val();

            for (int j = 0; j < size; j++) {
                y = y.forward[0];
                yRect = (Rectangle) y.val();

                if (i != j && xRect.getX() + xRect.getW() > yRect.getX()
                        && yRect.getX() + yRect.getW() > xRect.getX()
                        && xRect.getY() + xRect.getH() > yRect.getY()
                        && yRect.getY() + yRect.getH() > xRect.getY()) {
                    result = result + "\n" + x.val().toString() + " | "
                            + y.val().toString();
                }
            }
        }
        return result;
    }

    /**
     * Checks that rectangles intersect the specified region
     * 
     * @param xx x
     * @param yy y
     * @param ww w
     * @param hh h
     * @return string result
     */
    public String regionsearch(int xx, int yy, int ww, int hh) {
        Rectangle regionRect = new Rectangle("", xx, yy, ww, hh);
        String result = "Rectangles intersecting region (" + xx + ", " + yy
                + ", " + ww + ", " + hh + "):";
        SkipNode x = head;
        for (int i = 0; i < size; i++) {
            x = x.forward[0];
            Rectangle currRect = (Rectangle) x.val();
            if (currRect.intersects(regionRect)) {
                result = result + "\n" + x.val().toString();
            }
        }
        return result;
    }

    /**
     * toString
     *
     * @return String
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        SkipNode x = head;
        sb.append(String.format("Node has depth %d, Value (null)\n",
                x.forward.length));
        x = x.forward[0];
        while (x != null) {
            sb.append(String.format("Node has depth %d, Value %s\n",
                    x.forward.length, x.val().toString()));
            x = x.forward[0];
        }
        sb.append(String.format("SkipList size is: %d", size));
        return sb.toString();
    }

    /**
     * get size
     * 
     * @return size
     */
    public int getSize() {
        return size;
    }

    /**
     * get level
     * 
     * @return level
     */
    public int getLevel() {
        return level;
    }

    /**
     * SkipNode class
     * 
     * @author kmcguig3, megdt923
     * @version 2/2/2016
     *
     */
    private class SkipNode implements Comparable<SkipNode> {
        SkipNode[] forward;
        K key;
        V val;

        /**
         * Create a new SkipNode object.
         *
         * @param level level of node
         * @param k key for node
         * @param v val for node
         */
        @SuppressWarnings("unchecked")
        public SkipNode(int level, K k, V v) {
            forward = (SkipNode[]) Array.newInstance(SkipList.SkipNode.class,
                    level + 1);
            key = k;
            val = v;
        }

        /**
         * compare to
         * 
         * @param o o
         * @return value
         */
        public int compareTo(SkipNode o) {
            return key.compareTo(o.key);
        }

        /**
         * key
         * 
         * @return key
         */
        public K key() {
            return key;
        }

        /**
         * value
         * 
         * @return value
         */
        public V val() {
            return val;
        }
    }
}