import javax.swing.*;
import java.awt.*;

/**
 * This class handles the graphical user interface (GUI) of the Connect Four game.
 * It creates the game board and manages user interactions.
 */
public class ConnectFourGui {
    public JButton[][] buttons; // 2D array of buttons representing the game board
    private JFrame frame;       // The main window for the GUI

    /**
     * Constructor initializes the buttons array as null initially.
     */
    public ConnectFourGui() {
        buttons = null; // Initialize buttons as null initially
    }

    /**
     * Prompts the user to choose the dimensions of the game board.
     *
     * @return an array of two integers representing the number of rows and columns.
     */
    public int[] chooseDimensions() {
        String[] options = {"(8x5)", "(10x6)", "(12x7)"};
        int[] dimensions = new int[2];
        int chosenOne = JOptionPane.showOptionDialog(
                null,
                "Choose board dimensions",
                "Select Board Dimensions",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);

        if (chosenOne == 0) {
            dimensions[0] = 5; // Rows
            dimensions[1] = 8; // Columns
        } else if (chosenOne == 1) {
            dimensions[0] = 6;
            dimensions[1] = 10;
        } else if (chosenOne == 2) {
            dimensions[0] = 7;
            dimensions[1] = 12;
        }
        return dimensions;
    }

    /**
     * Draws the game board based on the chosen dimensions.
     *
     * @param chosenDimensions an array containing the number of rows and columns.
     */
    public void drawBoard(int[] chosenDimensions) {
        int rows = chosenDimensions[0];
        int cols = chosenDimensions[1];

        buttons = new JButton[rows][cols]; // Initialize button array

        frame = new JFrame("Connect Four");
        frame.setLayout(new GridLayout(rows, cols));

        // Create buttons for each cell in the grid
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                JButton button = new JButton();
                buttons[i][j] = button;
                frame.add(button);
            }
        }

        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                int response = JOptionPane.showConfirmDialog(frame,
                        "Are you sure you want to exit?", "Confirm Exit",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        frame.setVisible(true);
    }

    /**
     * Returns the 2D array of buttons representing the game board.
     *
     * @return the 2D array of buttons.
     */
    public JButton[][] getButtons() {
        return buttons;
    }

    /**
     * Adds action listeners to the top row of buttons, which correspond to columns.
     * When a button is clicked, a move is made in the corresponding column.
     *
     * @param game the ConnectFour game instance.
     */
    public void addListeners(ConnectFour game) {
        int cols = game.getBoard()[0].length;

        // Add listeners for each column
        for (int col = 0; col < cols; col++) {
            int finalCol = col;
            buttons[0][col].addActionListener(e -> {
                if (game.makeMove(finalCol)) {
                    updateGui(game);
                    if (game.checkWinner()) {
                        JOptionPane.showMessageDialog(null, game.getPlayer() + " wins!");
                        game.resetGame();
                        updateGui(game);
                    } else if (game.isBoardFull()) {
                        JOptionPane.showMessageDialog(null, "It's a draw!");
                        game.resetGame();
                        updateGui(game);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Column is full. Try another.");
                }
            });
        }
    }

    /**
     * Updates the GUI to reflect the current state of the game.
     *
     * @param game the ConnectFour game instance.
     */
    public void updateGui(ConnectFour game) {
        char[][] board = game.getBoard();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                buttons[i][j].setText(String.valueOf(board[i][j]));
            }
        }
    }
}
