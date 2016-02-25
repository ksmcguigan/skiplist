import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * The CommandProcessor class
 *
 * @author Krista McGuigan (kmcguig3)
 * @author Megan Tucker (megdt923)
 * @version 2/1/16
 */
public class CommandProcessor {
    private File inputFile;
    private Database db;

    /**
     * CommandProcessorConstructor
     *
     * @param filename the file taken in
     * @throws FileNotFoundException
     */
    public CommandProcessor(String filename) throws FileNotFoundException {
        inputFile = new File(filename);
        db = new Database();
    }

    /**
     * runCommands
     *
     * @throws FileNotFoundException
     */
    public void runCommands() throws FileNotFoundException {
        Scanner fileScan = new Scanner(inputFile);

        while (fileScan.hasNextLine()) {
            String line = fileScan.nextLine();
            line = line.trim();
            if (line.isEmpty()) {
                continue;
            }
            Scanner lineScan = new Scanner(line);
            String command = lineScan.next();

            switch (command) {
                case "insert": {
                    String name = lineScan.next();
                    int x = lineScan.nextInt();
                    int y = lineScan.nextInt();
                    int w = lineScan.nextInt();
                    int h = lineScan.nextInt();
                    insert(name, x, y, w, h);
                    break;
                }
                case "remove": {
                    if (lineScan.hasNextInt()) {
                        int x = lineScan.nextInt();
                        int y = lineScan.nextInt();
                        int w = lineScan.nextInt();
                        int h = lineScan.nextInt();
                        removeCoord(x, y, w, h);
                    }
                    else {
                        String name = lineScan.next();
                        removeName(name);
                    }
                    break;
                }
                case "regionsearch": {
                    int x = lineScan.nextInt();
                    int y = lineScan.nextInt();
                    int w = lineScan.nextInt();
                    int h = lineScan.nextInt();
                    regionsearch(x, y, w, h);
                    break;
                }
                case "intersections": {
                    intersections();
                    break;
                }
                case "search": {
                    String name = lineScan.next();
                    search(name);
                    break;
                }
                default:
                    dump();
                    break;
            }
            lineScan.close();
        }
        fileScan.close();
    }

    /**
     * Inserts a valid rectangle into the database
     *
     * @param name the name of the rectangle
     * @param x the x coordinate of the upper left corner
     * @param y the y coordinate of the upper left corner
     * @param w the width
     * @param h the height
     */
    public void insert(String name, int x, int y, int w, int h) {
        if (w <= 0 || h <= 0 || x < 0 || y < 0 || x + w > 1024
                || y + h > 1024) {
            System.out.printf("Rectangle rejected: " + "(%s, %d, %d, %d, %d)\n",
                    name, x, y, w, h);
        }
        else {
            db.insert(name, x, y, w, h);
            System.out.printf("Rectangle inserted: " + "(%s, %d, %d, %d, %d)\n",
                    name, x, y, w, h);
        }
    }

    /**
     * Removes a valid rectangle from the database
     *
     * @param name the name of the rectangle
     */
    public void removeName(String name) {
        db.removeName(name);
    }

    /**
     * Removes a valid rectangle with the specified dimensions
     *
     * @param x the x coordinate of the upper left corner
     * @param y the y coordinate of the upper left corner
     * @param w the width
     * @param h the height
     */
    public void removeCoord(int x, int y, int w, int h) {
        db.removeCoord(x, y, w, h);
    }

    /**
     * Reports all rectangles in the database that intersect the query rectangle
     * specified by the parameters.
     *
     * @param x the x coordinate of the upper left corner
     * @param y the y coordinate of the upper left corner
     * @param w the width
     * @param h the height
     */
    public void regionsearch(int x, int y, int w, int h) {
        if (w <= 0 || h <= 0) {
            System.out.printf("Rectangle rejected: " + "(%d, %d, %d, %d)\n", x,
                    y, w, h);
        }
        else {
            System.out.println(db.regionsearch(x, y, w, h));
        }
    }

    /**
     * Reports all pairs of rectangles within the database that intersect
     */
    public void intersections() {
        System.out.println(db.intersections());
    }

    /**
     * Searches for information about the rectangle
     *
     * @param name the name of the rectangle
     */
    public void search(String name) {
        System.out.println(db.searchAll(name));
    }

    /**
     * dump
     */
    public void dump() {
        System.out.println(db.dump());
    }
}
