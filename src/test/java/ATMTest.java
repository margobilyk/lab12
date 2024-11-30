import org.junit.jupiter.api.Test;

import ua.ucu.edu.apps.task1.ATM;
import ua.ucu.edu.apps.task1.Section;
import ua.ucu.edu.apps.task1.Section_500;

import static org.junit.jupiter.api.Assertions.*;

class ATMTest {

    @Test
    void testSingleSectionExactAmount() {
        Section section = new Section_500();
        section.process(1000);
    }

    @Test
    void testSingleSectionNonExactAmount() {
        Section section = new Section_500();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            section.process(550);
        });
        assertEquals("Hernia sia stala", exception.getMessage());
    }

    @Test
    void testChainOfSections() {
        ATM atm = new ATM();
        atm.giveMoney(700);
    }

    @Test
    void testUnprocessableAmountInATM() {
        ATM atm = new ATM();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            atm.giveMoney(750);
        });
        assertEquals("Hernia sia stala", exception.getMessage());
    }

    @Test
    void testATMHandlesExactAmount() {
        ATM atm = new ATM();
        atm.giveMoney(1200);
    }
}
