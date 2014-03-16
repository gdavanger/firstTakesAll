package no.anderska.wta.questions;

import org.junit.Test;

import java.util.Random;

import static no.anderska.wta.questions.assertions.QuestionAssert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

public class NextPrimeQuestionGeneratorTest {
    @Test
    public void shouldReturnQuestionsBetweenMinAndMax() throws Exception {
        Random random = mock(Random.class);
        AbstractQuestionGenerator generator = new NextPrimeQuestionGenerator(random);
        generator.createQuestion();
        verify(random).nextInt(eq(NextPrimeQuestionGenerator.MAX_VALUE - NextPrimeQuestionGenerator.MIN_VALUE));
    }

    @Test
    public void shouldReturnNextPrime() throws Exception {
        assertThat(generatorWithNext(140).createQuestion())
                .hasQuestionEqualTo("140")
                .hasAnswerEqualTo("149");
    }

    @Test
    public void shouldReturnNextPrimeForPrimes() throws Exception {
        assertThat(generatorWithNext(13).createQuestion())
                .hasQuestionEqualTo("13")
                .hasAnswerEqualTo("17");
    }

    private NextPrimeQuestionGenerator generatorWithNext(int next) {
        Random random = mock(Random.class);
        when(random.nextInt(anyInt())).thenReturn(next - NextPrimeQuestionGenerator.MIN_VALUE);
        return new NextPrimeQuestionGenerator(random);
    }
}
