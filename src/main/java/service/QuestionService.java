package service;

import entity.Answer;
import entity.Question;
import repository.QuestionRepository;

import java.util.Optional;

public class QuestionService {
    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Question getQuestionByID(Long id) {
        return questionRepository.getQuestionByID(id).orElseThrow();
    }

    public Optional<Long> getAnswerToQuestion(long questionID, long answerID) {
        Question question = questionRepository.getQuestionByID(questionID).orElseThrow();
        return question.getAnswers().stream()
                .filter(e -> e.getId() == answerID)
                .map(Answer::getQuestionID)
                .findFirst();
    }
}
