package UltimateTicTacToe;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Line;


public class Board extends Pane implements LetterCell {
    private final Node[][] cells;
    private char letter;

    //Board used to extend GridPane, but now it is a variable
    protected GridPane gridPane;

    public Board(){
        //Initializes the cells and some graphics
        cells = new Node[3][3];
        this.setStyle("-fx-background-color: black;");

        gridPane = new GridPane();
        this.getChildren().add(gridPane);
        gridPane.setVgap(3);
        gridPane.setHgap(3);

        //Value to determine who won the board
        letter = 'n';
    }

    //For the small boards
    public Board(int bigR, int bigC){
        //Initializes the cells, this is for the small Boards
        this();
        //Creates all the buttons to fill the cells array
        for (int c = 0; c < 3; c++){
            for (int r = 0; r < 3; r++){
                //Initialization
                Cell cell = new Cell();
                cell.setPrefSize(75, 75);

                //Makes the values to a variable for the on action
                int smallR = r;
                int smallC = c;
                //Will check if cell is available and set it to a player, update values and check for wins.
                //It needs the data of the button, where it is in the small board, and where it is in the big board.
                cell.setOnAction((ActionEvent e) ->
                        Brain.updateCell(cell, smallR, smallC, bigR, bigC)
                );


                //adds cells to the nodes array
                cells[r][c] = cell;
                //adds cells to the GridPane for graphics
                gridPane.add(cells[r][c], c, r);
            }
        }
    }

    //Finds if there is a winner, and if there is, initiate the graphics and return true
    public boolean checkWin(char lastPlaced){
        //for the location of the first and last winning letters, to make a line
        int fRow = -1;
        int fCol = -1;
        int lRow = -1;
        int lCol = -1;

        //Checks if all spots are taken up (cats)
        outer:
        for (int r = 0; r < 3; r++){
            for (int c = 0; c < 3; c++){
                if(((LetterCell)cells[r][c]).getLetter() == 'n'){
                    break outer;
                }
                if (r == 2 && c == 2){
                    letter = 'c';
                }
            }
        }

        //checks the horizontal wins
        for (int i = 0; i < 3; i++){
            if (((LetterCell)cells[i][0]).getLetter() == lastPlaced &&
                    ((LetterCell)cells[i][1]).getLetter() == lastPlaced &&
                    ((LetterCell)cells[i][2]).getLetter() == lastPlaced){
                fRow = i; fCol = 0; lRow = i; lCol = 2;
                letter = lastPlaced;
                break;
            }
        }
        //checks vertical wins
        for (int i = 0; i < 3; i++){
            if (((LetterCell)cells[0][i]).getLetter() == lastPlaced &&
                    ((LetterCell)cells[1][i]).getLetter() == lastPlaced &&
                    ((LetterCell)cells[2][i]).getLetter() == lastPlaced){
                fRow = 0; fCol = i; lRow = 2; lCol = i;
                letter = lastPlaced;
                break;
            }
        }
        //checks \ wins
        if (((LetterCell)cells[0][0]).getLetter() == lastPlaced &&
                ((LetterCell)cells[1][1]).getLetter() == lastPlaced &&
                ((LetterCell)cells[2][2]).getLetter() == lastPlaced){
            fRow = 0; fCol = 0; lRow = 2; lCol = 2;
            letter = lastPlaced;
        }
        //checks / wins
        if (((LetterCell)cells[2][0]).getLetter() == lastPlaced &&
                ((LetterCell)cells[1][1]).getLetter() == lastPlaced &&
                ((LetterCell)cells[0][2]).getLetter() == lastPlaced){
            fRow = 2; fCol = 0; lRow = 0; lCol = 2;
            letter = lastPlaced;
        }

        //Makes a line if there is a winner. WILL NEED A STACK PANE ANYWAY BECAUSE NEED TO PUT BIG O OR X

        if (letter != 'n'){
            //Adds the line and, if a small board, a big letter (called in the addLine)
            addWinGraphic(fRow, fCol, lRow, lCol);

            return true;
        }
        return false;
    }

    //Adds the line, as well as the large letter that goes on the board
    public void addWinGraphic(int fRow, int fCol, int lRow, int lCol){
        //Only makes a line if it's not cats, checks for -1 indicating cats
        if (fRow != -1){
            double cellSizeHalf = ((Region)cells[fRow][fCol]).getWidth() / 2;

            Line line = new Line(cells[fRow][fCol].getLayoutX() + cellSizeHalf, cells[fRow][fCol].getLayoutY() + cellSizeHalf,
                    cells[lRow][lCol].getLayoutX() + cellSizeHalf, cells[lRow][lCol].getLayoutY() + cellSizeHalf);

            line.setStrokeWidth(8);

            this.getChildren().add(line);
        }

        //Adds the letter
        ImageView largeLetter;
        if (letter == 'x'){
            largeLetter = new ImageView(new Image("file:UltTicTacToeImages/UltimateXTP.png"));
        }else if (letter == 'o'){
            largeLetter = new ImageView(new Image("file:UltTicTacToeImages/UltimateOTP.png"));
        }else{
            largeLetter = new ImageView(new Image("file:UltTicTacToeImages/UltimateCTP.png"));
        }
        largeLetter.fitHeightProperty().bind(this.heightProperty());
        largeLetter.fitWidthProperty().bind(this.widthProperty());

        this.getChildren().add(largeLetter);


    }

    //returns the nodes (cells or boards) contained in the array
    public Node[][] getCells(){
        return cells;
    }

    //INTERFACE METHODS, is a letter cell
    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

}
