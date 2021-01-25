package FlashcardApp;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Resources/FlashcardApp.fxml"));
        primaryStage.setTitle("Flashcard App");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();

    }
    //Opens connection to the database. If connection fails, displays error message
    @Override
    public void init() throws Exception {
        super.init();
        DataSource.getInstance().open();
        if (!DataSource.getInstance().open()){
            System.out.println("FATAL ERROR: Couldn't Connect to the Database");
            Platform.exit();
        }
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        DataSource.getInstance().close();
    }

    public static void main(String[] args) {
        launch(args);

    }
}
