/**
 * This class handles the game logic for Connect Four.
 * It manages the game board, player turns, and checks for a winner.
 */
public class ConnectFour {
    private char[][] board;
    private int rows, cols;
    private String player;

    /**
     * Constructor initializes the game board with the given dimensions and sets the starting player to "Player 1".
     *
     * @param board the initial game board (usually null, as it's initialized inside the constructor).
     * @param rows the number of rows in the board.
     * @param cols the number of columns in the board.
     * @param player the starting player ("Player 1").
     */
    public ConnectFour(char[][] board, int rows, int cols, String player) {
        this.board = new char[rows][cols];
        this.rows = rows;
        this.cols = cols;
        this.player = "Player 1";
        initializeBoard();
    }

    /**
     * Initializes the board with empty spaces (' ').
     */
    public void initializeBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = ' ';
            }
        }
    }

    /**
     * Makes a move by placing a disc in the specified column.
     * The disc will fall to the lowest available row in that column.
     *
     * @param column the column where the player wants to drop the disc.
     * @return true if the move was successful, false if the column is full.
     */
    public boolean makeMove(int column) {
        for (int i = rows - 1; i >= 0; i--) {
            if (board[i][column] == ' ') {
                board[i][column] = (player.equals("Player 1")) ? 'X' : 'O';
                changeTheTurn();
                return true;
            }
        }
        return false;
    }

    /**
     * Resets the game by re-initializing the board.
     */
    public void resetGame() {
        initializeBoard();
    }

    /**
     * Checks if the board is full (no empty spaces).
     *
     * @return true if the board is full, false otherwise.
     */
    public boolean isBoardFull() {
        for (char[] row : board) {
            for (char cell : row) {
                if (cell == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Changes the turn to the other player (Player 1 or Player 2).
     */
    private void changeTheTurn() {
        if (!checkWinner()) {
            player = player.equals("Player 1") ? "Player 2" : "Player 1";
        }
    }

    /**
     * Checks if there is a winner (four discs in a row horizontally, vertically, or diagonally).
     *
     * @return true if there is a winner, false otherwise.
     */
    public boolean checkWinner() {
        return checkHorizontal() || checkVertical() || checkDiagonal();
    }

    /**
     * Checks if there is a horizontal line of four matching discs.
     *
     * @return true if there is a horizontal line, false otherwise.
     */
    private boolean checkHorizontal() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols - 3; j++) {
                if (board[i][j] != ' ' && board[i][j] == board[i][j + 1] && board[i][j] == board[i][j + 2] && board[i][j] == board[i][j + 3]) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if there is a vertical line of four matching discs.
     *
     * @return true if there is a vertical line, false otherwise.
     */
    private boolean checkVertical() {
        for (int i = 0; i < rows - 3; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] != ' ' && board[i][j] == board[i + 1][j] && board[i][j] == board[i + 2][j] && board[i][j] == board[i + 3][j]) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if there is a diagonal line of four matching discs.
     *
     * @return true if there is a diagonal line, false otherwise.
     */
    private boolean checkDiagonal() {
        for (int i = 0; i < rows - 3; i++) {
            for (int j = 0; j < cols - 3; j++) {
                if (board[i][j] != ' ' && board[i][j] == board[i + 1][j + 1] && board[i][j] == board[i + 2][j + 2] && board[i][j] == board[i + 3][j + 3]) {
                    return true;
                }
            }
        }
        for (int i = 3; i < rows; i++) {
            for (int j = 0; j < cols - 3; j++) {
                if (board[i][j] != ' ' && board[i][j] == board[i - 1][j + 1] && board[i][j] == board[i - 2][j + 2] && board[i][j] == board[i - 3][j + 3]) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns the current state of the game board.
     *
     * @return the game board.
     */
    public char[][] getBoard() {
        return board;
    }

    /**
     * Returns the name of the current player.
     *
     * @return the name of the current player.
     */
    public String getPlayer() {
        return player;
    }
}
