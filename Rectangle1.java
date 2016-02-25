import java.io.FileNotFoundException;

// On my honor:
//
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.
/**
 * The class containing the main method, the entry point of the application.
 *
 * @author Krista McGuigan (kmcguig3)
 * @author Megan Tucker (megdt923)
 * @version 1/24/16
 */
public class Rectangle1 {

    /**
     * The main class entry-point for running class as a Java program
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        try {
            CommandProcessor cp = new CommandProcessor(args[0]);
            cp.runCommands();
        }
        catch (FileNotFoundException e) {
            System.err.println("Please enter a valid file");
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.err.println(
                    "Please invoke as java Rectangle1 " + "{command-file}");
        }
    }
}
