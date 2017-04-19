package codeAnalyze;

import java.io.File;

public class FileUtil {
    public static String extPath(File file) {
        int index = file.getPath().lastIndexOf(".");
        return file.getPath().substring(index+1);
    }
}
