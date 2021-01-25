package FlashcardApp;

/*
Class for the individual cards
 */

public class Card {

    private String question;
    private String answer;
    private int id;

    public Card(String question, String definition) {
        this.question = question;
        this.answer = definition;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}
