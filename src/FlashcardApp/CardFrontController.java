package FlashcardApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class CardFrontController implements Initializable {
    // List to store queried cards and card index
    private ArrayList<Card> cardsToStudy;
    private int cardIndex;

    @FXML
    private TextArea question;

//Initialize text field in GUI w/ question from current card
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        question.setEditable(false);
        cardsToStudy = DeckHolder.getDeckHolderInstance().getCards();
        cardIndex = DeckHolder.getDeckHolderInstance().getCardIndex();
        question.setText(cardsToStudy.get(cardIndex).getQuestion());

    }

    //Method to view next card
    public void nextCard(){

        if (!cardsToStudy.isEmpty()){
            //If card index is greater than # of cards, reset card index to 0
            if ((cardIndex +1 ) >= cardsToStudy.size()){

                cardIndex=0;
            }

            else {
                //Otherwise, increase card index
                cardIndex+=1;
            }
            //Set the text field in GUI to show question from current card
            question.setText(cardsToStudy.get(cardIndex).getQuestion());
            DeckHolder.getDeckHolderInstance().setCardIndex(cardIndex);
        }

        else{

            System.out.println("This deck has no cards to study.");
        }
    }
    // Method to view previous card
    public void previousCard(){

        if (!cardsToStudy.isEmpty()){

            // If index is <0, reset index to last card in the list
            if (cardIndex -1 < 0){

                cardIndex = cardsToStudy.size()-1;

            }

            else {

                cardIndex-=1;
            }
            // Sets text field on GUI to show question
            question.setText(cardsToStudy.get(cardIndex).getQuestion());
            DeckHolder.getDeckHolderInstance().setCardIndex(cardIndex);

        }

        else {

            System.out.println("There are no cards to study.");
        }
    }

    //Method to flip to back of card
    public void flipCardToBack(ActionEvent event) throws IOException {
        Parent flipToCardBackParent = FXMLLoader.load(getClass().getResource("Resources/CardBack.fxml"));
        Scene flipToCardBackScene = new Scene(flipToCardBackParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(flipToCardBackScene);
        window.show();
    }

    //Returns to the main menu
    public void returnToHome(ActionEvent event) throws IOException {
        Parent returnToHomeParent = FXMLLoader.load(getClass().getResource("Resources/FlashcardApp.fxml"));
        Scene returnToHomeScene = new Scene(returnToHomeParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(returnToHomeScene);
        window.show();
    }
}
