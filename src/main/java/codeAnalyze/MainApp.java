package codeAnalyze;

import java.io.IOException;

public class MainApp {
    public static void main(String[] args) throws IOException {
        String path = args[0];
        AnalyzeResult analyzeResult = new CodeAnalyzer().analyze(path);
        System.out.println("there are " + analyzeResult.fileCount() + " files under " + path);
        for (String fileType :
                analyzeResult.fileTypes()) {
            System.out.println(fileType + ": "+analyzeResult.fileCountForType(fileType));
        }
        System.out.println("total line count " + analyzeResult.totalLineCount());
    }
}
