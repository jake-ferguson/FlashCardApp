package FlashcardApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class AddCardsController {

    @FXML
    private TextArea question;

    @FXML
    private TextArea answer;

    //add cards to deck previously created.
    public void addCardsToDeck(ActionEvent event) throws IOException, SQLException {

        //gets input from user to set the question and answer.
        String question = this.question.getText();
        String answer = this.answer.getText();

        //Create new card.
        Card card = new Card(question, answer);

//      Get deck id
        Deck deck = DeckHolder.getDeckHolderInstance().getDeck();
        int deckId = deck.getId();

        //Set Id for card. Get counter.
        card.setId(deckId);
        int cardId = card.getId();

        //Insert new card into Database.
        DataSource.getInstance().insertNewCard(question, answer, cardId);

        //After new card is created, reloads the "Add Card" scene.
        Parent addCardsParent = FXMLLoader.load(getClass().getResource("Resources/AddCards.fxml"));
        Scene addCardsScene = new Scene(addCardsParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(addCardsScene);
        window.show();
    }

    //Return to home screen
    public void returnToHome(ActionEvent event) throws IOException {

        Parent returnToHomeParent = FXMLLoader.load(getClass().getResource("Resources/FlashcardApp.fxml"));
        Scene returnToHomeScene = new Scene(returnToHomeParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(returnToHomeScene);
        window.show();
    }
}



