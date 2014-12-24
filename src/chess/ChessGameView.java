package chess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * View Class for the chess board.
 */
public class ChessGameView {
    /**
     * Create a new gui for the chess program.
     */
    public ChessGameView(ChessController gameController)
    {
        myController = gameController;
        gameWindow = new JFrame("Chess Program");
        gameWindow.setSize(windowWidth, windowHeight);
        startNewGame();
        setUpMenu(gameWindow);
        gameWindow.setVisible(true);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Set the board position to what it should be in a new game.
     */
    public void startNewGame()
    {
        JPanel totalLayoutPanel = new JPanel(new BorderLayout());
        statusLabel = new JLabel("White's turn.");
        totalLayoutPanel.add(statusLabel, BorderLayout.PAGE_START);

        // make the chess board's panel
        JPanel boardPanel = new JPanel();
        boardPanel.setPreferredSize(new Dimension(windowWidth, windowHeight));
        boardPanel.setLayout(new GridLayout(chessBoardRows, chessBoardColumns));
        buttonCollection = new ChessSpaceButton[chessBoardRows][chessBoardColumns];
        addButtons(boardPanel);
        totalLayoutPanel.add(boardPanel, BorderLayout.CENTER);

        gameWindow.setContentPane(totalLayoutPanel);
        gameWindow.setVisible(true);
    }

    /**
     * Move the graphical representation of a chess piece to another space.
     */
    public void moveChessPiece(int oldRow, int oldColumn, int newRow, int newColumn)
    {
        buttonCollection[oldRow][oldColumn].movePiece(buttonCollection[newRow][newColumn]);
    }

    /**
     * @param message to display in the status area
     */
    public void setStatusLabel(String message)
    {
        statusLabel.setText(message);
    }

    /**
     * Get the chess space at the given row and column in the view.
     * @param row of the chess space (0-7)
     * @param column of the chess space (0-7)
     * @return the chess space
     */
    public ChessSpaceButton getSpace(int row, int column)
    {
        if (!( (0 <= row && row <= 7) && (0 <= column && column <= 7) )) {
            throw new OffTheChessBoardException("row: " + row + " col: " + column);
        }
        return buttonCollection[row][column];
    }

    /**
     * Create the game's menu.
     * @param window to put the menu in.
     */
    private void setUpMenu(JFrame window)
    {
        JMenuBar menubar = new JMenuBar();
        JMenu file = new JMenu("File");
        // New Game menu item
        JMenuItem newGame = new JMenuItem("New Game");
        newGame.addActionListener(myController);
        file.add(newGame);
        // Close menu item
        JMenuItem close = new JMenuItem("Close");
        close.addActionListener(myController);
        file.add(close);
        menubar.add(file);
        window.setJMenuBar(menubar);
    }

    /**
     * Add all the buttons representing the squares and pieces on the chess board.
     * @param boardPanel
     */
    private void addButtons(JPanel boardPanel)
    {
        Color backgroundColor;
        for (int row = 0; row < chessBoardRows; ++row) {
            // initial color for this row
            if (row % 2 == 0)
                backgroundColor = lightSpace;
            else
                backgroundColor = darkSpace;
            for (int column = 0; column < chessBoardColumns; ++column) {
                ChessSpaceButton b = new ChessSpaceButton(row, column, backgroundColor, myController);
                boardPanel.add(b);
                buttonCollection[row][column] = b;
                backgroundColor = nextColor(backgroundColor);
            }
        }
        setUpNewGameButtonIcons();
    }

    /**
     * Set the images to the hardcoded default positions.
     */
    private void setUpNewGameButtonIcons()
    {
        // set up pawns
        for (int i = 0; i < chessBoardColumns; ++i) {
            buttonCollection[1][i].setPiece("blackPawn.png");
            buttonCollection[6][i].setPiece("whitePawn.png");
        }
        // set up rooks
        buttonCollection[0][0].setPiece("blackRook.png");
        buttonCollection[0][7].setPiece("blackRook.png");
        buttonCollection[7][0].setPiece("whiteRook.png");
        buttonCollection[7][7].setPiece("whiteRook.png");
        // set up knights
        buttonCollection[0][1].setPiece("blackKnight.png");
        buttonCollection[0][6].setPiece("blackKnight.png");
        buttonCollection[7][1].setPiece("whiteKnight.png");
        buttonCollection[7][6].setPiece("whiteKnight.png");
        // set up bishops
        buttonCollection[0][2].setPiece("blackBishop.png");
        buttonCollection[0][5].setPiece("blackBishop.png");
        buttonCollection[7][2].setPiece("whiteBishop.png");
        buttonCollection[7][5].setPiece("whiteBishop.png");
        // set up queens
        buttonCollection[0][3].setPiece("blackQueen.png");
        buttonCollection[7][3].setPiece("whiteQueen.png");
        // set up kings
        buttonCollection[0][4].setPiece("blackKing.png");
        buttonCollection[7][4].setPiece("whiteKing.png");
    }

    /**
     * Helper function to allow swapping back and forth between black and white.
     * @param c the current color
     * @return lightSpace if input is darkSpace, darkSpace if input is lightSpace
     */
    private Color nextColor(Color c)
    {
        if (c == darkSpace)
            return lightSpace;
        else if (c == lightSpace)
            return darkSpace;
        return lightSpace;
    }

    /**
     * Reference to the controller that handles the game loop.
     */
    protected ChessController myController;

    /**
     * Default height of the game window.
     */
    private final static int windowHeight = 700;

    /**
     * Default width of the game window.
     */
    private final static int windowWidth = 700;

    /**
     * Number of rows on a chess board.
     */
    private final static int chessBoardRows = 8;

    /**
     * Number of columns on a chess board.
     */
    private final static int chessBoardColumns = 8;

    /**
     * Color of the lighter spaces on the chess board.
     */
    private final static Color lightSpace = new Color(1.0f, 0.808f, 0.62f);

    /**
     * Color of the darker spaces on the chess board.
     */
    private final static Color darkSpace = new Color(0.82f, 0.545f, 0.278f);

    /**
     * Collection holding references to all the button objects representing spaces
     * on a chess board.
     */
    private ChessSpaceButton buttonCollection[][];

    /**
     * The window for this game.
     */
    private JFrame gameWindow;

    /**
     * Gives status information about the currently running game.
     */
    private JLabel statusLabel;

}
