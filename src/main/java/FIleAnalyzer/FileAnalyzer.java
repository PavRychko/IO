package FIleAnalyzer;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@Data
public class FileAnalyzer {
    private String path;
    private String wordToFind;


    public FileAnalyzerDTO readAndAnalyzeFile() {
        File file = new File(getPath());
        if (file.isFile()) {
            try (FileInputStream inputStream = new FileInputStream(file)) {
                String text = new String(inputStream.readAllBytes());
                return getSentencesWithWord(text, wordToFind);
            } catch (IOException e) {
                System.out.println(e.getMessage());
                System.out.println("an exception occurred during reading a file, empty result returned ");
                return new FileAnalyzerDTO(new ArrayList<>(), 0);
            }
        } else {
            System.out.println("path " + path + " is not a file path! Returning NUll result");
            return null;
        }
    }

    private int wordCount(String sentence, String wordToFind) {
        int counter = 0;
        int index = -1;
        while ((index = sentence.toLowerCase().indexOf(wordToFind, index + 1)) != -1) {
            counter++;
        }
        return counter;
    }


    public FileAnalyzerDTO getSentencesWithWord(String text, String wordToFind) {
        List<String> sentences = List.of(text.split("(?<=[.?!])"));
        int wordCounter = 0;
        List<String> sentencesWithWordInIt = new ArrayList<>();
        String word = wordToFind.toLowerCase();
        for (String sentence : sentences) {
            String sentenceInLower = sentence.toLowerCase();
            if (sentenceInLower.contains(word)) {
                wordCounter = wordCounter + wordCount(sentenceInLower, word);
                sentencesWithWordInIt.add(sentence);
            }
        }
        return new FileAnalyzerDTO(sentencesWithWordInIt, wordCounter);
    }
}
