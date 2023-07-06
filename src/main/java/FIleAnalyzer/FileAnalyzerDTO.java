package FIleAnalyzer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class FileAnalyzerDTO {
    private List<String> sentencesWithWord;
    private int wordCount;
}
