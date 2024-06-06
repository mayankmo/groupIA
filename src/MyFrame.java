import javax.swing.JFrame;
import java.awt.*;




public class MyFrame extends JFrame {



    private static final int FRAME_WIDTH = 400; // sets static size for width
    private static final int FRAME_HEIGHT = 400; // sets static size for height

    MyFrame(){
        setTitle("Sudoku Solver App"); // temporary title of the app
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //allows for user to exit on click of "X"
        setResizable(true); // allows for frame size to be changed so user can use on different screens
        setSize(FRAME_WIDTH, FRAME_HEIGHT); // sets frame size
        setLocationRelativeTo(null); // all objects created refer to center
        setVisible(true); // makes frame visible
        getContentPane().setBackground(new Color(58,58,58));//change background color


    }




}
