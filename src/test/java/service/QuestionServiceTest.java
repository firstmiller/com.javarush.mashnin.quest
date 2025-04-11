package service;

import entity.Question;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import repository.QuestionRepository;

import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Optional;

class QuestionServiceTest {
    private QuestionService questionService;
    private QuestionRepository questionRepository;
    private Question question;

    @BeforeEach
    void init() {
        questionRepository = Mockito.mock(QuestionRepository.class);
        questionService = new QuestionService(questionRepository);
        question = Mockito.mock(Question.class);
    }

    @Test
    void throwExceptionIfNoFoundId() {
        Mockito.when(questionRepository.getQuestionByID(Mockito.anyLong())).thenReturn(Optional.empty());
        Assertions.assertThrows(NoSuchElementException.class, () -> questionService.getQuestionByID(Mockito.anyLong()));
    }

    @Test
    void answerToQuestionNotFound() {
        Mockito.when(question.getId()).thenReturn(10L);
        Mockito.when(question.getAnswers()).thenReturn(Collections.emptyList());
        Mockito.when(question.getText()).thenReturn("");

        Mockito.when(questionRepository.getQuestionByID(Mockito.anyLong())).thenReturn(Optional.of(question));

        Assertions.assertEquals(Optional.empty(), questionService.getAnswerToQuestion(1, -4));
    }
}