package chess.test;

import static chess.File.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import chess.Bishop;
import chess.ChessPieceColor;

/**
 * Tests for the {@link Bishop} class.
 */
public class BishopTest {

    /**
     * Check that Bishops created in the correct starting positions have the
     * right properties.
     */
    @Test
    public void initializeWithValidInputs()
    {
        Bishop whiteLeft = new Bishop(1, 3, ChessPieceColor.WHITE);
        assertTrue(whiteLeft.atValidStartingPosition());
        assertEquals(1, whiteLeft.getRow());
        assertEquals(3, whiteLeft.getColumn());
        assertEquals(ChessPieceColor.WHITE, whiteLeft.getColor());

        Bishop whiteRight = new Bishop(1, 6, ChessPieceColor.WHITE);
        assertTrue(whiteRight.atValidStartingPosition());
        assertEquals(f, whiteRight.getFile());
        assertEquals(1, whiteRight.getRank());

        Bishop blackLeft = new Bishop(8, 3, ChessPieceColor.BLACK);
        assertTrue(blackLeft.atValidStartingPosition());
        assertEquals(c, blackLeft.getFile());
        assertEquals(8, blackLeft.getRank());
        assertEquals(ChessPieceColor.BLACK, blackLeft.getColor());

        Bishop blackRight = new Bishop(f, 8, ChessPieceColor.BLACK);
        assertTrue(blackRight.atValidStartingPosition());
        assertEquals(8, blackRight.getRow());
        assertEquals(6, blackRight.getColumn());
        assertEquals(ChessPieceColor.BLACK, blackRight.getColor());
    }

    /**
     * Swap the positions of the white and black Bishops and confirm that they
     * aren't in valid starting positions.
     */
    @Test
    public void initializeWithWhiteAndBlackBishopSwapped()
    {
        Bishop whiteInLeftBlackSpot = new Bishop(8, 3, ChessPieceColor.WHITE);
        assertFalse(whiteInLeftBlackSpot.atValidStartingPosition());

        Bishop whiteInRightBlackSpot = new Bishop(f, 8, ChessPieceColor.WHITE);
        assertFalse(whiteInRightBlackSpot.atValidStartingPosition());

        Bishop blackInLeftWhiteSpot = new Bishop(c, 1, ChessPieceColor.BLACK);
        assertFalse(blackInLeftWhiteSpot.atValidStartingPosition());

        Bishop blackInRightWhiteSpot = new Bishop(1, 6, ChessPieceColor.BLACK);
        assertFalse(blackInRightWhiteSpot.atValidStartingPosition());
    }

    /**
     * Confirm that Bishops placed in the correct row but wrong column do not
     * have valid starting positions.
     */
    @Test
    public void initializeWithRightRowWrongColumn() {
        Bishop wrongColumnWhite = new Bishop(1, 1, ChessPieceColor.WHITE);
        assertFalse(wrongColumnWhite.atValidStartingPosition());

        Bishop wrongColumnBlack = new Bishop(8, 8, ChessPieceColor.BLACK);
        assertFalse(wrongColumnBlack.atValidStartingPosition());

        Bishop wrongColumnAlgWhite = new Bishop(b, 1, ChessPieceColor.WHITE);
        assertFalse(wrongColumnAlgWhite.atValidStartingPosition());

        Bishop wrongColumnAlgBlack = new Bishop(g, 8, ChessPieceColor.BLACK);
        assertFalse(wrongColumnAlgBlack.atValidStartingPosition());
    }

    /**
     * Confirm that Bishops placed far from their normal place do not have valid
     * starting positions.
     */
    @Test
    public void initializeWithCompletelyWrongLocation()
    {
        Bishop offTheBoard = new Bishop(4, 9, ChessPieceColor.WHITE);
        assertFalse(offTheBoard.atValidStartingPosition());

        Bishop middleOfTheBoard = new Bishop(6, 7, ChessPieceColor.BLACK);
        assertFalse(middleOfTheBoard.atValidStartingPosition());

        Bishop upperRightOfBoard = new Bishop(h, 7, ChessPieceColor.BLACK);
        assertFalse(upperRightOfBoard.atValidStartingPosition());

        Bishop leftMiddleOfBoard = new Bishop(a, 5, ChessPieceColor.WHITE);
        assertFalse(leftMiddleOfBoard.atValidStartingPosition());
    }

    /**
     * Confirm that diagonal moves made from the left white Bishop's starting
     * position are properly recognized as valid/invalid.
     */
    @Test
    public void checkForValidMovesAtLeftWhiteBishopStart()
    {
        Bishop leftWhite = new Bishop(1, 3, ChessPieceColor.WHITE);
        // Move to self
        assertFalse(leftWhite.isValidMove(1, 3));
        // Go up-right one
        assertTrue(leftWhite.isValidMove(2, 4));
        // Go up-right three
        assertTrue(leftWhite.isValidMove(f, 4));
        // Go up-right all the way
        assertTrue(leftWhite.isValidMove(6, 8));
        // Go up-left one
        assertTrue(leftWhite.isValidMove(b, 2));
        // Go up-left all the way
        assertTrue(leftWhite.isValidMove(3, 1));
        // Go down-right off the edge
        assertFalse(leftWhite.isValidMove(-1, 5));
    }

    /**
     * Confirm that diagonal moves made from the middle of the board are
     * properly recognized as valid/invalid.
     */
    @Test
    public void checkForValidMovesInMiddleOfBoard()
    {
        Bishop middle = new Bishop(5, 4, ChessPieceColor.BLACK);
        // go up-left all the way
        assertTrue(middle.isValidMove(8, 1));
        // go up-right all the way
        assertTrue(middle.isValidMove(8, 7));
        // go down-left all the way
        assertTrue(middle.isValidMove(2, 1));
        // go down-right all the way
        assertTrue(middle.isValidMove(1, 8));
        // go up-right too far
        assertFalse(middle.isValidMove(9, 8));
    }

    /**
     * Confirm that non-diagonal moves are recognized as invalid.
     */
    @Test
    public void checkCompletelyWrongMovement()
    {
        Bishop wrong = new Bishop(3, 3, ChessPieceColor.BLACK);
        // move like rook right
        assertFalse(wrong.isValidMove(3, 6));
        // move like rook up
        assertFalse(wrong.isValidMove(6, 3));
        // move like pawn down
        assertFalse(wrong.isValidMove(2, 3));
        // totally random
        assertFalse(wrong.isValidMove(4, 8));
        // move to upper left corner
        assertFalse(wrong.isValidMove(a, 8));
        // move like a knight
        assertFalse(wrong.isValidMove(e, 4));
        // move like a knight
        assertFalse(wrong.isValidMove(a, 2));
        // move to lower right corner
        assertFalse(wrong.isValidMove(h, 1));
        // move to right bishop's spot
        assertFalse(wrong.isValidMove(f, 1));
    }

    /**
     * Walk the right white bishop around the board from its starting position.
     * Confirm that valid moves succeed, invalid moves fail, and the state is
     * updated correctly.
     */
    @Test
    public void walkFromRightWhiteBishop()
    {
        Bishop traveler = new Bishop(f, 1, ChessPieceColor.WHITE);
        // move up-right to edge
        assertTrue(traveler.move(h, 3));
        // move up-left to edge
        assertTrue(traveler.move(c, 8));
        // move down-left to edge
        assertTrue(traveler.move(a, 6));
        assertEquals(6, traveler.getRow());
        assertEquals(1, traveler.getColumn());
        // bad move like rook
        assertFalse(traveler.move(f, 6));
        // move down-right two
        assertTrue(traveler.move(c, 4));
        // bad move like knight up-right
        assertFalse(traveler.move(d, 6));
        // move down-left one
        assertTrue(traveler.move(b, 3));
        // move down-left one
        assertTrue(traveler.move(a, 2));
        // bad move up like king
        assertFalse(traveler.move(a, 3));
        assertEquals(a, traveler.getFile());
        assertEquals(2, traveler.getRank());
        // move up-right three
        assertTrue(traveler.move(d, 5));
    }
}
