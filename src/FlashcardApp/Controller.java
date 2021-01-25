package FlashcardApp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

/*
This class controls the home scene of the app - "FlashcardApp" FXML file
 */


public class Controller implements Initializable {
    //Observable list to display deck list when app opens
    ObservableList<Deck> decks = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Deck, String> deckNameColumn;

    @FXML
    private TableView<Deck> deckList;

    //Initializes deckList table view w/ list of decks when app starts
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        deckNameColumn.setCellValueFactory(new PropertyValueFactory<Deck, String>("name"));

        decks = DataSource.getInstance().queryDecks();
        deckList.setItems(decks);

        //Set Selection mode for selecting which deck to study
        deckList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    // Changes the scene from main menu scene to 'create deck' scene when "Create Deck" is clicked on main menu.
    public void changeToCreateDeckScene(ActionEvent event) throws IOException {
        Parent createDeckParent = FXMLLoader.load(getClass().getResource("Resources/CreateDeck.fxml"));
        Scene createDeckScene = new Scene(createDeckParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(createDeckScene);
        window.show();
    }

    /*
    This method takes user selected deck and queries for deck ID, then queries Cards
    with matching ID and stores in Array list cards. Scene is changed to 'CardFront'.
    Cards list is passed to CardFrontController
     */
    public void studySelectedDeck(ActionEvent event) throws SQLException, IOException {

        // Get user-selected deck
        if (deckList.getSelectionModel().getSelectedItem() != null) {
            Deck selectedDeck = deckList.getSelectionModel().getSelectedItem();

            //Query selected deck
            int queriedDeckID = DataSource.getInstance().queryDeckByName(selectedDeck.getName());

            ArrayList<Card> cards = DataSource.getInstance().queryCards(queriedDeckID);
            DeckHolder.getDeckHolderInstance().setCards(cards);

            //Change scenes to Card Front
            Parent cardFrontParent = FXMLLoader.load(getClass().getResource("Resources/CardFront.fxml"));
            Scene cardFrontScene = new Scene(cardFrontParent);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(cardFrontScene);
            window.show();

        }
    }
}


