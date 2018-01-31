import edu.illinois.cs.cs125.lib.mazemaker.Maze;

import javax.swing.*;
import java.awt.*;

/**
 * Solve a randomly-generated maze.
 *
 * @see <a href="https://github.com/cs125-illinois/mazemaker">Mazemaker on GitHub</a>
 * @see <a href="https://cs125-illinois.github.io/mazemaker/">Mazemaker Documentation</a>
 * @see <a href="https://cs125.cs.illinois.edu/lab/2/#maze">Lab 2 Writeup</a>
 */
@SuppressWarnings("checkstyle:emptyblock")
public class SolveMaze {

    /**
     * Implement your maze solving algorithm in the main method below.
     *
     * @param unused unused input arguments
     */

    public static void main(final String[] unused) {
        /*
         * Create a new 10 x 10 maze. Feel free to change these values.
         */
        Maze maze = new Maze(10, 10);

        /*
         * Pick (0, 0), the bottom left corner, as the starting point.
         * Put the end in the top right corner.
         */
        maze.startAtZero();
        maze.endAtTopRight();
        JFrame j = new JFrame("Test");
        j.setSize(1024, 768);
        JTextArea area = new JTextArea(20, 20);
        area.setFont(new Font("monospaced", Font.PLAIN, 20));
        j.add(area);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setVisible(true);
        /*
         * You should be able to solve a 10 x 10 maze in (far fewer than) 1000 steps.
         * Feel free to adjust this number if you experiment with other mazes.
         */
        boolean hadWall = true;
        for (int step = 0; step < 1000; step++) {
            // Implement your maze solving algorithm here
//            while(!maze.isFinished() ){
            // check for wall
            maze.turnRight();
            boolean haveWall = !maze.canMove();
            maze.turnLeft();
            //
            maze.turnLeft();
            boolean haveLeftWall = !maze.canMove();
            maze.turnRight();

            if(!maze.canMove()){
                if(haveWall) maze.turnLeft();
                else maze.turnRight();
            }

            if(haveWall && maze.canMove()){
                maze.move();
                continue;
            }

            // check to see if wall ended
            if (!haveWall && hadWall) {
                maze.turnRight();
            }
            hadWall = haveWall;
            maze.move();
            area.setText(maze.toString());
            j.repaint();
            try {
                Thread.sleep(10);
            }catch(Throwable t){}
            System.out.println(maze.toString());
//            }

        }

        System.out.println(maze.toString());

        if (maze.isFinished()) {
            System.out.println("You solved the maze!");
        } else {
            System.out.println("Try again!");
        }
    }
}
/*
if(!maze.canMove()){
                    maze.turnRight();
                    if(!maze.canMove()){
                        maze.turnLeft();
                        maze.turnLeft();
                        maze.turnLeft();
                    }
                }
 */