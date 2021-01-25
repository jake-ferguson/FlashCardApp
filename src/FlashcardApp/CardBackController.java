package FlashcardApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CardBackController implements Initializable {

    private ArrayList<Card> cardsToStudy;
    private int cardIndex;

    @FXML
    private TextArea answer;

    // Initializes the text field in the GUI w/answer of current card
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        answer.setEditable(false);
        cardsToStudy = DeckHolder.getDeckHolderInstance().getCards();
        cardIndex = DeckHolder.getDeckHolderInstance().getCardIndex();
        answer.setText(cardsToStudy.get(cardIndex).getAnswer());

    }

    //Method to flip to front of card
    public void flipCardToFront(ActionEvent event) throws IOException {
        Parent flipToCardFrontParent = FXMLLoader.load(getClass().getResource("Resources/CardFront.fxml"));
        Scene flipToCardFrontScene = new Scene(flipToCardFrontParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(flipToCardFrontScene);
        window.show();

    }

}
