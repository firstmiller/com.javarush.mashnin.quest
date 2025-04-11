package entity;

import java.util.List;

public class Question {
    private final long id;
    private final String text;
    private final List<Answer> answers;

    public Question(long id, String text, List<Answer> answers) {
        this.id = id;
        this.text = text;
        this.answers = answers;
    }

    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public List<Answer> getAnswers() {
        return answers;
    }
}
