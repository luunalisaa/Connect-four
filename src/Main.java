/**
 * The main class to launch the Connect Four game.
 * It initializes the game and starts the GUI.
 */
public class Main {
    public static void main(String[] args) {
        ConnectFourGui gui = new ConnectFourGui();
        int[] dimensions = gui.chooseDimensions(); // Let the user choose dimensions
        ConnectFour game = new ConnectFour(null, dimensions[0], dimensions[1], "Player 1");
        gui.drawBoard(dimensions);
        gui.addListeners(game);
    }
}
