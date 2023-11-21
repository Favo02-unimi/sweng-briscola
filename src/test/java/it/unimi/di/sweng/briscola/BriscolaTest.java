package it.unimi.di.sweng.briscola;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class BriscolaTest {

    @Nested
    class turnWinnerTests {

        @Mock Player g1;
        @Mock Player g2;
        @Mock Deck deck;
        Briscola SUT;

        @ParameterizedTest
        @CsvSource({
                "4S,AS,6C,p1",
                "4S,7B,6C,p1",
                "FS,7B,2S,p2",
        })
        void turnWinnerTest(String briscola, String card1, String card2, String winner) {
            Card c1 = TestCardUtils.toCard(card1);
            Card c2 = TestCardUtils.toCard(card2);
            Card b = TestCardUtils.toCard(briscola);

            when(deck.draw()).thenReturn(b);
            when(g1.getName()).thenReturn("p1");
            when(g2.getName()).thenReturn("p2");

            SUT = new Briscola(g1, g2, deck);

            assertThat(SUT.establishTurnWinner(c1, c2).getName()).isEqualTo(winner);
        }
    }

}
