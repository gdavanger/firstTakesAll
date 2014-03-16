package no.anderska.wta.questions;

import no.anderska.wta.game.Question;

import java.util.Random;

import static java.lang.Math.abs;

public class NextPrimeQuestionGenerator extends AbstractQuestionGenerator {
    public static final int NUMBER_OF_QUESTIONS = 10;
    public static final String DESCRIPTION = "Return the next prime. E.g. '8' => '11', '13' -> '17'";
    public static final long MIN_VALUE = 1L;
    public static final long MAX_VALUE = 1000000L;

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
        long value = nextLong(MIN_VALUE, MAX_VALUE);
        return new Question(String.valueOf(value), String.valueOf(getNextPrime(value)));
    }

    private long nextLong(long minValue, long maxValue) {
        return abs(random.nextLong()) % (maxValue - minValue) + minValue;
    }

    long getNextPrime(long value) {
        //noinspection StatementWithEmptyBody
        while (!isPrime(++value)) {
        }
        return value;
    }

    boolean isPrime(long value) {
        for (long i = 2; i * i <= value; i++) {
            if (value % i == 0) {
                return false;
            }
        }
        return true;
    }
}
