import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class InteractiveQATest {

    @Test
    void addQuestion() {
        Scanner scanner = mock(Scanner.class);
        when(scanner.nextLine()).thenReturn("What is your name? \"Ini\" \"Jim\"");

        InteractiveQA interactiveQA = new InteractiveQA();
        interactiveQA.addQuestion(scanner);

        Hashtable<String, String[]> expected = new Hashtable<>();
        expected.put("What is your name", new String[]{"Ini", "Jim"});
        assertEquals(expected, interactiveQA.questionAndAnswersToBeSaved);
    }

}