package codeAnalyze;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CodeAnalyzer {
    public int countFiles(String path) {
        System.out.println("scanning path:" + path);
        File directory = new File(path);
        File[] subFiles = directory.listFiles();
        int count = 0;
        for (File subFile : subFiles) {
            if (subFile.isFile()) {
                count += 1;
            } else {
                count += countFiles(subFile.getPath());
            }
        }
        return count;
    }
}