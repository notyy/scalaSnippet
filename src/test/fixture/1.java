package codeAnalyze;

public class MainApp {
    public static void main(String[] args) {
        String path = args[0];
        int fileCount = new CodeAnalyzer().countFiles(path);
        System.out.println("there are " + fileCount + " files under " + path);
    }
}