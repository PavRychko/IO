package FileAnalyzer;

import FIleAnalyzer.FileAnalyzer;
import FIleAnalyzer.FileAnalyzerDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class FileAnalyzerITest {
    private final String pathToFile = "src/test/resources/testFile.txt";


    @Test
    public void getSentencesWithExistingWordTest() {
        //given
        String wordToFind = "Оскар";
        int expectedSentencesCount = 6;
        int expectedWordCount = 10;
        FileAnalyzer fileAnalyzer = new FileAnalyzer(pathToFile, wordToFind);

        //do
        FileAnalyzerDTO fileAnalyzerDTO = fileAnalyzer.readAndAnalyzeFile();
        int resultWordCount = fileAnalyzerDTO.getWordCount();
        List<String> sentences = fileAnalyzerDTO.getSentencesWithWord();

        //verify
        assertEquals(expectedWordCount, resultWordCount);
        assertEquals(expectedSentencesCount, sentences.size());
        for (String sentence : sentences) {
            assertTrue(sentence.contains(wordToFind));
            assertTrue(sentence.endsWith(".") || sentence.endsWith("?") || sentence.endsWith("!"));
        }
    }

    @Test
    @DisplayName("getSentences with word that not in text returns empty list")
    public void getSentencesWithWordThatNotInTextReturnsEmptyListTest() {
        //given
        String notInTextWord = "Оріон";
        FileAnalyzer fileAnalyzer = new FileAnalyzer(pathToFile, notInTextWord);

        //do
        FileAnalyzerDTO fileAnalyzerDTO = fileAnalyzer.readAndAnalyzeFile();
        int resultWordCount = fileAnalyzerDTO.getWordCount();

        //verify
        assertEquals(0, resultWordCount);
        assertTrue(fileAnalyzerDTO.getSentencesWithWord().isEmpty());
    }

    @Test
    public void givingNotAFilePathTest() {
        //given
        String wrongPath = "src/test/resources";
        FileAnalyzer fileAnalyzer = new FileAnalyzer(wrongPath, "test");

        //do & verify
        assertNull(fileAnalyzer.readAndAnalyzeFile());
    }
}
