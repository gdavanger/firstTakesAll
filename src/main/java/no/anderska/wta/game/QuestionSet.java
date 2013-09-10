package no.anderska.wta.game;

import no.anderska.wta.AnswerStatus;
import org.joda.time.DateTime;

import java.util.List;

public class QuestionSet {
    private List<Question> questions;
    private QuestionGenerator engine;
    private String categoryName;
    private DateTime limit;

    public QuestionSet(List<Question> questions, QuestionGenerator engine, String categoryName) {
        this.questions = questions;
        this.engine = engine;
        this.categoryName = categoryName;
        this.limit = new DateTime().plusSeconds(8);
    }

    public AnswerStatus validateAnswer(List<String> answers) {
        DateTime answered = new DateTime();

        if (answers == null) {
            return AnswerStatus.ERROR;
        }
        if (questions.size() != answers.size()) {
            return AnswerStatus.WRONG;
        }
        for (int i=0;i<answers.size();i++) {
            if (!questions.get(i).isCorrect(answers.get(i))) {
                return AnswerStatus.WRONG;
            }
        }
        if (answered.isAfter(limit)) {
            return AnswerStatus.LATE;
        }
        return AnswerStatus.OK;
    }

    public QuestionGenerator getEngine() {
        return engine;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
