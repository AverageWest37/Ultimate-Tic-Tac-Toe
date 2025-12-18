package UltimateTicTacToe;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class BottomPanel extends Pane {
    private static Text playerText;
    private static Text winnerText;

    public BottomPanel(){
        this.setPrefSize(700, 80);
        this.setStyle("-fx-background-color: lightgray;");
        Button resetButton = new Button("Reset");
        resetButton.setFont(new Font(20));
        resetButton.setPrefSize(100, 50);

        resetButton.setOnAction(e -> UltTicTacToe.reset());

        //Binds button to center
        resetButton.layoutXProperty().bind(
                this.widthProperty().subtract(resetButton.widthProperty()).divide(2)
        );
        resetButton.layoutYProperty().bind(
                this.heightProperty().subtract(resetButton.heightProperty()).divide(2)
        );

        playerText = new Text("Current player: " + Brain.currentPlayer);
        playerText.setFont(new Font(20));

        //Binds player text to left
        playerText.layoutXProperty().bind(
                this.widthProperty().subtract(playerText.getLayoutBounds().getWidth()).divide(9)
        );
        playerText.layoutYProperty().bind(
                this.heightProperty().divide(2)
        );


        winnerText = new Text("Winner: n");
        winnerText.setFont(new Font(20));

        //Binds winner text to right side
        winnerText.layoutXProperty().bind(
                this.widthProperty().subtract(playerText.getLayoutBounds().getWidth()).divide(1.1)
        );
        winnerText.layoutYProperty().bind(
                this.heightProperty().divide(2)
        );

        this.getChildren().add(resetButton);
        this.getChildren().add(playerText);
        this.getChildren().add(winnerText);

    }


    //Getters to update the text
    public static Text getPlayerText() {
        return playerText;
    }

    public static Text getWinnerText() {
        return winnerText;
    }

}
