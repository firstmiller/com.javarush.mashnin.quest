package repository;

import entity.Answer;
import entity.Question;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONTokener;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class QuestionRepository {
    private final List<Question> questions = new ArrayList<>();

    private void initRepository() {
        InputStream inputStream = getClass().getResourceAsStream("/data/quest.json");
        JSONArray jsonArray = new JSONArray(new JSONTokener(inputStream));
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject questionJSON = jsonArray.getJSONObject(i);
            long questionID = questionJSON.getLong("id");
            String questionText = questionJSON.getString("text");

            JSONArray answersArray = questionJSON.getJSONArray("answers");
            List<Answer> answerList = new ArrayList<>();
            for (int j = 0; j < answersArray.length(); j++) {
                JSONObject answerJSON = answersArray.getJSONObject(j);
                long answerID = answerJSON.getLong("id");
                String text = answerJSON.getString("text");
                long nextQuestionID = answerJSON.getLong("nextQuestion");
                answerList.add(new Answer(answerID, text, nextQuestionID));
            }
            questions.add(new Question(questionID, questionText, answerList));
        }
    }

    public QuestionRepository() {
        initRepository();
    }

    public Optional<Question> getQuestionByID(long id) {
        return questions.stream()
                .filter(e -> e.getId() == id)
                .findFirst();
    }
}
