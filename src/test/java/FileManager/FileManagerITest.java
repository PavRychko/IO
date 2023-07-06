package FileManager;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FileManagerITest {
    private final String path = "src/test/resources/FileManagerTest/";

    @Test
    public void countFilesTest() throws FileNotFoundException {
        //given
        int expectedFileCount = 6;

        //do
        int filesCount = FileManager.countFiles(path);

        //verify
        assertEquals(expectedFileCount, filesCount);
    }


    @Test
    public void CountFoldersTest() throws FileNotFoundException {
        //given
        int expectedDirsCount = 7;

        //do
        int dirsCount = FileManager.countDirs(path);

        //verify
        assertEquals(expectedDirsCount, dirsCount);
    }

    @Test
    @DisplayName("path to file caused exception test")
    public void filePathCausedExceptionTest() throws FileNotFoundException {
        //given
        String notADirPath = "src/test/resources/FileManagerTest/first/second/test1.txt";

        //do & verify
        assertThrows(IllegalArgumentException.class, () -> FileManager.countDirs(notADirPath));
        assertThrows(IllegalArgumentException.class, () -> FileManager.countFiles(notADirPath));

    }

    @Test
    @DisplayName("random string as a path should cause exception")
    public void randomStringAsAPathCauseExceptionTest() throws FileNotFoundException {
        //given
        String notAPathString = "123";

        //do & verify
        assertThrows(FileNotFoundException.class, () -> FileManager.countDirs(notAPathString));
        assertThrows(FileNotFoundException.class, () -> FileManager.countFiles(notAPathString));
    }


}
