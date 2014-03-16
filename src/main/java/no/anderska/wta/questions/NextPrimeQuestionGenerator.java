package no.anderska.wta.questions;

import no.anderska.wta.game.Question;

import java.util.Random;

public class NextPrimeQuestionGenerator extends AbstractQuestionGenerator {
    public static final int NUMBER_OF_QUESTIONS = 10;
    public static final String DESCRIPTION = "Return the next prime. E.g. '8' => '11', '13' -> '17'";
    public static final int MIN_VALUE = 1;
    public static final int MAX_VALUE = 1000000;

    private final Random random;

    public NextPrimeQuestionGenerator() {
        this(new Random());
    }

    NextPrimeQuestionGenerator(Random random) {
        super(NUMBER_OF_QUESTIONS, DESCRIPTION);
        this.random = random;
    }

    @Override
    protected Question createQuestion() {
        int value = random.nextInt(MAX_VALUE - MIN_VALUE) + MIN_VALUE;
        return new Question(String.valueOf(value), String.valueOf(getNextPrime(value)));
    }

    private int getNextPrime(int value) {
        //noinspection StatementWithEmptyBody
        while (!isPrime(++value)) {
        }
        return value;
    }

    private boolean isPrime(int value) {
        for (int i = 2; i * i <= value; i++) {
            if (value % i == 0) {
                return false;
            }
        }
        return true;
    }
}
