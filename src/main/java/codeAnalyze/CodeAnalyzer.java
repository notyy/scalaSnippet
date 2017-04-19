package codeAnalyze;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CodeAnalyzer {

    private FileAnalyzer fileAnalyzer = new FileAnalyzerImpl();
    private DirectoryScanner directoryScanner = new DirectoryScannerImpl();

    public CodeAnalyzer(DirectoryScanner directoryScanner, FileAnalyzer fileAnalyzer) {
        this.directoryScanner = directoryScanner;
        this.fileAnalyzer = fileAnalyzer;
    }

    public CodeAnalyzer() {

    }

    private long totalLineCount(List<File> files) throws IOException {
        long count = 0;
        for (File file : files) {
            count += fileAnalyzer.countLines(file);
        }
        return count;
    }

    public AnalyzeResult analyze(String path) throws IOException {
        System.out.println("scanning path:" + path);
        List<File> files = directoryScanner.scan(path);
        Map<String,Integer> fileTypeCountMap = new HashMap<String, Integer>();
        for (File file :
                files) {
            String extPath = FileUtil.extPath(file);
            if(fileTypeCountMap.containsKey(extPath)){
                int currCount = fileTypeCountMap.get(extPath);
                fileTypeCountMap.put(extPath, currCount + 1);
            }else{
                fileTypeCountMap.put(extPath, 1);
            }
        }
        return new AnalyzeResult(fileCount(files), totalLineCount(files), fileTypeCountMap);
    }

    private int fileCount(List<File> files) {
        return files.size();
    }
}
