package chess;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.Border;

/**
 * Custom button type made so I can store additional information on a JButton.
 */
@SuppressWarnings("serial")
public class ChessSpaceButton extends JButton {
    /**
     * Create a new empty chess space button
     * @param row in the grid layout of buttons
     * @param column in the grid layout of buttons
     * @param backgroundColor the background color of this space.
     * @param listener the ActionListener that will receive this space's events.
     */
    public ChessSpaceButton(int row, int column, Color backgroundColor, ActionListener listener)
    {
        emptySpace = true;
        pieceColor = ChessPieceColor.NONE;
        unselectedBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.WHITE);
        selectedBorder = BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLACK);
        highlightedBorder = BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GREEN);
        this.row = row;
        this.column = column;

        setBackground(backgroundColor);
        setOpaque(true);
        setBorder(unselectedBorder);
        addActionListener(listener);
    }

    /**
     * Make this space have a chess piece based on the given image.
     * @param iconSource file name of the chess piece's image
     */
    public void setPiece(String iconSource)
    {
        setIcon(new ImageIcon(imageDirectory + iconSource));
        emptySpace = false;
        if (iconSource.startsWith("white"))
            pieceColor = ChessPieceColor.WHITE;
        else if (iconSource.startsWith("black"))
            pieceColor = ChessPieceColor.BLACK;
    }

    /**
     * Move one space's button properties to another space
     * @param newSpace the button that this space will move to.
     */
    public void movePiece(ChessSpaceButton newSpace)
    {
        newSpace.setIcon(getIcon());
        newSpace.setPieceColor(getPieceColor());
        newSpace.setSpaceOccupied();
        setEmptySpace();
    }

    /**
     * Mark this space as selected.
     */
    public void selectSpace()
    {
        setBorder(selectedBorder);
    }

    /**
     * Mark this space as unselected.
     */
    public void deselectSpace()
    {
        setBorder(unselectedBorder);
    }

    /**
     * Highlight this space. This is used to indicate valid moves.
     */
    public void highlightSpace()
    {
        setBorder(highlightedBorder);
    }

    /**
     * Determine if this chess space is empty.
     */
    public boolean isEmptySpace()
    {
        return emptySpace;
    }

    /**
     * Make this space empty.
     */
    public void setEmptySpace()
    {
        emptySpace = true;
        pieceColor = ChessPieceColor.NONE;
        super.setIcon(null);
    }

    /**
     * Mark this space as not empty.
     */
    public void setSpaceOccupied()
    {
        emptySpace = false;
    }

    /**
     * @return the color of the chess piece.
     */
    public ChessPieceColor getPieceColor()
    {
        return pieceColor;
    }

    /**
     * @param pieceColor the pieceColor to set
     */
    public void setPieceColor(ChessPieceColor pieceColor)
    {
        this.pieceColor = pieceColor;
    }

    /**
     * @return the row of this chess space in the grid layout of buttons.
     */
    public int getRow()
    {
        return row;
    }

    /**
     * @return the column of this chess space in the grid layout of buttons.
     */
    public int getColumn()
    {
        return column;
    }

    /**
     * Flag marking whether or not this space contains a chess piece.
     */
    private boolean emptySpace;

    /**
     * Color of the chess piece in this space.
     */
    private ChessPieceColor pieceColor;

    /**
     * The border style of an unselected space on the chess board.
     */
    private Border unselectedBorder;

    /**
     * The border style of a selected space on the chess board.
     */
    private Border selectedBorder;

    /**
     * The border style of a highlighted space on the chess board.
     */
    private Border highlightedBorder;

    /**
     * Row in the grid layout of buttons. Valid values 0 - 7.
     */
    private int row;

    /**
     * Column in the grid layout of buttons. Valid values 0 - 7.
     */
    private int column;

    /**
     * Path to the directory where the image files are stored.
     */
    private final static String imageDirectory = "src/chess/images/";
}
