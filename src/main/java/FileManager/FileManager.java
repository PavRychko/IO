package FileManager;

import java.io.File;
import java.io.FileNotFoundException;

public class FileManager {

    public static int countFiles(String path) throws FileNotFoundException {
        File file = new File(path);
        pathCheck(file);
        return count(file, false);
    }

    public static int countDirs(String path) throws FileNotFoundException {
        File file = new File(path);
        pathCheck(file);
        return count(file, true) - 1; // -1 to exclude parent folder
    }

    private static void pathCheck(File file) throws FileNotFoundException {
        if (!file.exists()) {
            throw new FileNotFoundException("no file exists by path: " + file.getPath());
        }
        if(!file.isDirectory()) {
            throw new IllegalArgumentException("path: " + file.getPath() + " is not a directory path!");
        }
    }


    private static int count(File file, boolean isCountDirs) {
        int dirCounter = 0;
        int filesCounter = 0;
        if (!file.isDirectory()) {
            filesCounter++;
        } else {
            dirCounter++;
            File[] files = file.listFiles();
            if (files != null) {
                for (File file1 : files) {
                    if (isCountDirs) {
                        dirCounter = dirCounter + count(file1, true);
                    } else {
                        filesCounter = filesCounter + count(file1, false);
                    }
                    }
                }
            }
            if (isCountDirs) {
                return dirCounter;
            }
            return filesCounter;

    }

}
