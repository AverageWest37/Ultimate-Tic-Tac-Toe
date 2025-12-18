package UltimateTicTacToe;

import javafx.scene.layout.Region;
import javafx.scene.shape.Line;

public class BigBoard extends Board{

    //For the big board, make extended class?
    public BigBoard(){
        super();
        gridPane.setVgap(6);
        gridPane.setHgap(6);

        //Initializes all the smaller boards and puts them into the bigger board cells
        for (int r = 0; r < 3; r++){
            for (int c = 0; c < 3; c++){

                //Makes a board
                Board tempBoard = new Board(r, c);
                //Adds boards to cell array
                super.getCells()[r][c] = tempBoard;
                //Adds boards to gridPane
                gridPane.add(tempBoard, c, r);
            }
        }
    }
    @Override
    //Override in order to not make a large letter to cover the whole board, and to increase stroke width
    public void addWinGraphic(int fRow, int fCol, int lRow, int lCol){
        if (fRow != -1) {
            double cellSizeHalf = ((Region) super.getCells()[fRow][fCol]).getWidth() / 2;

            Line line = new Line(super.getCells()[fRow][fCol].getLayoutX() + cellSizeHalf, super.getCells()[fRow][fCol].getLayoutY() + cellSizeHalf,
                    super.getCells()[lRow][lCol].getLayoutX() + cellSizeHalf, super.getCells()[lRow][lCol].getLayoutY() + cellSizeHalf);

            line.setStrokeWidth(16);

            this.getChildren().add(line);
        }
    }
}
