package UltimateTicTacToe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class UltTicTacToe extends Application {
    private static BorderPane mainPane;
    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("Program Start");

        //Main pane for the grids and button below
        mainPane = new BorderPane();

        //The controller for the game. Handles making the board, as well as the logic
        Brain brain = new Brain();

        //Sets the board to the main pane
        mainPane.setCenter(brain.getBigBoard());

        //Bottom panel for the game, for reset button and info
        BottomPanel bottomPanel = new BottomPanel();

        //Sets the panel to the bottom
        mainPane.setBottom(bottomPanel);

        //Initializes the stage
        Scene scene = new Scene(mainPane);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        //Files and rules
        File rules = null;
        try{
            rules = new File("UltTicTacToeImages/UltimateTicTacToeRules.txt");
        }catch(Exception e){
            System.out.println("Cannot find rules file");
        }

        Scanner scan = new Scanner(rules);

        String ruleString = "";
        while(scan.hasNext()){
            ruleString += scan.nextLine();
            ruleString += "\n";
        }
        Text ruleText = new Text(ruleString);
        ruleText.setLayoutY(20);

        Pane rulePane = new Pane(ruleText);

        Scene ruleScene = new Scene(rulePane);
        Stage ruleStage = new Stage();
        ruleStage.setScene(ruleScene);
        ruleStage.setResizable(false);
        ruleStage.show();
    }

    //Method for the reset button. Essentially just remakes a new brain and bottom panel, acting like you opened a new window
    public static void reset(){
        //The controller for the game. Handles making the board, as well as the logic
        Brain brain = new Brain();

        //Sets the board to the main pane
        mainPane.setCenter(brain.getBigBoard());

        //Bottom panel for the game, for reset button and info
        BottomPanel bottomPanel = new BottomPanel();

        //Sets the panel to the bottom
        mainPane.setBottom(bottomPanel);
    }
}
