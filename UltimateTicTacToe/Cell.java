package UltimateTicTacToe;

import javafx.scene.control.Button;

public class Cell extends Button implements LetterCell {
    private char letter;


    //This class is just to allow a value, that is the letter on a cell, to be stored
    public Cell(){
        letter = 'n';
    }

    //INTERFACE METHODS, is a letter cell
    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }
}
