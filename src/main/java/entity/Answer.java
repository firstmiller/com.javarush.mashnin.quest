package entity;

public class Answer {
    private final long id;
    private final String text;
    private final long questionID;

    public Answer(long id, String text, long questionID) {
        this.id = id;
        this.text = text;
        this.questionID = questionID;
    }

    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public long getQuestionID() {
        return questionID;
    }
}
