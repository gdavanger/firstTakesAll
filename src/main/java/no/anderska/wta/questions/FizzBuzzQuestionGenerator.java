package no.anderska.wta.questions;

import no.anderska.wta.game.Question;

import java.util.Random;

public class FizzBuzzQuestionGenerator extends AbstractQuestionGenerator {
    public static final int NUMBER_OF_QUESTIONS = 20;
    public static final String DESCRIPTION = "The answer to numbers divisible by 3 is Fizz, divisible by 5 is Buzz, divisible by 3 and 3 is FizzBuzz. E.g. '4' -> '4', '6' -> 'Fizz', '30' -> 'FizzBuzz";
    public static final int MIN_VALUE = 1;
    public static final int MAX_VALUE = 999;

    private final Random random;

    public FizzBuzzQuestionGenerator() {
        this(new Random());
    }

    FizzBuzzQuestionGenerator(Random random) {
        super(NUMBER_OF_QUESTIONS, DESCRIPTION);
        this.random = random;
    }

    @Override
    protected Question createQuestion() {
        int number = MIN_VALUE + random.nextInt(MAX_VALUE - MIN_VALUE);
        return new Question(String.valueOf(number), solveFizzBuzz(number));
    }

    String solveFizzBuzz(int number) {
        StringBuilder sb = new StringBuilder();
        if (number % 3 == 0) {
            sb.append("Fizz");
        }
        if (number % 5 == 0) {
            sb.append("Buzz");
        }
        if (sb.length() != 0) {
            return sb.toString();
        }
        else {
            return String.valueOf(number);
        }
    }
}
