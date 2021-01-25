package FlashcardApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;

public class CreateDeckController {

    @FXML
    private TextField deckName;

    //Create new deck when "Next" is clicked from the Create New Deck Scene
    public void createNewDeck(ActionEvent event) throws IOException, SQLException {
        //Gets user input to create and name the deck.
        String deckName = this.deckName.getText();

        Deck deck = new Deck(deckName);

        // Checks to see if deck already exists. Displays error message if so.
        if (DataSource.getInstance().queryDeckByName(deckName) != -1) {

            Alert duplicateDeck = new Alert(Alert.AlertType.WARNING);
            duplicateDeck.setTitle("Duplicate deck name.");
            duplicateDeck.setHeaderText(null);
            duplicateDeck.setContentText("Deck already exists. Please choose another name.");
            duplicateDeck.show();

        }

        else {
            //Adds deck to database and sets the ID for the Deck
            deck.setId(DataSource.getInstance().insertNewDeck(deckName));

            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();

            try {
                // Passes newly created deck to DeckHolder class to pass to other controller
                DeckHolder deckHolder = DeckHolder.getDeckHolderInstance();
                deckHolder.setDeck(deck);

                //After new deck is created, loads the "Add Card" scene.
                Parent root = FXMLLoader.load(getClass().getResource("Resources/AddCards.fxml"));
                Scene addCardsScene = new Scene(root);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(addCardsScene);
                window.show();

            }

            catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }

        }
    }

    //Returns to the main menu when "Cancel" is click from any scene.
    public void returnToHome(ActionEvent event) throws IOException {
        Parent returnToHomeParent = FXMLLoader.load(getClass().getResource("Resources/FlashcardApp.fxml"));
        Scene returnToHomeScene = new Scene(returnToHomeParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(returnToHomeScene);
        window.show();
    }
}
