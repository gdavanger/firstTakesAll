package no.anderska.wta.game;

import no.anderska.wta.AnswerStatus;
import no.anderska.wta.GameHandlerPlayerInterface;
import no.anderska.wta.QuestionList;
import no.anderska.wta.StatusGiver;
import no.anderska.wta.dto.CategoryDTO;
import no.anderska.wta.servlet.PlayerHandler;

import java.util.*;

public class GameHandler implements GameHandlerPlayerInterface, StatusGiver {
    private PlayerHandler playerHandler;
    private Map<String,Engine> engines;
    private Map<String,QuestionSet> askedQuestions = new HashMap<>();
    private Map<String,String> takenCategories = new HashMap<>();

    @Override
    public AnswerStatus answer(String playerid, List<String> answers) {
        QuestionSet questionSet = askedQuestions.get(playerid);
        if (questionSet == null) {
            return AnswerStatus.ERROR;
        }
        AnswerStatus answerStatus = questionSet.validateAnswer(answers);
        if (answerStatus == AnswerStatus.OK) {
            boolean gotpoint = claimCategory(questionSet.getCategoryName(),playerid);
            if (gotpoint) {
                playerHandler.addPoints(playerid,questionSet.getEngine().points());
            }
        }
        return answerStatus;
    }

    private synchronized boolean claimCategory(String categoryName,String playerid) {
        if (takenCategories.containsKey(categoryName)) {
            return false;
        }
        takenCategories.put(categoryName,playerid);
        return true;
    }

    @Override
    public QuestionList questions(String playerid, String categoryid) {
        if (!playerHandler.playerPlaying(playerid)) {
            return QuestionList.error("Unknown player '" + playerid + "'");
        }
        Engine engine = engines.get(categoryid);
        if (engine == null) {
            return QuestionList.error("Unknown category '" + categoryid + "'");
        }
        List<Question> questions = engine.generateQuestions(playerid);
        List<String> questionList = new ArrayList<>();

        for (Question question : questions) {
            questionList.add(question.getQuestion());
        }

        askedQuestions.put(playerid,new QuestionSet(questions,engine, categoryid));

        return QuestionList.create(questionList);
    }

    public void setPlayerHandler(PlayerHandler playerHandler) {
        this.playerHandler = playerHandler;
    }

    public void setEngines(Map<String, Engine> engines) {
        this.engines = engines;
    }

    public void setAskedQuestions(Map<String, QuestionSet> askedQuestions) {
        this.askedQuestions = askedQuestions;
    }

    @Override
    public List<CategoryDTO> catergoryStatus() {
        List<CategoryDTO> result = new ArrayList<>();
        for (Map.Entry<String, Engine> entry : engines.entrySet()) {

            String answeredById = takenCategories.get(entry.getKey());
            String answeredBy = answeredById != null ? playerHandler.playerName(answeredById) : null;
            CategoryDTO categoryDTO = new CategoryDTO(entry.getKey(), entry.getValue().description(), entry.getValue().points(), answeredBy);
            result.add(categoryDTO);
        }
        return result;
    }
}
