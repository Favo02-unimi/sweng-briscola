package it.unimi.di.sweng.briscola;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class PlayerTest {

    @Nested
    class comparableTests {
        Player SUT = new Player("g1");
        Player otherPlayer = new Player("g2");
        @ParameterizedTest
        @CsvSource({
                "3C 3S AC AB 3B RB RC 7B,3C 3S AC AB 3B RB RC 2B,0",
                "5B 7S 2C AC,2S 2C,1",
                "7S RC CS 4S,2C CS FS AC 6S 3C,-1",
        })
        void comparableTest(String g1cards, String g2cards, int result) {
            List<Card> g1cardsList = TestCardUtils.fromStringList(g1cards);
            for (int i = 0; i < g1cardsList.size(); i+=2)
                SUT.addWonCardsToPersonalDeck(g1cardsList.get(i), g1cardsList.get(i+1));

            List<Card> g2cardsList = TestCardUtils.fromStringList(g2cards);
            for (int i = 0; i < g2cardsList.size(); i+=2)
                otherPlayer.addWonCardsToPersonalDeck(g2cardsList.get(i), g2cardsList.get(i+1));

            assertThat(SUT.compareTo(otherPlayer)).isEqualTo(result);
        }
    }

}
