package UltimateTicTacToe;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Arrays;

//NOTE: This class could be integrated into the BigBoard, probably. CONSIDER?????????
public class Brain {

    private static BigBoard bigBoard;
    protected static char currentPlayer;
    private static boolean[][] activeBigCells;


    public Brain(){
        currentPlayer = 'x';
        activeBigCells = new boolean[3][3];
        Arrays.fill(activeBigCells[0], true);
        Arrays.fill(activeBigCells[1], true);
        Arrays.fill(activeBigCells[2], true);

        bigBoard = new BigBoard();
    }


    public Board getBigBoard(){
        return bigBoard;
    }

    //Methods called after a button press. WIll update the cell if it is empty and call the appropriate methods, as well as add an image to the cell you clicked
    public static void updateCell(Cell cell, int smallR, int smallC, int bigR, int bigC){
        if (cell.getLetter() == 'n' && activeBigCells[bigR][bigC]){

            ImageView shape;
            cell.setLetter(currentPlayer);
            char tempPlayer = currentPlayer;

            //Switches the player and adds the graphic to the cell
            if (currentPlayer == 'x'){
                shape = new ImageView(new Image("file:UltTicTacToeImages/UltimateXTP.png"));
                currentPlayer = 'o';
            }else{
                shape = new ImageView(new Image("file:UltTicTacToeImages/UltimateOTP.png"));
                currentPlayer = 'x';
            }

            BottomPanel.getPlayerText().setText("Current player: " + currentPlayer);

            shape.setPreserveRatio(true);
            shape.setFitWidth(55);
            shape.setFitHeight(55);
            cell.setGraphic(shape);

            //Checks if there is a small win, and if there is, check the bigBoard for wins. If there is a big winner, set current player letter (X, O, C).
            //Also updates the values in the board
            boolean smallWin = ((Board)bigBoard.getCells()[bigR][bigC]).checkWin(tempPlayer);

            if (smallWin && bigBoard.checkWin(tempPlayer)){
                currentPlayer = bigBoard.getLetter();
                BottomPanel.getWinnerText().setText("Winner: " + currentPlayer);
            }


            //Switches the active big cells to inhibit clicking in the wrong square, and changes the graphic to see
            // the active big cells
            Arrays.fill(activeBigCells[0], false);
            Arrays.fill(activeBigCells[1], false);
            Arrays.fill(activeBigCells[2], false);

            //checks if there is a win, then does logic of checking if there is a letter and logic for if there isn't
            if (bigBoard.getLetter() == 'n'){
                if (((Board)bigBoard.getCells()[smallR][smallC]).getLetter() != 'n'){
                    for (int r = 0; r < 3; r++){
                        for (int c = 0; c < 3; c++){
                            if (((Board)bigBoard.getCells()[r][c]).getLetter() == 'n'){
                                activeBigCells[r][c] = true;
                            }
                        }
                    }
                }else{
                    activeBigCells[smallR][smallC] = true;
                }
            }

            //Updates the background graphic color to indicate the available cells
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    if (activeBigCells[r][c]){
                        bigBoard.getCells()[r][c].setStyle("-fx-background-color: magenta;");
                    }else{
                        bigBoard.getCells()[r][c].setStyle("-fx-background-color: black;");
                    }

                }
            }

        }
    }


}
