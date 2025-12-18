package UltimateTicTacToe;

//This interface is for nodes (cells and boards) that are checked for their letter, when checking for a win in a small or big board
public interface LetterCell {
    char getLetter();
    void setLetter(char letter);

}
