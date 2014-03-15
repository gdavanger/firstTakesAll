package no.anderska.wta.questions;

import org.junit.Test;

import java.util.Random;

import static no.anderska.wta.questions.assertions.QuestionAssert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

public class FizzBuzzQuestionGeneratorTest {
    @Test
    public void shouldAnswer1ForValue1() throws Exception {
        assertThat(generatorWithNextValue(1).createQuestion())
                .hasQuestionEqualTo("1")
                .hasQuestionEqualTo("1");
    }

    @Test
    public void shouldAnswer2ForValue2() throws Exception {
        assertThat(generatorWithNextValue(2).createQuestion())
                .hasQuestionEqualTo("2")
                .hasAnswerEqualTo("2");
    }

    @Test
    public void shouldAnswerFizzForValue3() throws Exception {
        assertThat(generatorWithNextValue(3).createQuestion())
                .hasQuestionEqualTo("3")
                .hasAnswerEqualTo("Fizz");
    }

    @Test
    public void shouldAnswerBuzzForValue5() throws Exception {
        assertThat(generatorWithNextValue(5).createQuestion())
                .hasQuestionEqualTo("5")
                .hasAnswerEqualTo("Buzz");
    }

    @Test
    public void shouldAnswerFizzBuzzForValue15() throws Exception {
        assertThat(generatorWithNextValue(15).createQuestion())
                .hasQuestionEqualTo("15")
                .hasAnswerEqualTo("FizzBuzz");
    }

    @Test
    public void shouldCallRandomWithMaxMinusOne() throws Exception {
        Random mock = mock(Random.class);
        new FizzBuzzQuestionGenerator(mock).createQuestion();
        verify(mock).nextInt(FizzBuzzQuestionGenerator.MAX_VALUE - 1);
    }

    private FizzBuzzQuestionGenerator generatorWithNextValue(int value) {
        Random mock = mock(Random.class);
        when(mock.nextInt(anyInt())).thenReturn(value - FizzBuzzQuestionGenerator.MIN_VALUE);
        return new FizzBuzzQuestionGenerator(mock);
    }
}
